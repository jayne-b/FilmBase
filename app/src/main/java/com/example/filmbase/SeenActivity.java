package com.example.filmbase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SeenActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seen);

        Button btnAddSeen = findViewById(R.id.btnAddSeen);
        Button btnBackSeen = findViewById(R.id.btnBackSeen);
        btnAddSeen.setOnClickListener(this);
        btnBackSeen.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddSeen:

                break;
            case R.id.btnBackSeen:
                Intent intentMain = new Intent(this, MainActivity.class);
                startActivity(intentMain);
                break;
        }
    }
}
