package com.example.fullstackprojekt.Controller;

import com.example.fullstackprojekt.Model.User;
import com.example.fullstackprojekt.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {


    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        RedirectAttributes redirectAttributes){

        // Autentificere user i forhold til databasen
        User user = userService.getUserByUsernameAndPassword(username, password);
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        user.setPassword(encodedPassword);

        if (user != null) {
            // Gemmer autentificeret users i session
            session.setAttribute("user", user);
            return "redirect:/forside";
        } else {
            // Autentificering fejlet, redirect tilbage til login siden med en error besked
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login";
        }
    }
}
