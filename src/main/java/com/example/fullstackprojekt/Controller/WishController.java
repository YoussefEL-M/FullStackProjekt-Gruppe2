package com.example.fullstackprojekt.Controller;

import com.example.fullstackprojekt.Model.Wish;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
}
