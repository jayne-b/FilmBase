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

    public String edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seen);

        Button btnAddSeen = findViewById(R.id.btnAddSeen);
        Button btnBackSeen = findViewById(R.id.btnBackSeen);
        Spinner spGenreSeen = findViewById(R.id.spGenreSeen);
        EditText etTitleSeen = findViewById(R.id.etTitleSeen);
        EditText etCommentsSeen = findViewById(R.id.etCommentSeen);
        RatingBar rbRatingsSeen = findViewById(R.id.rbRatingSeen);

        btnAddSeen.setOnClickListener(this);
        btnBackSeen.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.genre_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGenreSeen.setAdapter(adapter);

        Intent intent = getIntent();

        if(intent != null) {
            String id = intent.getStringExtra("id");
            String title = intent.getStringExtra("title");
            String genre = intent.getStringExtra("genre");
            String comments = intent.getStringExtra("comments");
            String ratings = intent.getStringExtra("ratings");
            edit = intent.getStringExtra("edit");
            etTitleSeen.setText(title);
            etCommentsSeen.setText(comments);
            spGenreSeen.setSelection(adapter.getPosition(genre));
            rbRatingsSeen.setNumStars(Integer.valueOf(ratings));

        }

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
                if(edit.matches(edit)) {
                    actions.updateSeen(movies);
                    Toast.makeText(this, "Movie Edited", Toast.LENGTH_LONG).show();
                    Intent intentMain = new Intent(this, MainActivity.class);
                    startActivity(intentMain);
                } else {
                    actions.addSeen(movies);
                    Toast.makeText(this, "Movie Added", Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(getIntent());
                }

                break;
            case R.id.btnBackSeen:
                Intent intentMain = new Intent(this, MainActivity.class);
                startActivity(intentMain);
                break;
        }
    }
}
