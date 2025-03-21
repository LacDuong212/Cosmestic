package com.api.cosmesticapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest { //Dương Nguyễn Hoài Bảo - 22110283
    private String username;
    private String password;
    private String email;
    private String gender;
}
