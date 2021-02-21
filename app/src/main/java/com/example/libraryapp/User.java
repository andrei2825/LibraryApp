package com.example.libraryapp;

public class User {
    private Integer Id;
    private String Name;
    private String Email;
    private String Password;
    private String AvatarUrl;

    public Integer getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getAvatarUrl() {
        return AvatarUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", AvatarUrl='" + AvatarUrl + '\'' +
                '}';
    }
}
