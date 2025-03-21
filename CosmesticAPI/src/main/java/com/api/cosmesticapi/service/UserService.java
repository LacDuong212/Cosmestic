package com.api.cosmesticapi.service;

import com.api.cosmesticapi.dto.ApiResponse;
import com.api.cosmesticapi.dto.RegisterRequest;
import com.api.cosmesticapi.dto.VerifyOTPRequest;

public interface UserService {     // 22110394 - Ong Vĩnh Phát

    ApiResponse registerUser(RegisterRequest request);
    ApiResponse verifyOTP(VerifyOTPRequest request);
    String generateOTP();

}
