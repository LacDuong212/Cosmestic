package com.api.cosmesticapi.controller;

import com.api.cosmesticapi.dto.ApiResponse;
import com.api.cosmesticapi.dto.RegisterRequest;
import com.api.cosmesticapi.dto.VerifyOTPRequest;
import com.api.cosmesticapi.service.Impl.UserServiceImpl;
import com.api.cosmesticapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")

public class AuthController {     // 22110394 - Ong Vĩnh Phát
    @Autowired
    private UserService userService = new UserServiceImpl();

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest request) {
        ApiResponse response = userService.registerUser(request);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponse> verifyOTP(@RequestBody VerifyOTPRequest request) {
        ApiResponse response = userService.verifyOTP(request);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(response);
    }

}
