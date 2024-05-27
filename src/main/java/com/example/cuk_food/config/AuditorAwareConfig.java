package com.example.cuk_food.config;

import com.example.cuk_food.dto.Oauth2.CustomOAuth2User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Configuration
public class AuditorAwareConfig implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // 현재 인증된 사용자의 정보 가져오기
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomOAuth2User) {
            // UserDetails에서 사용자의 이름 반환
            return Optional.of(((CustomOAuth2User) principal).getUsername());
        }
        return Optional.of(principal.toString());
    }
}
