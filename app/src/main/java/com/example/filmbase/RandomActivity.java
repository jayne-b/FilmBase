package com.example.filmbase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

public class RandomActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        Button btnRepickRandom = findViewById(R.id.btnRepickRandom);
        Button btnBackRandom = findViewById(R.id.btnBackRandom);

        btnRepickRandom.setOnClickListener(this);
        btnBackRandom.setOnClickListener(this);
    }

    public void Randomizer () {
        //do random thingy
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnRepickRandom:
                // do it again
                finish();
                startActivity(getIntent());

                break;
            case R.id.btnBackRandom:
                Intent intentMain = new Intent(this, MainActivity.class);
                startActivity(intentMain);
                break;
        }
    }
}
