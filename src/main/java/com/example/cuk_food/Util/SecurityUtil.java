package com.example.cuk_food.Util;

import com.example.cuk_food.dto.Oauth2.CustomOAuth2User;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;


@NoArgsConstructor
public class SecurityUtil {

    public static String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomOAuth2User) {
            return ((CustomOAuth2User) principal).getUsername();
        }
        throw new RuntimeException("Unexpected principal type: " + principal.getClass().getName());
    }
}
