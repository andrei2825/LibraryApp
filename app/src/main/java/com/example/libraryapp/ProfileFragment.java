package com.example.libraryapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    PieChart pieChart;
    RecyclerView readBooksRecView;
    private ReadBooksRecViewAdapter adapter;
    private ImageView imgProfile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        imgProfile = view.findViewById(R.id.imgProfileFrag);
        Glide.with(view.getContext())
                .asBitmap().load("https://i.pinimg.com/originals/51/f6/fb/51f6fb256629fc755b8870c801092942.png")
                .into(imgProfile);
        adapter = new ReadBooksRecViewAdapter(view.getContext());
        readBooksRecView = view.findViewById(R.id.readBooksRecViewFrag);
        pieChart = view.findViewById(R.id.piechartFrag);
        pieChart.addPieSlice(
                new PieModel(
                        "Action",
                        25,
                        Color.parseColor("#FF0000")
                )
        );
        pieChart.addPieSlice(
                new PieModel(
                        "Adventure",
                        20,
                        Color.parseColor("#29BF00")
                )
        );
        pieChart.addPieSlice(
                new PieModel(
                        "Fantasy",
                        30,
                        Color.parseColor("#9A03A5")
                )
        );
        pieChart.addPieSlice(
                new PieModel(
                        "Comics",
                        25,
                        Color.parseColor("#A84923")
                )
        );

        readBooksRecView.setAdapter(adapter);
        readBooksRecView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));

        ArrayList<Book> readBooks = new ArrayList<>();
        readBooks.add(new Book(1, "Carrie", "Stephen King", 300,
                "https://images-na.ssl-images-amazon.com/images/I/51Tfl0+Bn2L._SX324_BO1,204,203,200_.jpg",
                "Carrie is an epistolary horror novel by American author Stephen King",
                "Carrie was his first published novel, released on April 5, 1974, with a " +
                        "first print-run of 30,000 copies.[1] Set primarily in the then-future year of 1979, " +
                        "it revolves around the eponymous Carrie White, an unpopular friendless misfit and bullied " +
                        "high-school girl from an abusive religious household."));
        readBooks.add(new Book(1, "Carrie", "Stephen King", 300,
                "https://images-na.ssl-images-amazon.com/images/I/51Tfl0+Bn2L._SX324_BO1,204,203,200_.jpg",
                "Carrie is an epistolary horror novel by American author Stephen King",
                "Carrie was his first published novel, released on April 5, 1974, with a " +
                        "first print-run of 30,000 copies.[1] Set primarily in the then-future year of 1979, " +
                        "it revolves around the eponymous Carrie White, an unpopular friendless misfit and bullied " +
                        "high-school girl from an abusive religious household."));
        readBooks.add(new Book(1, "Carrie", "Stephen King", 300,
                "https://images-na.ssl-images-amazon.com/images/I/51Tfl0+Bn2L._SX324_BO1,204,203,200_.jpg",
                "Carrie is an epistolary horror novel by American author Stephen King",
                "Carrie was his first published novel, released on April 5, 1974, with a " +
                        "first print-run of 30,000 copies.[1] Set primarily in the then-future year of 1979, " +
                        "it revolves around the eponymous Carrie White, an unpopular friendless misfit and bullied " +
                        "high-school girl from an abusive religious household."));
        readBooks.add(new Book(1, "Carrie", "Stephen King", 300,
                "https://images-na.ssl-images-amazon.com/images/I/51Tfl0+Bn2L._SX324_BO1,204,203,200_.jpg",
                "Carrie is an epistolary horror novel by American author Stephen King",
                "Carrie was his first published novel, released on April 5, 1974, with a " +
                        "first print-run of 30,000 copies.[1] Set primarily in the then-future year of 1979, " +
                        "it revolves around the eponymous Carrie White, an unpopular friendless misfit and bullied " +
                        "high-school girl from an abusive religious household."));
        readBooks.add(new Book(1, "Carrie", "Stephen King", 300,
                "https://images-na.ssl-images-amazon.com/images/I/51Tfl0+Bn2L._SX324_BO1,204,203,200_.jpg",
                "Carrie is an epistolary horror novel by American author Stephen King",
                "Carrie was his first published novel, released on April 5, 1974, with a " +
                        "first print-run of 30,000 copies.[1] Set primarily in the then-future year of 1979, " +
                        "it revolves around the eponymous Carrie White, an unpopular friendless misfit and bullied " +
                        "high-school girl from an abusive religious household."));
        readBooks.add(new Book(1, "Carrie", "Stephen King", 300,
                "https://images-na.ssl-images-amazon.com/images/I/51Tfl0+Bn2L._SX324_BO1,204,203,200_.jpg",
                "Carrie is an epistolary horror novel by American author Stephen King",
                "Carrie was his first published novel, released on April 5, 1974, with a " +
                        "first print-run of 30,000 copies.[1] Set primarily in the then-future year of 1979, " +
                        "it revolves around the eponymous Carrie White, an unpopular friendless misfit and bullied " +
                        "high-school girl from an abusive religious household."));
        readBooks.add(new Book(1, "Carrie", "Stephen King", 300,
                "https://images-na.ssl-images-amazon.com/images/I/51Tfl0+Bn2L._SX324_BO1,204,203,200_.jpg",
                "Carrie is an epistolary horror novel by American author Stephen King",
                "Carrie was his first published novel, released on April 5, 1974, with a " +
                        "first print-run of 30,000 copies.[1] Set primarily in the then-future year of 1979, " +
                        "it revolves around the eponymous Carrie White, an unpopular friendless misfit and bullied " +
                        "high-school girl from an abusive religious household."));

        adapter.setReadBooks(readBooks);
        return view;
    }
}
