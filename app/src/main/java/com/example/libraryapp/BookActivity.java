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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();

//        Book book = new Book(1, "Carrie", "Stephen King", 300,
//                "https://images-na.ssl-images-amazon.com/images/I/51Tfl0+Bn2L._SX324_BO1,204,203,200_.jpg",
//                "Carrie is an epistolary horror novel by American author Stephen King",
//                "Carrie was his first published novel, released on April 5, 1974, with a " +
//                        "first print-run of 30,000 copies.[1] Set primarily in the then-future year of 1979, " +
//                        "it revolves around the eponymous Carrie White, an unpopular friendless misfit and bullied " +
//                        "high-school girl from an abusive religious household.");

        Intent intent = getIntent();
        if (null != intent) {
            int bookId = intent.getIntExtra(BOOK_ID_KEY,  -1);
            if (bookId != -1) {
                Book incomingBook = Utils.getInstance().getBookById(bookId);
                if (null != incomingBook) {
                    setData(incomingBook);

                    handleAlreadyRead(incomingBook);
                }
            }
        }
    }

    private void handleAlreadyRead(Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance().getAlreadyReadBooks();

        boolean existInAlreadyReadBooks = false;

        for (Book b : alreadyReadBooks) {
            if (b.getId() == book.getId()) {
                existInAlreadyReadBooks = true;
            }
        }

        if (existInAlreadyReadBooks == true) {
            btnReserve.setEnabled(false);
        } else {
            btnReserve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToAlreadyRead(book)) {
                        //TODO creaza popup window pentru imprumut
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
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
    }
}