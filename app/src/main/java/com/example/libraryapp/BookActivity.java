package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BookActivity extends AppCompatActivity {

    private TextView txtBookTitle, txtBookStatus, txtDescTitle, txtDescription;
    private ImageView imgBookCover;
    private FloatingActionButton btnReserve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();

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
        txtBookTitle.setText(book.getName());
        txtDescription.setText(book.getLongDesc());
        //TODO: status
        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(imgBookCover);
    }

    void initViews() {
        txtBookTitle = findViewById(R.id.txtBookTitle);
        txtBookStatus = findViewById(R.id.txtBookStatus);
        txtDescTitle = findViewById(R.id.txtDescTitle);
        txtDescription = findViewById(R.id.txtDescription);
        imgBookCover = findViewById(R.id.imgBookCover);
        btnReserve = findViewById(R.id.btnReserve);
    }
}