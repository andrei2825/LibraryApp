package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";

    private TextView txtBookTitle, txtBookStatus, txtDescTitle, txtDescription;
    private ImageView imgBookCover;
    private FloatingActionButton btnReserve;
    private FloatingActionButton btnWishlist;
    private ServerApi serverApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://simple-express-server-3nkkx.ondigitalocean.app")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serverApi = retrofit.create(ServerApi.class);
        initViews();
        getBook();
    }

    private void manageButtons(int bookId){
        Call<List<GetWishList>> call = serverApi.getWishLists();
        call.enqueue(new Callback<List<GetWishList>>() {
            @Override
            public void onResponse(Call<List<GetWishList>> call, Response<List<GetWishList>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(BookActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                int chack = 0;
                List<GetWishList> wishLists = response.body();
                for (GetWishList wishList : wishLists) {
                    if (wishList.getBookId() == bookId) {
                        btnWishlist.setEnabled(false);
                        chack = 1;
                        break;
                    }
                }
                if (chack == 0) {
                    btnWishlist.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<GetWishList>> call, Throwable t) {
                Toast.makeText(BookActivity.this, "Sadge Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getBook() {
        Intent intent = getIntent();
        if (null != intent) {
            int bookId = intent.getIntExtra(BOOK_ID_KEY,  -1);
            manageButtons(bookId);
            if (bookId != -1) {
                Call<List<GetBooks>> call = serverApi.getBook(bookId);
                call.enqueue(new Callback<List<GetBooks>>() {
                    @Override
                    public void onResponse(Call<List<GetBooks>> call, Response<List<GetBooks>> response) {
                        if (!response.isSuccessful()) {
                            txtBookTitle.setText("Code: " + response.code());
                            return;
                        }

                        List<GetBooks> incomingBook = response.body();
                        if (null != incomingBook) {
                            txtBookTitle.setText(incomingBook.get(0).getTitle());
                            txtDescription.setText(incomingBook.get(0).getDescription());
                            Glide.with(getApplicationContext())
                                    .asBitmap().load(incomingBook.get(0).getCoverUrl())
                                    .into(imgBookCover);

                        }
                    }

                    @Override
                    public void onFailure(Call<List<GetBooks>> call, Throwable t) {
                        txtBookTitle.setText(t.getMessage());
                    }
                });
            }
        }
    }


    void initViews() {
        txtBookTitle = findViewById(R.id.txtReadBookTitle);
        txtBookStatus = findViewById(R.id.txtBookStatus);
        txtDescTitle = findViewById(R.id.txtReadDescTitle);
        txtDescription = findViewById(R.id.txtReadDescription);
        imgBookCover = findViewById(R.id.imgReadBookCover);
        btnReserve = findViewById(R.id.btnReserve);
        btnWishlist = findViewById(R.id.btnWishlist);
    }
}