package com.example.fullstackprojekt.Controller;

import com.example.fullstackprojekt.Model.Wish;
import com.example.fullstackprojekt.Service.UserService;
import com.example.fullstackprojekt.Service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class WishController {

    @Autowired
    WishService wishService;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String forside(){
        return "forside";
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

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        // Assuming you have a service class for wishes
        Wish wish = wishService.getWishById(id);
        model.addAttribute("Wish", wish);
        return "wishUpdateForm";
    }

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

}
