package com.example.libraryapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static Retrofit retrofit = null;

    private RetrofitFactory() {}

    public static  Retrofit getRetrofit() {
        if (retrofit == null) {
             retrofit = new Retrofit.Builder()
                    .baseUrl("https://simple-express-server-3nkkx.ondigitalocean.app")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;
    }
}
