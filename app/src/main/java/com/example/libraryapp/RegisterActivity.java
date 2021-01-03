package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    private TextView txtTitle;
    private TextView txtAlreadyRegistered;
    private TextView txtSignIn;
    private EditText txtUsername;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtConfirmPassword;
    private ImageView imgLogo;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();

        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
        btnLogin = findViewById(R.id.btnLogin);
    }
}