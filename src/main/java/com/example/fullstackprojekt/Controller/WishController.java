package com.example.fullstackprojekt.Controller;

import com.example.fullstackprojekt.Model.User;
import com.example.fullstackprojekt.Model.Wish;
import com.example.fullstackprojekt.Service.UserService;
import com.example.fullstackprojekt.Service.WishService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.fullstackprojekt.Repository.WishlistRepo;
import com.example.fullstackprojekt.Service.WishService;
import com.example.fullstackprojekt.Service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String loggedIn(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {

        // Perform authentication
        User user = userService.getUserByUsernameAndPassword(username, password);
        if (user != null) {
            // Store authenticated user in session
            session.setAttribute("user", user);
            return "redirect:/forside";
        } else {
            // Authentication failed, redirect back to login page with error message
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login";
        }

    }

    @GetMapping("/WishForm")
    public String create(){return "WishForm";}

    @PostMapping("/createWish")
    public String createWish(
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("amount") int amount,
            @RequestParam("description") String description,
            @RequestParam("reserved") boolean reserved,
            RedirectAttributes redirectAttributes
    ){

        if(name.isEmpty() || amount <=0 || description.isEmpty()){

            redirectAttributes.addFlashAttribute("error", "Vær venlig og indtast korrekt værdier i inputfelterne");
            return "redirect:/WishForm";
        }

        Wish newWish = new Wish(name, price, amount, description, reserved);
        wishService.createWish(newWish);


        return "redirect:/wishlist?id="+newWish.getId();
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
            @RequestParam("description") String description,
            @RequestParam("reserved") boolean reserved
    ){

        Wish wishToUpdate = wishService.getWishById(id);

        wishToUpdate.setName(name);
        wishToUpdate.setPrice(price);
        wishToUpdate.setAmount(amount);
        wishToUpdate.setDescription(description);
        wishToUpdate.setReserved(reserved);

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
    public String createAnAccount(@RequestParam("brugernavn") String username,
                                  @RequestParam("adgangskode") String password,
                                  RedirectAttributes redirectAttributes) {


        if (username.isEmpty() || password.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Please provide a username and password.");
            return "redirect:/createUser";
        }

        // Create a new user and save to the database
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        userService.createUser(newUser);

        return "redirect:/forside";
    }

    @GetMapping("/wishlist")
    public String wishlist(@RequestParam("id") int id, Model model){
        model.addAttribute("wishlist", wishService.getWishesInWishlist(id));
        return "wishlist";
    }
}
