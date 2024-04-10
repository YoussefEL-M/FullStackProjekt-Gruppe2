package com.example.fullstackprojekt.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WishController {

    @GetMapping("/")
    public String forside(){
        return "forside";
    }
}
