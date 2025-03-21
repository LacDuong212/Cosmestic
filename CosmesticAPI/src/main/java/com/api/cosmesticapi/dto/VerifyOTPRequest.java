package com.api.cosmesticapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VerifyOTPRequest { // 22110394 - Ong Vĩnh Phát
    private String email;
    private String otp;

}
