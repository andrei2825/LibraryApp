package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class BooksActivity extends AppCompatActivity {

    private RecyclerView booksRecView;
    private BookRecViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        adapter = new BookRecViewAdapter(this);
        initViews();

        booksRecView.setAdapter(adapter);
        booksRecView.setLayoutManager(new GridLayoutManager(this, 2));

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1, "Carrie", "Stephen King", 300,
                "https://images-na.ssl-images-amazon.com/images/I/51Tfl0+Bn2L._SX324_BO1,204,203,200_.jpg",
                "Carrie is an epistolary horror novel by American author Stephen King",
                "Carrie was his first published novel, released on April 5, 1974, with a " +
                        "first print-run of 30,000 copies.[1] Set primarily in the then-future year of 1979, " +
                        "it revolves around the eponymous Carrie White, an unpopular friendless misfit and bullied " +
                        "high-school girl from an abusive religious household who uses her newly discovered " +
                        "telekinetic powers to exact revenge on those who torment her. During the process, " +
                        "she causes one of the worst local disasters the town has ever had. King has commented " +
                        "that he finds the work to be \"raw\" and \"with a surprising power to hurt and horrify.\" " +
                        "It is one of the most frequently banned books in United States schools,[2] because of Carrie’s " +
                        "violence, cursing, underage sex and negative view of religion.[3] Much of the book uses newspaper " +
                        "clippings, magazine articles, letters, and excerpts from books to tell how Carrie destroyed the " +
                        "fictional town of Chamberlain, Maine while exacting revenge on her sadistic classmates and her " +
                        "own mother Margaret."));

        adapter.setBooks(books);


    }

    void initViews() {
        booksRecView = findViewById(R.id.booksRecView);
    }
}