package com.example.cuk_food.Service;

import com.example.cuk_food.entity.UserAccount;
import com.example.cuk_food.repository.UserAccountRepository;
import com.example.cuk_food.request.SignupRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final PasswordEncoder passwordEncoder;
    private final UserAccountRepository userAccountRepository;

    public JoinService(PasswordEncoder passwordEncoder, UserAccountRepository userAccountRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userAccountRepository = userAccountRepository;
    }

    public String save(SignupRequest dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        UserAccount user = UserAccount.builder()
                .userId(dto.getUserId())
                .userPassword(encodedPassword)
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .build();

        return userAccountRepository.save(user).getUserId();
    }
}
