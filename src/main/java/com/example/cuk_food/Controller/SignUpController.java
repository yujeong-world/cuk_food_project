package com.example.cuk_food.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/api")
@RestController
public class SignUpController {
    @PostMapping("/signup")
    public String signup(){
        return "signup";
    }

}
