package com.example.fullstackprojekt.Controller;

import com.example.fullstackprojekt.Model.User;
import com.example.fullstackprojekt.Model.Wish;
import com.example.fullstackprojekt.Model.Wishlist;
import com.example.fullstackprojekt.Model.Wishlist;
import com.example.fullstackprojekt.Model.User;
import com.example.fullstackprojekt.Model.Wish;
import com.example.fullstackprojekt.Service.UserService;
import com.example.fullstackprojekt.Service.WishService;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.fullstackprojekt.Repository.WishlistRepo;
import com.example.fullstackprojekt.Service.WishService;
import com.example.fullstackprojekt.Service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class WishController {

    @Autowired
    private WishlistService wishlistService;
    @Autowired
    private WishService wishService;

    @Autowired
    UserService userService;


    @GetMapping("/login")
    public String login() {

        return "/login";

    }

    @PostMapping("/loggingIn")
    public String loggingIn(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            Model model,
                            HttpSession session) {
        try {
            User user = userService.getUserByUsername(username);
            if (user.getPassword().equals(password)) {
                session.setAttribute("User", user);
                return "redirect:/userpage";
            } else {
                model.addAttribute("passwordMismatch", true);
                return "login";
            }
        } catch (EmptyResultDataAccessException E){
            model.addAttribute("usernameMissing", true);
            return "login";
        }

    }

    @GetMapping("/userpage")
    public String userpage(Model model, HttpSession session){
        User user = (User) session.getAttribute("User");
        model.addAttribute("link", "wishlist?id="+user.getId());
        return "/userpage";
    }

    @GetMapping("/WishForm")
    public String create(Model model, HttpSession session){
        try {
            User user = (User) session.getAttribute("User");
            int userId = user.getId();

            List<Wishlist> wishlists = wishlistService.getWishlistsForUser(userId);

            model.addAttribute("wishlists", wishlists);
            return "WishForm";
        } catch (EmptyResultDataAccessException E){
            return "404";
        }
    }

    @PostMapping("/createWish")
    public String createWish(
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("amount") int amount,
            @RequestParam("description") String description,
            @RequestParam("url") String url,
            @RequestParam("wishlistId") int wishlistId
    ) {
        Wish newWish = new Wish(name, price, amount, description, url);
        newWish.setWishlist_id(wishlistId);
        wishService.createWish(newWish);

        return "redirect:/wishlist";
    }

    @GetMapping("/")
    public String showWishlist(Model model) {

        return "forside";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        try {
            Wish wish = wishService.getWishById(id);
            model.addAttribute("Wish", wish);
            return "wishUpdateForm";
        } catch (EmptyResultDataAccessException E){
            return "404";
        }
    }

    @PostMapping("/updateWish")
    public String updateWish(
            @RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("amount") int amount,
            @RequestParam("description") String description
    ){
        try {
            Wish wishToUpdate = wishService.getWishById(id);

            wishToUpdate.setName(name);
            wishToUpdate.setPrice(price);
            wishToUpdate.setAmount(amount);
            wishToUpdate.setDescription(description);

            wishService.updateWish(wishToUpdate);

            return "redirect:/wishlist?id=" + wishToUpdate.getId();
        } catch (EmptyResultDataAccessException E){
            return "404";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        wishService.deleteWishById(id);

        return "redirect:/wishlist?id="+id;
    }

    @GetMapping("/createUser")
    public String createUser() {

        return "createUser";
    }

    @PostMapping("/createUser")
    public String createAnAccount(@RequestParam("brugernavn")String brugernavn,
                                  @RequestParam("adgangskode") String adgangskode,
                                  RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("username", brugernavn);
        redirectAttributes.addAttribute("password", adgangskode);

        User newUser = new User(brugernavn, adgangskode);
        userService.createUser(newUser);

        return "redirect:/login";
    }

    @GetMapping("/wishlist")
    public String wishlist(Model model, HttpSession session){
        try {
            User user = (User) session.getAttribute("User");
            if (user != null) {
                int userId = user.getId();
                Wishlist wishlist = wishlistService.getWishlistById(userId);
                if (wishlist != null) {
                    model.addAttribute("wishlistObject", wishlist);
                    model.addAttribute("wishlist", wishService.getWishesInWishlist(wishlist.getId()));
                    return "wishlist";
                } else {
                    return "denied";
                }
            } else {
                return "login";
            }
        } catch (EmptyResultDataAccessException E){
            return "404";
        }
    }
}

