package com.example.filmbase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class SeenInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "" ;
    public Context context;
    private String id;
    private String state;
    private String title;
    private String genre;
    private String comments;
    private Float ratings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seen_info);

        Button btnEditSeenInfo = findViewById(R.id.btnEditSeenInfo);
        Button btnDeleteSeenInfo = findViewById(R.id.btnDeleteSeenInfo);
        Button btnBackSeenInfo = findViewById(R.id.btnBackSeenInfo);
        TextView tvTitle = findViewById(R.id.tvTitleSeenInfo);
        TextView tvGenre = findViewById(R.id.tvGenreSeenInfo);
        TextView tvComments = findViewById(R.id.tvCommentSeenInfo);
        RatingBar tvRatings = findViewById(R.id.rbRatingSeen);
        btnEditSeenInfo.setOnClickListener(this);
        btnDeleteSeenInfo.setOnClickListener(this);
        btnBackSeenInfo.setOnClickListener(this);

        Intent intent = getIntent();

        id = intent.getStringExtra("id");
        state = intent.getStringExtra("state");
        title = intent.getStringExtra("title");
        genre = intent.getStringExtra("genre");
        comments = intent.getStringExtra("comments");
        ratings = intent.getFloatExtra("ratings", 0);

        tvTitle.setText(title);
        tvGenre.setText(genre);
        tvComments.setText(comments);
        tvRatings.setRating(ratings);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnEditSeenInfo:
                Intent intentSeen = new Intent(this, SeenActivity.class);
                intentSeen.putExtra("id", id);
                intentSeen.putExtra("title", title);
                intentSeen.putExtra("genre", genre);
                intentSeen.putExtra("comments", comments);
                intentSeen.putExtra("ratings", ratings);
                intentSeen.putExtra("edit", "edit");
                startActivity(intentSeen);
                break;

             case R.id.btnDeleteSeenInfo:
                MovieActionsSeen actions = new MovieActionsSeen(getApplicationContext());
                Log.d(TAG, "onClick: delete id = " + id);
                actions.delete(Integer.parseInt(id));
                Toast.makeText(this, "Movie Deleted", Toast.LENGTH_LONG).show();
                Intent intentMain = new Intent(this, MainActivity.class);
                startActivity(intentMain);
                break;

            case R.id.btnBackSeenInfo:
                intentMain = new Intent(this, MainActivity.class);
                startActivity(intentMain);
                break;
        }

    }

}
