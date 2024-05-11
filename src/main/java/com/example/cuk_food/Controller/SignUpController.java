package com.example.cuk_food.Controller;
import com.example.cuk_food.request.SignupRequest;

import com.example.cuk_food.Service.SignUpService;
import com.example.cuk_food.request.SignupResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SignUpController {

    private final SignUpService signUpService;

    public SignUpController(SignUpService signUpService){
        this.signUpService = signUpService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest signupRequest) {
        try {
            String userId = signUpService.save(signupRequest);
            SignupResponse response = new SignupResponse(true, userId, "User successfully registered.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            SignupResponse response = new SignupResponse(false, null, "Registration failed: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
