package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private TextView txtTitle;
    private TextView txtAlreadyRegistered;
    private TextView txtSignIn;
    private EditText txtUsername;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtConfirmPassword;
    private ImageView imgLogo;
    private Button btnRegister;
    private ServerApi serverApi;
    private int status = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://simple-express-server-3nkkx.ondigitalocean.app")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serverApi = retrofit.create(ServerApi.class);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (
                        !txtPassword.getText().toString()
                                .equals(txtConfirmPassword.getText().toString())
                ) {
                    Toast.makeText(RegisterActivity.this, "Password does not match.", Toast.LENGTH_SHORT).show();
                } else{
                    if (
                            !txtUsername.getText().toString().equals("")
                            && !txtEmail.getText().toString().equals("")
                            && !txtPassword.getText().toString().equals("")
                            && !txtConfirmPassword.getText().toString().equals("")
                    ) {
                        dataCheck(
                                txtUsername.getText().toString(),
                                txtEmail.getText().toString(),
                                txtPassword.getText().toString());
                        if (status == 1) {
                            Toast.makeText(RegisterActivity.this, "merge", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, BooksActivity.class);
                            finishAffinity();
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Complete all fields.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void  dataCheck(String username, String email, String password) {
        Call<List<User>> call = serverApi.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                int check = 0;
                List<User> users = response.body();
                for (User user : users) {
                    if (user.getName().equals(username)) {
                        check = 1;
                        break;
                    } else if (user.getEmail().equals(email)) {
                        check = 1;
                        break;
                    }
                }
                if (check == 0) {
                    createAccount(username, email, password);
                    status = 1;
                } else {
                    txtUsername.setText("");
                    txtEmail.setText("");
                    txtPassword.setText("");
                    txtConfirmPassword.setText("");
                    Toast.makeText(RegisterActivity.this, "Username or email already used", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(RegisterActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostRegister> call, Throwable t) {
            }
        });
    }

    void initViews() {
        txtTitle = findViewById(R.id.txtTitle);
        txtAlreadyRegistered = findViewById(R.id.txtAlreadyRegistered);
        txtUsername = findViewById(R.id.txtUsername2);
        txtSignIn = findViewById(R.id.txtSignIn);
        txtEmail = findViewById(R.id.txtEmail2);
        txtPassword = findViewById(R.id.txtPassword2);
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword2);
        imgLogo = findViewById(R.id.imgLogo);
        btnRegister = findViewById(R.id.btnRegister);
    }
}