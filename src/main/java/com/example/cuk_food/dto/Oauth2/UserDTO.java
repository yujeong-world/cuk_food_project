package com.example.cuk_food.dto.Oauth2;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String role;
    private String name;
    private String username;
    private String email;
}