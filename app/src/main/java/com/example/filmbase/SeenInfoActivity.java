package com.example.filmbase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SeenInfoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seen_info);

        Button btnEditSeenInfo = findViewById(R.id.btnEditSeenInfo);
        Button btnDeleteSeenInfo = findViewById(R.id.btnDeleteSeenInfo);
        Button btnBackSeenInfo = findViewById(R.id.btnBackSeenInfo);
        btnEditSeenInfo.setOnClickListener(this);
        btnDeleteSeenInfo.setOnClickListener(this);
        btnBackSeenInfo.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnEditSeenInfo:
                Intent intentSeen = new Intent(this, SeenActivity.class);
                startActivity(intentSeen);
                break;
            case R.id.btnDeleteSeenInfo:

                break;
            case R.id.btnBackSeenInfo:
                Intent intentMain = new Intent(this, MainActivity.class);
                startActivity(intentMain);
                break;
        }

    }
}
