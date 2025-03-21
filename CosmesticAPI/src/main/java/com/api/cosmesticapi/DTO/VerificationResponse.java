package com.api.cosmesticapi.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class VerificationResponse {
    private boolean success;
    private String message;
}