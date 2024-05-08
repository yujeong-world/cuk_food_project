package com.example.cuk_food.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HellowController {
    @GetMapping("/api/test")
    public String hello(){
        return "백엔드입니다 테스트입니다.";
    }
}
