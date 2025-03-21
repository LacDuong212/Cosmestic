package com.api.cosmesticapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtpVerificationRequest { //Dương Nguyễn Hoài Bảo - 22110283
    private String email;
    private String otp;
}
