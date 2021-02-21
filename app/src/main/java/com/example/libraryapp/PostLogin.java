package com.example.libraryapp;

public class PostLogin {
    private String Name;
    private String Password;

    public PostLogin(String name, String password) {
        Name = name;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return Password;
    }
}
