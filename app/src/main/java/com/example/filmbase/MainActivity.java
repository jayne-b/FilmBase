package com.example.filmbase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSeenMain = findViewById(R.id.btnSeenMain);
        Button btnWantMain = findViewById(R.id.btnWantMain);
        Button btnSearchMain = findViewById(R.id.btnSearchMain);
        Button btnRandomMain = findViewById(R.id.btnRandomMain);
        btnSeenMain.setOnClickListener(this);
        btnWantMain.setOnClickListener(this);
        btnSearchMain.setOnClickListener(this);
        btnRandomMain.setOnClickListener(this);
    }

    public void onClick (View view) {
        switch (view.getId()){
            case R.id.btnSeenMain:
                Intent intentSeen = new Intent(this, SeenActivity.class);
                intentSeen.putExtra("extra", "main");
                startActivity(intentSeen);
                break;
            case R.id.btnWantMain:
                Intent intentWant = new Intent(this, WantActivity.class);
                startActivity(intentWant);
                break;
            case R.id.btnSearchMain:
                Intent intentSearch = new Intent(this, SearchActivity.class);
                startActivity(intentSearch);
                break;
            case R.id.btnRandomMain:
                Intent intentRandom = new Intent(this, RandomActivity.class);
                startActivity(intentRandom);

                break;

        }

    }
}
