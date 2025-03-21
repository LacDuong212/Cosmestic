package com.api.cosmesticapi.service;

import com.api.cosmesticapi.dto.ApiResponse;
import com.api.cosmesticapi.dto.RegisterRequest;
import com.api.cosmesticapi.dto.VerifyOTPRequest;

public interface UserService {
    ApiResponse registerUser(RegisterRequest request);
    ApiResponse verifyOTP(VerifyOTPRequest request);
    String generateOTP();

}
