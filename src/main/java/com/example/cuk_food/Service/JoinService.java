package com.example.cuk_food.Service;

import com.example.cuk_food.entity.UserAccount;
import com.example.cuk_food.repository.UserAccountRepository;
import com.example.cuk_food.request.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JoinService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserAccountRepository userAccountRepository;

    public Long save(SignupRequest dto) {
        String encodedPassword = bCryptPasswordEncoder.encode(dto.getPassword());

        UserAccount user = User.builder()
                .userId(dto.getUserId())
                .password(encodedPassword) // Store the hashed password
                .build();

        return userAccountRepository.save(user).getUserId();
    }


}
