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

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";

    private TextView txtBookTitle, txtBookStatus, txtDescTitle, txtDescription;
    private ImageView imgBookCover;
    private FloatingActionButton btnReserve;
    private FloatingActionButton btnWishlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();

        Intent intent = getIntent();
        if (null != intent) {
            int bookId = intent.getIntExtra(BOOK_ID_KEY,  -1);
            if (bookId != -1) {
                Book incomingBook = Utils.getInstance().getBookById(bookId);
                if (null != incomingBook) {
                    setData(incomingBook);

                    handleReserveButton(incomingBook);
                    handleWishListButton(incomingBook);
                }
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
        txtBookTitle.setText(book.getName());
        txtDescription.setText(book.getLongDesc());
        //TODO: status
        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(imgBookCover);
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