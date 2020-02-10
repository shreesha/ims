package com.example.ims.views;

import lombok.Data;

@Data
public class AuthUser {
    private String username;
    private String password;

    public AuthUser() {
    }

    public AuthUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
