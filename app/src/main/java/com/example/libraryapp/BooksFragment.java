package com.example.libraryapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BooksFragment extends Fragment {

    private RecyclerView booksRecViewFr;
    private BookRecViewAdapter adapterFr;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_books, container, false);
        booksRecViewFr = view.findViewById(R.id.booksRecViewFr);
        booksRecViewFr.setHasFixedSize(true);
        booksRecViewFr.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        adapterFr = new BookRecViewAdapter(view.getContext());
        booksRecViewFr.setAdapter(adapterFr);
        adapterFr.setBooks(Utils.getInstance().getAllBooks());
        return view;
    }
}
