package com.example.arslanzeliha.jsonbayrak;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class StartActivity extends AppCompatActivity {

  Button btnbilgi,btnbayrak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btnbilgi = (Button) findViewById(R.id.btn_bilgi);
        btnbayrak = (Button) findViewById(R.id.btn_bayrak);

    }
            public void ButtonClick(View v) {
                if (v.getId() == R.id.btn_bilgi) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else if (v.getId() == R.id.btn_bayrak) {
                    Intent intent = new Intent(getApplicationContext(), AnotherActivity.class);
                    startActivity(intent);
                }
            }


}


