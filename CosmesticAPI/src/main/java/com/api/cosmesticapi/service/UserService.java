// 22110394 - Ong Vĩnh Phát
// 22110410 - Huỳnh Thị Mỹ Tâm
package com.api.cosmesticapi.service;

import com.api.cosmesticapi.dto.*;

public interface UserService {
    ApiResponse registerUser(RegisterRequest request);
    ApiResponse verifyOTP(VerifyOTPRequest request);
    String generateOTP();
    public LoginResponse login(LoginRequest request);


}
