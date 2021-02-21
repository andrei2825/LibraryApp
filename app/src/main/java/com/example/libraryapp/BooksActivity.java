package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BooksActivity extends  AppCompatActivity{

    private RecyclerView booksRecView;
    private BookRecViewAdapter adapter;
    private ServerApi serverApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://simple-express-server-3nkkx.ondigitalocean.app")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serverApi = retrofit.create(ServerApi.class);

        adapter = new BookRecViewAdapter(this);
        initViews();

        booksRecView.setAdapter(adapter);
        booksRecView.setLayoutManager(new GridLayoutManager(this, 2));

        Call<List<GetBooks>> call = serverApi.getBooks();
        call.enqueue(new Callback<List<GetBooks>>() {
            @Override
            public void onResponse(Call<List<GetBooks>> call, Response<List<GetBooks>> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                List<GetBooks> getBooks = response.body();
                adapter.setBooks(getBooks);
            }

            @Override
            public void onFailure(Call<List<GetBooks>> call, Throwable t) {
                Toast.makeText(BooksActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    void initViews() {
        booksRecView = findViewById(R.id.booksRecView);
    }
}