// 22110410 - Huỳnh Thị Mỹ Tâm

package com.api.cosmesticapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRequest {
    private String username; // Could be username or email
    private String password;

}
