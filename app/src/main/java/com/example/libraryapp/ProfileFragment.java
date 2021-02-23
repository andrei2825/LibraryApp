package com.example.libraryapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {

    PieChart pieChart;
    RecyclerView readBooksRecView;
    private ReadBooksRecViewAdapter adapter;
    private TextView txtUserName;
    private ImageView imgProfile;
    private ServerApi serverApi;
    private int userId;
    private int[] genres = new int[9];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        imgProfile = view.findViewById(R.id.imgProfileFrag);
        txtUserName = view.findViewById(R.id.txtUserName);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://simple-express-server-3nkkx.ondigitalocean.app")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serverApi = retrofit.create(ServerApi.class);

        userId = getArguments().getInt("userId");
        Call<List<User>> call = serverApi.getUser(userId);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(view.getContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<User> user = response.body();
                User actualUser = user.get(0);
                Glide.with(view.getContext())
                        .asBitmap().load(actualUser.getAvatarUrl())
                        .into(imgProfile);
                txtUserName.setText(actualUser.getName());
                loadBooks(userId, view);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(view.getContext(), "Sadge", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void loadBooks(int userId, View view) {

        Call<List<GetBooks>> call = serverApi.getFinishedBooks(userId);
        call.enqueue(new Callback<List<GetBooks>>() {
            @Override
            public void onResponse(Call<List<GetBooks>> call, Response<List<GetBooks>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(view.getContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<GetBooks> finishedBooks = response.body();
                adapter = new ReadBooksRecViewAdapter(view.getContext());
                readBooksRecView = view.findViewById(R.id.readBooksRecViewFrag);
                readBooksRecView.setAdapter(adapter);
                readBooksRecView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
                adapter.setReadBooks(finishedBooks);
                pieChart = view.findViewById(R.id.piechartFrag);
                int j = 0;
                for (GetBooks book : finishedBooks) {
                    switch (book.getType()) {
                        case "Action":
                            genres[0]++;
                            break;
                        case "Adventure":
                            genres[1]++;
                            break;
                        case "Fantasy":
                            genres[2]++;
                            break;
                        case "Horror":
                            genres[3]++;
                            break;
                        case "Romantic":
                            genres[4]++;
                            break;
                        case "Sci-Fi":
                            genres[5]++;
                            break;
                        case "Comedy":
                            genres[6]++;
                            break;
                        case "Politics":
                            genres[7]++;
                            break;
                        case "Fiction":
                            genres[8]++;
                            break;
                    }
                }
                for (int i = 0; i < 9; i++) {
                    if (genres[i] != 0) {
                        switch (i) {
                            case 0:
                                pieChart.addPieSlice(
                                        new PieModel(
                                                "Action",
                                                genres[i],
                                                Color.parseColor("#FF0000")
                                        )
                                );
                                break;
                            case 1:
                                pieChart.addPieSlice(
                                        new PieModel(
                                                "Adventure",
                                                genres[i],
                                                Color.parseColor("#29BF00")
                                        )
                                );
                                break;
                            case 2:
                                pieChart.addPieSlice(
                                        new PieModel(
                                                "Fantasy",
                                                genres[i],
                                                Color.parseColor("#9A03A5")
                                        )
                                );
                                break;
                            case 3:
                                pieChart.addPieSlice(
                                        new PieModel(
                                                "Horror",
                                                genres[i],
                                                Color.parseColor("#532B2B")
                                        )
                                );
                                break;
                            case 4:
                                pieChart.addPieSlice(
                                        new PieModel(
                                                "Romantic",
                                                genres[i],
                                                Color.parseColor("#FF6DCE")
                                        )
                                );
                                break;
                            case 5:
                                pieChart.addPieSlice(
                                        new PieModel(
                                                "Sci-Fi",
                                                genres[i],
                                                Color.parseColor("#4400FF")
                                        )
                                );
                                break;
                            case 6:
                                pieChart.addPieSlice(
                                        new PieModel(
                                                "Comedy",
                                                genres[i],
                                                Color.parseColor("#FFEB3B")
                                        )
                                );
                                break;
                            case 7:
                                pieChart.addPieSlice(
                                        new PieModel(
                                                "00D9FF",
                                                genres[i],
                                                Color.parseColor("#FF0000")
                                        )
                                );
                                break;
                            case 8:
                                pieChart.addPieSlice(
                                        new PieModel(
                                                "Fiction",
                                                genres[i],
                                                Color.parseColor("#830000")
                                        )
                                );
                                break;
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<GetBooks>> call, Throwable t) {
                Toast.makeText(view.getContext(), "Sadge Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
