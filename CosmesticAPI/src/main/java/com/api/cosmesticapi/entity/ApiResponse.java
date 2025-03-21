package com.api.cosmesticapi.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
public class ApiResponse<T> {
    private boolean isSuccess;
    @Getter
    private String message;
    @Getter
    private T data;

    public ApiResponse() {}

    public ApiResponse(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public ApiResponse(boolean isSuccess, String message, T data) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.data = data;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

}
