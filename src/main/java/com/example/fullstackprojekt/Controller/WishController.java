package com.example.fullstackprojekt.Controller;

import com.example.fullstackprojekt.Model.Wish;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class WishController {

    @GetMapping("/")
    public String forside(){
        return "forside";
    }

    @PostMapping("/submitWish")
    public String submitWish(Wish wish) {
        // Save the wish to the database
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(@RequestParam("username") String username,RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("username", username);

        return "redirect:/loggingIn";

    }

    @PostMapping("/loggingIn")
    public String loggedIn(@RequestParam("username") String username, Model model) {

        model.addAttribute("username", username);

        return "loggedIn";

    }

    @GetMapping("/createUser")
    public String createUser() {

        return "createUser";
    }

    @PostMapping("/createUser")
    public String createAnAccount(@RequestParam("username")String username, RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("username", username);

        return "redirect:/loggingIn";
    }

}
