package com.example.libraryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DrawerActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private MaterialToolbar toolbar;
    private ImageView drawerPicture;
    private int userId;
    private ServerApi serverApi;
    private TextView txtUsernameHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        userId = getIntent().getExtras().getInt("USER_ID", -1);
        Utils.getInstance();
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://simple-express-server-3nkkx.ondigitalocean.app")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        serverApi = retrofit.create(ServerApi.class);
        intiViews();
//        Call<List<User>> call = serverApi.getUser(userId);
//        call.enqueue(new Callback<List<User>>() {
//            @Override
//            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(DrawerActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                List<User> user = response.body();
//                User actualUser = user.get(0);
//                txtUsernameHeader.setText(actualUser.getName());
//                Picasso.get().load(actualUser.getAvatarUrl())
//                        .resize(70,70)
//                        .centerCrop()
//                        .into(drawerPicture);
//            }
//
//            @Override
//            public void onFailure(Call<List<User>> call, Throwable t) {
//                Toast.makeText(DrawerActivity.this, "Error :(", Toast.LENGTH_SHORT).show();
//            }
//        });



        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, new BooksFragment());
        fragmentTransaction.commit();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Bundle b1 = new Bundle();
                        b1.putInt("userId", userId);
                        FragmentTransaction fragmentTransactionHome = getSupportFragmentManager().beginTransaction();
                        BooksFragment booksFragment = new BooksFragment();
                        booksFragment.setArguments(b1);
                        fragmentTransactionHome.replace(R.id.container, booksFragment);
                        fragmentTransactionHome.commit();
                        Toast.makeText(DrawerActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.profile:
                        Bundle b2 = new Bundle();
                        b2.putInt("userId", userId);
                        FragmentTransaction fragmentTransactionProfile = getSupportFragmentManager().beginTransaction();
                        ProfileFragment profile = new ProfileFragment();
                        profile.setArguments(b2);
                        fragmentTransactionProfile.replace(R.id.container, profile);
                        fragmentTransactionProfile.commit();
                        Toast.makeText(DrawerActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });



    }

    private void intiViews() {
        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);
        drawerPicture = findViewById(R.id.drawerPicture);
        txtUsernameHeader = findViewById(R.id.txtUsernameHeader);
    }
}