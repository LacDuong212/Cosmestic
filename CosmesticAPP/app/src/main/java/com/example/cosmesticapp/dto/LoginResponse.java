// 22110410 - Huỳnh Thị Mỹ Tâm
package com.example.cosmesticapp.dto;

import com.example.cosmesticapp.model.User;

public class LoginResponse {
    private boolean success;
    private String message;
    private User user;


    public LoginResponse(boolean success, String message, User user) {
        this.success = success;
        this.message = message;
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
