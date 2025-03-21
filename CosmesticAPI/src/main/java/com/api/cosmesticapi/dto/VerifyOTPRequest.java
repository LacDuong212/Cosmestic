package com.api.cosmesticapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VerifyOTPRequest { //Dương Nguyễn Hoài Bảo - 22110283
    private String email;
    private String otp;

}
