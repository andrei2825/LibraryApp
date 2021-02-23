package com.example.libraryapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BooksFragment extends Fragment {

    private RecyclerView booksRecViewFr;
    private BookRecViewAdapter adapterFr;
    private ServerApi serverApi;
    private EditText searchView;
    CharSequence search="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_books, container, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://simple-express-server-3nkkx.ondigitalocean.app")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serverApi = retrofit.create(ServerApi.class);

        booksRecViewFr = view.findViewById(R.id.booksRecViewFr);
        searchView = view.findViewById(R.id.txtSearchBarFr);
        booksRecViewFr.setHasFixedSize(true);
        booksRecViewFr.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        adapterFr = new BookRecViewAdapter(view.getContext());
        booksRecViewFr.setAdapter(adapterFr);


        Call<List<GetBooks>> call = serverApi.getBooks();
        call.enqueue(new Callback<List<GetBooks>>() {
            @Override
            public void onResponse(Call<List<GetBooks>> call, Response<List<GetBooks>> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                List<GetBooks> getBooks = response.body();
                adapterFr.setBooks(getBooks);
            }

            @Override
            public void onFailure(Call<List<GetBooks>> call, Throwable t) {

            }
        });


        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                adapterFr.getFilter().filter(s);
                search = s;

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }
}
