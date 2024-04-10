package com.example.fullstackprojekt.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class WishController {

    @GetMapping("/")
    public String index() {

        return "forside";
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
