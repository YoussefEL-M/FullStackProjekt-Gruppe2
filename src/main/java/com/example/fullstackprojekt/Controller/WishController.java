package com.example.fullstackprojekt.Controller;

import com.example.fullstackprojekt.Model.Wish;
import com.example.fullstackprojekt.Repository.WishlistRepo;
import com.example.fullstackprojekt.Service.WishService;
import com.example.fullstackprojekt.Service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class WishController {
    @Autowired
    private WishlistService wishlistService;
    @Autowired
    private WishService wishService;

    @GetMapping("/")
    public String forside(){
        return "forside";
    }

    @PostMapping("/submitWish")
    public String submitWish(Wish wish) {
        // Save the wish to the database
        return "redirect:/";
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

    @GetMapping("/wishlist/{id}")
    public String wishlist(@RequestParam("id") int id, Model model){
        model.addAttribute("wishlist", wishService.getWishesInWishlist(id));

        return "wishlist";
    }
}
