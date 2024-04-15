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
    UserService userService;

    /*@GetMapping("/")
    public String forside(){
        return "forside";
    }*/


    @GetMapping("/login")
    public String login() {

        return "/login";

    }

    @PostMapping("/loggingIn")
    public String loggedIn(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {

        model.addAttribute("username", username);
        model.addAttribute("password", password);

        return "loggedIn";

    }

    @GetMapping("/WishForm")
    public String create(){return "WishForm";}

    @PostMapping("/createWish")
    public String createWish(
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("amount") int amount,
            @RequestParam("description") String description,
            @RequestParam("reserved") boolean reserved
    ){

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
            @RequestParam("reserved") boolean reserved,
            @RequestParam("user_id") int user_id
    ){

        Wish wishToUpdate = wishService.getWishById(id);
        Wish wish = new Wish(id,name, price, amount, description, reserved, user_id);

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
    public String createAnAccount(@RequestParam("brugernavn")String brugernavn, @RequestParam("adgangskode") String adgangskode, RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("username", brugernavn);
        redirectAttributes.addAttribute("password", adgangskode);

        return "redirect:/login";
    }

    @GetMapping("/wishlist")
    public String wishlist(@RequestParam("id") int id, Model model){
        model.addAttribute("wishlist", wishService.getWishesInWishlist(id));
        return "wishlist";
    }
}
