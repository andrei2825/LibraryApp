package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout txtInputUsernameL;
    private TextInputLayout txtInputPasswordL;
    private TextView txtCreateAccount;
    private ServerApi serverApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://simple-express-server-3nkkx.ondigitalocean.app")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serverApi = retrofit.create(ServerApi.class);
        initViews();

        txtCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterTestActivity.class));
            }
        });
    }

    public void confirmInputL(View v) {
        String usernameInputL = txtInputUsernameL.getEditText().getText().toString().trim();
        String passwordInputL = txtInputPasswordL.getEditText().getText().toString().trim();
        Call<List<User>> call = serverApi.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                int check = 0;
                List<User> users = response.body();
                User user = null;
                for (User userIterator : users) {
                    if (userIterator.getName().equals(usernameInputL) && userIterator.getPassword().equals(passwordInputL)) {
                        user = userIterator;
                        check = 1;
                        break;
                    }
                }
                if (check == 1) {
                    txtInputPasswordL.setError(null);
                    txtInputUsernameL.setError(null);
                    Intent intent = new Intent(MainActivity.this, DrawerActivity.class);
                    intent.putExtra("USER_ID", user.getId());
                    finishAffinity();
                    startActivity(intent);
                } else {
                    txtInputPasswordL.setError("Username or password is incorrect");
                    txtInputUsernameL.setError(" ");
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error :( " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void initViews() {
        txtInputUsernameL = findViewById(R.id.txtInputUsernameL);
        txtInputPasswordL = findViewById(R.id.txtInputPasswordL);
        txtCreateAccount = findViewById(R.id.txtCreateAccount);
    }
}