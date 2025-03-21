// 22110410 - Huỳnh Thị Mỹ Tâm
package com.api.cosmesticapi.dto;

import com.api.cosmesticapi.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponse {
    private boolean success;
    private String message;
    private User user; // We'll send the user data back if login is successful

}
