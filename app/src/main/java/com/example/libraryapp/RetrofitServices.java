package com.example.libraryapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitServices {

    @POST("/api/login")
    Call<LoginResponse> isValidUser(@Body PostLogin postLogin);
}
