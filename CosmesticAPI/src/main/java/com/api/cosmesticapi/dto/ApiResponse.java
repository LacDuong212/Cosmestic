package com.api.cosmesticapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse { // 22110394 - Ong Vĩnh Phát
    private boolean success;
    private String message;

}
