package com.api.cosmesticapi.model;

import com.phatbee.backendmopr.dto.RegisterRequest;

public class OtpEntry {
    private String otp;
    private long expiryTime;
    private RegisterRequest registerRequest;

    public OtpEntry(String otp, long expiryTime, RegisterRequest registerRequest) {
        this.otp = otp;
        this.expiryTime = expiryTime;
        this.registerRequest = registerRequest;
    }

    public String getOtp() {
        return otp;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public RegisterRequest getRegisterRequest() {
        return registerRequest;
    }
}

