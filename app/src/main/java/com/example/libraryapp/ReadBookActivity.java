package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ReadBookActivity extends AppCompatActivity {

    private TextView txtReadBookTitle, txtReadDescTitle, txtReadDescription;
    private ImageView imgReadBookCover;
    private RatingBar ratingReadBook;
    private CheckBox checkBoxFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);

        initView();


        Book book = new Book(1, "Carrie", "Stephen King", 300,
                "https://images-na.ssl-images-amazon.com/images/I/51Tfl0+Bn2L._SX324_BO1,204,203,200_.jpg",
                "Carrie is an epistolary horror novel by American author Stephen King",
                "Carrie was his first published novel, released on April 5, 1974, with a " +
                        "first print-run of 30,000 copies.[1] Set primarily in the then-future year of 1979, " +
                        "it revolves around the eponymous Carrie White, an unpopular friendless misfit and bullied " +
                        "high-school girl from an abusive religious household.");
        setData(book);
    }

    private void setData(Book book) {
        txtReadBookTitle.setText(book.getName());
        txtReadDescription.setText(book.getLongDesc());
        //TODO: status
        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(imgReadBookCover);
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