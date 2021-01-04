package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();

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
                        Intent intent = new Intent(RegisterActivity.this, BooksActivity.class);
                        finishAffinity();
                        startActivity(intent);
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