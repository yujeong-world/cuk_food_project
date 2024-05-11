package com.example.cuk_food.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignupResponse {
    private boolean success;
    private String userId;
    private String message;
}
