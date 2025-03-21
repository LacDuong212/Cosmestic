package com.api.cosmesticapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {     // 22110394 - Ong Vĩnh Phát
    private String username;
    private String password;
    private String email;
    private String gender;
}
