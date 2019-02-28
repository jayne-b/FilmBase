package com.example.filmbase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WantActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want);

        Button btnAddWant = findViewById(R.id.btnAddWant);
        Button btnBackWant = findViewById(R.id.btnBackWant);
        btnAddWant.setOnClickListener(this);
        btnBackWant.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddWant:

                break;
            case R.id.btnBackWant:
                Intent intentMain = new Intent(this, MainActivity.class);
                startActivity(intentMain);
                break;
        }

    }
}
