package com.example.filmbase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class WantActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want);

        Button btnAddWant = findViewById(R.id.btnAddWant);
        Button btnBackWant = findViewById(R.id.btnBackWant);
        btnAddWant.setOnClickListener(this);
        btnBackWant.setOnClickListener(this);

        Spinner spGenreWant = findViewById(R.id.spGenreWant);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.genre_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGenreWant.setAdapter(adapter);

        Spinner spMonthWant = findViewById(R.id.spMonthWant);
        ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(this, R.array.month_array, android.R.layout.simple_spinner_item);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMonthWant.setAdapter(monthAdapter);

    }

    public void onClick(View view) {
        EditText etTitleWant = findViewById(R.id.etTitleWant);
        Spinner spGenreWant = findViewById(R.id.spGenreWant);
        EditText etCommentsWant = findViewById(R.id.etCommentWant);
        EditText etDayWant = findViewById(R.id.etDayWant);
        Spinner spMonthWant = findViewById(R.id.spMonthWant);
        EditText etYearWant = findViewById(R.id.etYearWant);

        switch (view.getId()) {
            case R.id.btnAddWant:
                MovieActionsWant actions = new MovieActionsWant();
                MoviesWant movies = new MoviesWant();
                movies.state = "w";
                movies.title = etTitleWant.getText().toString();
                movies.genre = spGenreWant.getSelectedItem().toString();
                movies.comments = etCommentsWant.getText().toString();
                movies.day = Integer.parseInt(etDayWant.getText().toString());
                movies.month = spMonthWant.getSelectedItem().toString();
                movies.year = Integer.parseInt(etYearWant.getText().toString());
                actions.addWant(movies);
                Toast.makeText(this, "Movie Added", Toast.LENGTH_LONG).show();
                finish();
                startActivity(getIntent());

                break;
            case R.id.btnBackWant:
                Intent intentMain = new Intent(this, MainActivity.class);
                startActivity(intentMain);
                break;
        }

    }
}
