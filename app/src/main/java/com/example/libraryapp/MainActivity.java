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

public class MainActivity extends AppCompatActivity {

    private TextView txtTitle;
    private TextView txtNewAccount;
    private TextView txtCreateAccount;
    private EditText txtEmail1;
    private EditText txtPassword1;
    private ImageView imgLogo;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        txtCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (
                        !txtEmail1.getText().toString().equals("")
                        &&!txtPassword1.getText().toString().equals("")
                ) {
                    Intent intent = new Intent(MainActivity.this, BooksActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Email or password missing", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    void initViews() {
        txtTitle = findViewById(R.id.txtTitle);
        txtNewAccount = findViewById(R.id.txtNewAccount);
        txtCreateAccount = findViewById(R.id.txtCreateAccount);
        txtEmail1 = findViewById(R.id.txtEmail1);
        txtPassword1 = findViewById(R.id.txtPassword1);
        imgLogo = findViewById(R.id.imgLogo);
        btnLogin = findViewById(R.id.btnLogin);
    }
}