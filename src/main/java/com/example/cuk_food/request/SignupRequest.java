package com.example.cuk_food.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    private String userId;
    private String password;
    private String nickname;
    private String email;
}
