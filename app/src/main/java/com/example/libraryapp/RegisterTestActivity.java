package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterTestActivity extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +
                    "(?=.*[a-z])" +
                    "(?=.*[A-Z])" +
                    "(?=\\S+$)" +
                    ".{6,}" +
                    "$");

    private TextInputLayout txtInputUsername;
    private TextInputLayout txtInputEmail;
    private TextInputLayout txtInputPassword;
    private TextInputLayout txtInputConfirmPassword;
    private TextView txtSignIn;
    private ServerApi serverApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_test);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://simple-express-server-3nkkx.ondigitalocean.app")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serverApi = retrofit.create(ServerApi.class);
        initViews();
        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void confirmInput(View v) {
        String usernameInput = txtInputUsername.getEditText().getText().toString().trim();
        String emailInput = txtInputEmail.getEditText().getText().toString().trim();
        String passwordInput = txtInputPassword.getEditText().getText().toString().trim();
        String confirmPasswordInput = txtInputConfirmPassword.getEditText().getText().toString().trim();
        Call<List<User>> call = serverApi.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(RegisterTestActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<User> users = response.body();
                int check = 0;
                int error = 0;
                if (usernameInput.isEmpty()) {
                    txtInputUsername.setError("Field can't be empty");
                    error = 1;
                } else {
                    for (User user : users) {
                        if (usernameInput.equals(user.getName())) {
                            txtInputUsername.setError("Username already used");
                            error = 1;
                            check = 1;
                            break;
                        }
                    }
                    if (check == 0) {
                        txtInputUsername.setError(null);
                    }
                }
                check = 0;
                if (emailInput.isEmpty()) {
                    txtInputEmail.setError("Field can't be empty");
                    error = 1;
                } else {
                    for (User user : users) {
                        if (emailInput.equals(user.getEmail())) {
                            txtInputEmail.setError("Email already used");
                            error = 1;
                            check = 1;
                            break;
                        }
                    }
                    if (check == 0) {
                        txtInputEmail.setError(null);
                    }
                }
                if (passwordInput.isEmpty()) {
                    txtInputPassword.setError("Field can't be empty");
                    error = 1;
                } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
                    txtInputPassword.setError("Password is too weak");
                    error = 1;
                } else if (!passwordInput.equals(confirmPasswordInput)){
                    txtInputConfirmPassword.setError("Passwords don't match");
                    error = 1;
                } else {
                    txtInputConfirmPassword.setError(null);
                    txtInputPassword.setError(null);
                }

                if (error == 0) {
                    createAccount(usernameInput, emailInput, passwordInput);
                    Intent intent = new Intent(RegisterTestActivity.this, BooksActivity.class);
                    finishAffinity();
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
            }
        });
    }

    private void createAccount(String username, String email, String password) {
        PostRegister postRegister = new PostRegister(username, "https://i.etsystatic.com/14449774/r/il/98b65a/1912380365/il_570xN.1912380365_2xo7.jpg", email, password);
        Call<PostRegister> call = serverApi.createAccount(postRegister);
        call.enqueue(new Callback<PostRegister>() {
            @Override
            public void onResponse(Call<PostRegister> call, Response<PostRegister> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(RegisterTestActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostRegister> call, Throwable t) {
                Toast.makeText(RegisterTestActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initViews() {
        txtInputUsername = findViewById(R.id.txtInputUsername);
        txtInputEmail = findViewById(R.id.txtInputEmail);
        txtInputPassword = findViewById(R.id.txtInputPassword);
        txtInputConfirmPassword = findViewById(R.id.txtInputConfirmPassword);
        txtSignIn = findViewById(R.id.txtSignIn);
    }
}