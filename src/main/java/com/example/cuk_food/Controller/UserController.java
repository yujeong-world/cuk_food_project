package com.example.cuk_food.Controller;// In your UserController.java

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.cuk_food.entity.UserEntity;
import com.example.cuk_food.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.example.cuk_food.dto.Oauth2.CustomOAuth2User;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public UserEntity getUser(@AuthenticationPrincipal CustomOAuth2User user) {
        return userRepository.findByUsername(user.getUsername());
    }
}
