package com.example.aldi.androidkopiku;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import info.hoang8f.widget.FButton;

public class MainActivity extends AppCompatActivity {

    private Button btn_masuk,btn_daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_masuk = (Button)findViewById(R.id.btnMasuk);
        btn_daftar = (Button)findViewById(R.id.btnDaftar);

        btn_masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIn = new Intent(MainActivity.this,SignIn.class);
                startActivity(signIn);
            }
        });

        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp = new Intent(MainActivity.this,SignUp.class);
                startActivity(signUp);
            }
        });
    }
}
