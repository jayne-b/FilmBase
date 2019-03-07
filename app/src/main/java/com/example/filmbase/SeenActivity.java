package com.example.filmbase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

public class SeenActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seen);

        Button btnAddSeen = findViewById(R.id.btnAddSeen);
        Button btnBackSeen = findViewById(R.id.btnBackSeen);
        Spinner spGenreSeen = findViewById(R.id.spGenreSeen);

        btnAddSeen.setOnClickListener(this);
        btnBackSeen.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.genre_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGenreSeen.setAdapter(adapter);

    }

    public void onClick(View view) {
        EditText etTitleSeen = findViewById(R.id.etTitleSeen);
        EditText etCommentsSeen = findViewById(R.id.etCommentSeen);
        RatingBar rbRatingSeen = findViewById(R.id.rbRatingSeen);
        Spinner spGenreSeen = findViewById(R.id.spGenreSeen);

        switch (view.getId()) {
            case R.id.btnAddSeen:
                MovieActionsSeen actions = new MovieActionsSeen(getApplicationContext());
                MoviesSeen movies = new MoviesSeen();
                movies.state = "s";
                movies.title = etTitleSeen.getText().toString();
                movies.genre = spGenreSeen.getSelectedItem().toString();
                movies.comments = etCommentsSeen.getText().toString();
                movies.ratings = rbRatingSeen.getNumStars();
                actions.addSeen(movies);
                Toast.makeText(this, "Movie Added", Toast.LENGTH_LONG).show();
                finish();
                startActivity(getIntent());

                break;
            case R.id.btnBackSeen:
                Intent intentMain = new Intent(this, MainActivity.class);
                startActivity(intentMain);
                break;
        }
    }
}
