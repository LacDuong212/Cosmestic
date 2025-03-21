package com.api.cosmesticapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest { //Dương Nguyễn Hoài Bảo - 22110283
    private String username;
    private String password;
    private String email;
    private String gender;
}
