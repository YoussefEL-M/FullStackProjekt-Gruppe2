package com.example.fullstackprojekt.Controller;

import com.example.fullstackprojekt.Model.Wish;
import com.example.fullstackprojekt.Service.UserService;
import com.example.fullstackprojekt.Service.WishService;
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
    WishService wishService;

    @Autowired
    UserService userService;

    /*@GetMapping("/")
    public String forside(){
        return "forside";
    }*/


    @PostMapping("/login")
    public String login(@RequestParam("username") String username,RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("username", username);

        return "redirect:/loggingIn";

    }

    @GetMapping("/loggingIn")
    public String loggedIn(@RequestParam("username") String username, Model model) {

        model.addAttribute("username", username);

        return "loggedIn";

    }
    @GetMapping("/WishForm")
    public String create(){return "WishForm";}

    @PostMapping("/createWish")
    public String createWish(
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("amount") int amount,
            @RequestParam("description") String description
    ){
        Wish wish = new Wish(name, price, amount, description);

        wishService.createWish(wish);
        return "redirect:/"; //skal ændres til ønskeliste når den er færdig
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

        Wish wish = new Wish(id,name, price, amount, description);

        wishService.updateWish(wish);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        wishService.deleteWishById(id);

        return "redirect:/"; //skal ændres til wishlist
    }

    @GetMapping("/wishlist")
    public String wishlist(@RequestParam("id") int id, Model model){
        model.addAttribute("wishlist", wishService.getWishesInWishlist(id));
        return "wishlist";
    }
}
