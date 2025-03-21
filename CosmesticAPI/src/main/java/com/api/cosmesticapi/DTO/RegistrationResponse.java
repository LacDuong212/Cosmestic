package com.api.cosmesticapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegistrationResponse { //Dương Nguyễn Hoài Bảo - 22110283
    private boolean success;
    private String message;
    private String email;
}
