package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReadBookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";

    private TextView txtReadBookTitle, txtReadDescTitle, txtReadDescription;
    private ImageView imgReadBookCover;
    private RatingBar ratingReadBook;
    private CheckBox checkBoxFavorite;
    private ServerApi serverApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://simple-express-server-3nkkx.ondigitalocean.app")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serverApi = retrofit.create(ServerApi.class);
        initView();
        getBook();
    }

    private void getBook() {
        Intent intent = getIntent();
        if (null != intent) {
            String bookTitle = intent.getStringExtra(BOOK_ID_KEY);
            if (bookTitle != null) {
                Call<List<GetBooks>> call = serverApi.getBooks();
                call.enqueue(new Callback<List<GetBooks>>() {
                    @Override
                    public void onResponse(Call<List<GetBooks>> call, Response<List<GetBooks>> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(ReadBookActivity.this, "Code:" + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        List<GetBooks> incomingBooks = response.body();
                        if (null != incomingBooks) {
                            for (GetBooks book : incomingBooks) {
                                if (book.getTitle().equals(bookTitle)) {
                                    txtReadBookTitle.setText(book.getTitle());
                                    txtReadDescription.setText(book.getDescription());
                                    Glide.with(getApplicationContext())
                                        .asBitmap().load(book.getCoverUrl())
                                        .into(imgReadBookCover);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<GetBooks>> call, Throwable t) {
                        Toast.makeText(ReadBookActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    void initView() {
        checkBoxFavorite = findViewById(R.id.checkBoxFavorite);
        txtReadBookTitle = findViewById(R.id.txtReadBookTitle);
        txtReadDescription = findViewById(R.id.txtReadDescription);
        txtReadDescTitle = findViewById(R.id.txtReadDescTitle);
        imgReadBookCover = findViewById(R.id.imgReadBookCover);
        ratingReadBook = findViewById(R.id.ratingReadBook);
    }
}