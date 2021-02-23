package com.example.libraryapp;

public class LoginResponse {
    private int Id;
    private String Name;
    private String AvatarUrl;
    private String Email;
    private String Password;

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getAvatarUrl() {
        return AvatarUrl;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", AvatarUrl='" + AvatarUrl + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
