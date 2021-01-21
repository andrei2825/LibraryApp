package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class ReservedBooksActivity extends AppCompatActivity {

    private RecyclerView bookRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserved_books);

        bookRecView = findViewById(R.id.borrowedBooksRecView);
        BookRecViewAdapter adapter = new BookRecViewAdapter(this);
        bookRecView.setAdapter(adapter);
        bookRecView.setLayoutManager(new GridLayoutManager(this, 2));


        adapter.setBooks(Utils.getReservedBooks());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DrawerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}