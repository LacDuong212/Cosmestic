package com.api.cosmesticapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ApiResponse<Void> registerUser(@RequestBody RegisterRequest registerRequest) {
        return userService.register(registerRequest);
    }

    @PostMapping("/register/verify")
    public ApiResponse<Void> verifyRegisterUser(@RequestBody OtpVerifyRequest otpVerifyRequest) {
        return userService.verifyRegister(otpVerifyRequest);
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
}
