package com.example.filmbase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

public class SeenActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "";
    public String extra;
    public String id;
    public String wantId;

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
            id = intent.getStringExtra("id");
            String title = intent.getStringExtra("title");
            String genre = intent.getStringExtra("genre");
            String comments = intent.getStringExtra("comments");
            String ratings = intent.getStringExtra("ratings");
            extra = intent.getStringExtra("extra");
            wantId = id;
            etTitleSeen.setText(title);
            etCommentsSeen.setText(comments);
            spGenreSeen.setSelection(adapter.getPosition(genre));
            if( ratings != null) {
                rbRatingsSeen.setRating(Float.parseFloat(ratings));
            }

        }

    }

    public void onClick(View view) {
        EditText etTitleSeen = findViewById(R.id.etTitleSeen);
        EditText etCommentsSeen = findViewById(R.id.etCommentSeen);
        RatingBar rbRatingSeen = findViewById(R.id.rbRatingSeen);
        Spinner spGenreSeen = findViewById(R.id.spGenreSeen);

        switch (view.getId()) {
            case R.id.btnAddSeen:

                if(extra.equals("main")) {
                    MovieActionsSeen actionsNew = new MovieActionsSeen(getApplicationContext());
                    MoviesSeen moviesNew = new MoviesSeen();
                    //movies.id = Integer.parseInt(id);
                    moviesNew.state = "s";
                    moviesNew.title = etTitleSeen.getText().toString();
                    moviesNew.genre = spGenreSeen.getSelectedItem().toString();
                    moviesNew.comments = etCommentsSeen.getText().toString();
                    moviesNew.ratings = rbRatingSeen.getRating();
                    Log.d(TAG, "onClick: ratings = " + moviesNew.ratings);
                    actionsNew.addSeen(moviesNew);
                    Toast.makeText(this, "Movie Added", Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(getIntent());
                } else {
                    MovieActionsSeen actions = new MovieActionsSeen(getApplicationContext());
                    MoviesSeen movies = new MoviesSeen();
                    movies.id = Integer.parseInt(id);
                    movies.state = "s";
                    movies.title = etTitleSeen.getText().toString();
                    movies.genre = spGenreSeen.getSelectedItem().toString();
                    movies.comments = etCommentsSeen.getText().toString();
                    movies.ratings = rbRatingSeen.getRating();
                    Intent intentMain = new Intent(this, MainActivity.class);

                    if (extra.equals("edit")) {
                        actions.updateSeen(movies);
                        Toast.makeText(this, "Movie Edited", Toast.LENGTH_LONG).show();
                        //Intent intentMain = new Intent(this, MainActivity.class);
                        startActivity(intentMain);
                    } else if (extra.equals("want")) {
                        int id = actions.addSeen(movies);
                        Log.d(TAG, "onClick: id = " + id);
                        MovieActionsWant actionsWant = new MovieActionsWant(getApplicationContext());
                        actionsWant.delete(Integer.parseInt(wantId));
                        Toast.makeText(this, "Movie Added", Toast.LENGTH_LONG).show();
                        //Intent intentMenu = new Intent(this, MainActivity.class);
                        finish();
                        startActivity(intentMain);
                    }
                }

                break;
            case R.id.btnBackSeen:
                Intent intentMain = new Intent(this, MainActivity.class);
                startActivity(intentMain);
                break;
        }
    }
}
