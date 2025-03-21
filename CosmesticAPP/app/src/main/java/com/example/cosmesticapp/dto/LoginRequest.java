// 22110410 - Huỳnh Thị Mỹ Tâm
package com.example.cosmesticapp.dto;

public class LoginRequest {
    private String username; // Can be either username or email
    private String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
