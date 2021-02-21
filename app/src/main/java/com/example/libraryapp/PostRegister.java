package com.example.libraryapp;

import android.provider.ContactsContract;

public class PostRegister {
    private Integer id;
    private final String name;
    private final String email;
    private final String password;
    private final String avatarUrl;

    public PostRegister(String name, String avatarUrl, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.avatarUrl = avatarUrl;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
