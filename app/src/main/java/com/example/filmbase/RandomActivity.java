package com.example.filmbase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        Button btnRepickRandom = findViewById(R.id.btnRepickRandom);
        Button btnBackRandom = findViewById(R.id.btnBackRandom);

        btnRepickRandom.setOnClickListener(this);
        btnBackRandom.setOnClickListener(this);

        MovieActionsSeen actions = new MovieActionsSeen(getApplicationContext());
        int random = actions.getProfilesCount();
        int id = Randomizer(random);
        //actions.getMovieSeen(id);

        ArrayList<HashMap<String, String>> movieList = actions.getMovieSeen(id);
        //MoviesSeen movieSeen = new MoviesSeen();
        //movieList.get(0);
        String title = String.valueOf(movieList.get(Integer.parseInt("title")));
        String genre = String.valueOf(movieList.get(Integer.parseInt("genre")));
        String comments = String.valueOf(movieList.get(Integer.parseInt("comments")));
        String ratings = String.valueOf(movieList.get(Integer.parseInt("ratings")));


        TextView tvTitleRandom = findViewById(R.id.tvTitleRandom);
        TextView tvGenreRandom = findViewById(R.id.tvGenreRandom);
        TextView tvCommentsRandom = findViewById(R.id.tvCommentRandom);
        RatingBar rbRatingRandom = findViewById(R.id.rbRatingRandom);

        tvTitleRandom.setText(title);
        tvGenreRandom.setText(genre);
        tvCommentsRandom.setText(comments);
        rbRatingRandom.setRating(Float.parseFloat(ratings));

    }

    public int Randomizer (int random) {
        Random rand = new Random();

        int n = rand.nextInt(random);
        n += 1;
        return n;
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
