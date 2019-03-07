package com.example.filmbase;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WantInfoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_info);

        Button btnEditWantInfo = findViewById(R.id.btnEditWantInfo);
        Button btnDeleteWantInfo = findViewById(R.id.btnDeleteWantInfo);
        Button btnBackWantInfo = findViewById(R.id.btnBackWantInfo);
        btnEditWantInfo.setOnClickListener(this);
        btnDeleteWantInfo.setOnClickListener(this);
        btnBackWantInfo.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnEditWantInfo:
                Intent intentWant = new Intent(this, WantActivity.class);
                startActivity(intentWant);
                break;
            case R.id.btnDeleteWantInfo:

                break;
            case R.id.btnBackWantInfo:
                Intent intentMain = new Intent(this, MainActivity.class);
                startActivity(intentMain);
                break;
        }
    }
}
