package com.example.libraryapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServerApi {

    @GET("/api/books")
    Call<List<GetBooks>> getBooks();

    @GET("/api/book/{id}")
    Call<List<GetBooks>> getBook(@Path("id") int bookId);

    @GET("/api/users")
    Call<List<User>> getUsers();

    @GET("/api/user/{id}")
    Call<List<User>> getUser(@Path("id") int userId);

    @POST("/api/register")
    Call<PostRegister> createAccount(@Body PostRegister postRegister);

    @POST("/api/login")
    Call<PostLogin> loginAccount(@Body PostLogin postLogin);
}
