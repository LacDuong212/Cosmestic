package com.example.cosmesticapp.dto;

public class VerifyOTPRequest {
    private String email;
    private String otp;

    // Constructor
    public VerifyOTPRequest(String email, String otp) {
        this.email = email;
        this.otp = otp;
    }

    // Getters and setters
}
