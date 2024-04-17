package com.example.fullstackprojekt.Controller;

import com.example.fullstackprojekt.Model.User;
import com.example.fullstackprojekt.Model.Wish;
import com.example.fullstackprojekt.Model.Wishlist;
import com.example.fullstackprojekt.Service.UserService;
import com.example.fullstackprojekt.Service.WishService;
import com.example.fullstackprojekt.Service.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

        User user = userService.getUserByUsername(username);
        if(user.getPassword().equals(password)) {
            session.setAttribute("User", user);
            return "redirect:/userpage";
        }
        else return "denied";

    }

    @GetMapping("/userpage")
    public String userpage(Model model, HttpSession session){
        User user = (User) session.getAttribute("User");
        model.addAttribute("link", "wishlist?id="+user.getId());

        return "/userpage";
    }

    @GetMapping("/WishForm")
    public String create(Model model, HttpSession session){
        User user = (User) session.getAttribute("User");
        int userId = user.getId();

        List<Wishlist> wishlists = wishlistService.getWishlistsForUser(userId);

        model.addAttribute("wishlists", wishlists);
        return "WishForm";
    }

    @PostMapping("/createWish")
    public String createWish(
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("amount") int amount,
            @RequestParam("description") String description,
            @RequestParam("url") String url,
            @RequestParam("wishlistId") int wishlistId,
            HttpSession session){

        User user = (User) session.getAttribute("User");
        int user_id = user.getId();


        Wish newWish = new Wish(name, price, amount, description, url, user_id);

        wishService.createWish(newWish);

        return "redirect:/wishlist?id=" + wishlistId;
    }

    @GetMapping("/")
    public String showWishlist(Model model) {
        List<Wish> wishes = wishService.getAllWishes();
        model.addAttribute("wishes", wishes);
        return "forside";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Wish wish = wishService.getWishById(id);
        model.addAttribute("Wish", wish);
        return "wishUpdateForm";
    }

    @PostMapping("/updateWish")
    public String updateWish(
            @RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("amount") int amount,
            @RequestParam("description") String description
    ){

        Wish wishToUpdate = wishService.getWishById(id);

        wishToUpdate.setName(name);
        wishToUpdate.setPrice(price);
        wishToUpdate.setAmount(amount);
        wishToUpdate.setDescription(description);

        wishService.updateWish(wishToUpdate);

        return "redirect:/wishlist?id=" + wishToUpdate.getId();
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
    public String wishlist(@RequestParam("id") int id, Model model, HttpSession session){
        model.addAttribute("wishlistObject", wishlistService.getWishlistById(id));
        model.addAttribute("wishlist", wishService.getWishesInWishlist(id));
        User user = (User) session.getAttribute("User");
        if(user.getId() == wishlistService.getWishlistById(id).getUserId()){
            return "wishlist";
        }
        else return "denied";
    }

    @GetMapping("/wishlistshare")
    public String wishlistshare(@RequestParam("id") int id, Model model, HttpSession session) {
        model.addAttribute("wishlistObject", wishlistService.getWishlistById(id));
        model.addAttribute("wishlist", wishService.getWishesInWishlist(id));
        User user = (User) session.getAttribute("User");
        if (user.getId() != wishlistService.getWishlistById(id).getUserId()) {
            return "wishlistShare";
        } else return "denied";
    }

    @GetMapping("/reserve")
    public String reserve(@RequestParam("id") int id){

        Wish wishToUpdate = wishService.getWishById(id);
        wishService.reserveWish(wishToUpdate);

        return "wishlistShare?id="+wishToUpdate.getUser_id();
    }
}
