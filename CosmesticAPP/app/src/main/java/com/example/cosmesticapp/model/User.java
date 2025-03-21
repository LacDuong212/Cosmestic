// 2211410 - Huỳnh Thị Mỹ Tâm
package com.example.cosmesticapp.model;

import java.io.Serializable;

public class User implements Serializable {
    private Long userId;
    private String username;
    private String email;
    private String gender;
    private String image;
    private boolean active;


    public User(Long userId, String username, String email, String gender, String image, boolean active) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.image = image;
        this.active = active;
    }

    public User() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
