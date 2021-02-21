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



//        getBooks();
        getBook();

    }

    private void getBooks(){
        Call<List<GetBooks>> call = serverApi.getBooks();
        call.enqueue(new Callback<List<GetBooks>>() {
            @Override
            public void onResponse(Call<List<GetBooks>> call, Response<List<GetBooks>> response) {
                if (!response.isSuccessful()) {
                    txtBookTitle.setText("Code: " + response.code());
                    return;
                }
                Intent intent = getIntent();
                if (null != intent) {
                    int bookId = intent.getIntExtra(BOOK_ID_KEY,  -1);
                    if (bookId != -1) {
                        List<GetBooks> getBooks = response.body();
                        for (GetBooks bookIterator : getBooks) {
                            if (bookIterator.getId() == bookId) {
                                Toast.makeText(BookActivity.this, "id " + bookId, Toast.LENGTH_SHORT).show();
                                if (null != bookIterator) {
                                    txtBookTitle.setText(bookIterator.getTitle());
                                    txtDescription.setText(bookIterator.getDescription());
                                    Glide.with(getApplicationContext())
                                            .asBitmap().load(bookIterator.getCoverUrl())
                                            .into(imgBookCover);

                                    //handleReserveButton(incomingBook);
                                    //handleWishListButton(incomingBook);
                                }
                                return;
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<GetBooks>> call, Throwable t) {
                txtBookTitle.setText(t.getMessage());
            }
        });
    }

    private void getBook() {
        Intent intent = getIntent();
        if (null != intent) {
            int bookId = intent.getIntExtra(BOOK_ID_KEY,  -1);
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

                            //handleReserveButton(incomingBook);
                            //handleWishListButton(incomingBook);
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

    private void handleWishListButton(Book book) {
        ArrayList<Book> wantToReadBooks = Utils.getWantToReadBooks();

        boolean existInWantToReadBooks = false;

        for (Book b : wantToReadBooks) {
            if (b.getId() == book.getId()) {
                existInWantToReadBooks = true;
                break;
            }
        }

        if (existInWantToReadBooks) {
            btnWishlist.setEnabled(false);
        } else {
            btnWishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToWishlist(book)) {
                        Toast.makeText(BookActivity.this, "Book added to WishList", Toast.LENGTH_SHORT).show();
                        btnWishlist.setEnabled(false);
                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleReserveButton(Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getAlreadyReadBooks();
        ArrayList<Book> reservedBooks = Utils.getReservedBooks();

        boolean existInAlreadyReadBooks = false;
        boolean existInReservedBooks = false;

        for (Book b : alreadyReadBooks) {
            if (b.getId() == book.getId()) {
                existInAlreadyReadBooks = true;
                break;
            }
        }

        for (Book b : reservedBooks) {
            if (b.getId() == book.getId()) {
                existInReservedBooks = true;
                break;
            }
        }

        if (existInAlreadyReadBooks || existInReservedBooks) {
            btnReserve.setEnabled(false);
        } else {
            btnReserve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToReserved(book)) {
                        //TODO creaza popup window pentru imprumut
                        Intent intent = new Intent(BookActivity.this, ReservedBooksActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setData(Book book) {

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