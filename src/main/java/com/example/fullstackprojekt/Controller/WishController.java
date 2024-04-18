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
            @RequestParam("wishlistId") int wishlistId,
            @RequestParam("img-url") String imgUrl
    ) {
        Wish newWish = new Wish(name, price, amount, description, url, imgUrl);
        newWish.setWishlist_id(wishlistId);
        wishService.createWish(newWish);

        return "redirect:/wishlist?id=" + wishlistId;
    }
    @GetMapping("/WishlistForm")
    public String createWishlistForm(Model model, HttpSession session) {
        try {
            User user = (User) session.getAttribute("User");
            int userId = user.getId();

            List<Wishlist> wishlists = wishlistService.getWishlistsForUser(userId);

            model.addAttribute("wishlists", wishlists);
            return "WishlistForm";
        } catch (EmptyResultDataAccessException e) {
            return "404";
        }
    }

    @PostMapping("/createWishlist")
    public String createWishlist(
            @RequestParam("name") String name,
            HttpSession session) {
        try {
            User user = (User) session.getAttribute("User");
            int userId = user.getId();

            Wishlist newWishlist = new Wishlist(name);
            newWishlist.setUserId(userId);

            wishlistService.createWishlist(newWishlist);

            return "redirect:/userpage";
        } catch (Exception e) {
            return "404";
        }
    }

    @GetMapping("/")
    public String displayFrontpage() {

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

            return "redirect:/wishlist?id=" + wishToUpdate.getWishlist_id();
        } catch (EmptyResultDataAccessException E){
            return "404";
        }
    }

    @GetMapping("/updateList/{id}")
    public String showWishlistUpdateForm(@PathVariable("id") int id, Model model){
        try {
            Wishlist wishlist = wishlistService.getWishlistById(id);
            model.addAttribute("Wislist", wishlist);
            return "wishlistUpdateForm";
        } catch (EmptyResultDataAccessException E){
            return "404";
        }
    }

    @PostMapping("/updateWishlist")
    public String updateWishlist(
            @RequestParam("id") int id,
            @RequestParam("name") String name
            ){
        try{
            Wishlist wishlistToUpdate = wishlistService.getWishlistById(id);

            wishlistToUpdate.setName(name);

            wishlistService.updateWishlist(wishlistToUpdate);
            return "redirect:/userpage?id="+id;
        }catch (EmptyResultDataAccessException E){
            return "404";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        wishService.deleteWishById(id);

        return "redirect:/wishlist?id="+id;
    }

    @GetMapping("/deleteList/{id}")
    public String deleteWishlist(@PathVariable("id") int id){
        wishlistService.deleteWishlist(id);

        return "redirect:/userpage?id="+id;
    }

    @GetMapping("/reserve/{id}")
    public String reserve(@PathVariable("id") int id, HttpSession session){
        try {
            Wish wish = wishService.getWishById(id);
            User user = (User) session.getAttribute("User");
            if(user != null){
                wish.setReserved(true);
                wish.setReserved_by(user.getId());
                wishService.updateWish(wish);
            }

        } catch (EmptyResultDataAccessException E){
            return "404";
        }

        return "redirect:/wishlist?id="+id;
    }
    @GetMapping("/cancel/{id}")
    public String cancel(@PathVariable("id") int id, HttpSession session){
        try {
            Wish wish = wishService.getWishById(id);
            User user = (User) session.getAttribute("User");
            if(user.getId() == wish.getReserved_by()){
                wish.setReserved_by(0);
                wish.setReserved(false);
                wishService.updateWish(wish);
            }

        } catch (EmptyResultDataAccessException E){
            return "404";
        }

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
    public String wishlist(@RequestParam("id") int id, Model model, HttpSession session){
        try {
            User user = (User) session.getAttribute("User");
            if(user == null)
                user = new User(0, "No user", "No user", "null", false);
            Wishlist wishlist = wishlistService.getWishlistById(id);
            model.addAttribute("wishlistObject", wishlist);
                    model.addAttribute("wishlist", wishService.getWishesInWishlist(wishlist.getId()));
                    model.addAttribute("user", user);
                    return "wishlist";

        } catch (EmptyResultDataAccessException E){
            return "404";
        }
    }
}

