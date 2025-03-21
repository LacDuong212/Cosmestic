package com.api.cosmesticapi.service;

public interface EmailService {
    void sendOTPEmail(String to, String otp);
}
