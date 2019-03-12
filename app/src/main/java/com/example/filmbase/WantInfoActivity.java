package com.example.filmbase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WantInfoActivity extends AppCompatActivity implements View.OnClickListener {

    public Context context;
    private String id;
    private String state;
    private String title;
    private String genre;
    private String comments;
    private String day;
    private String month;
    private String year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_info);

        Button btnEditWantInfo = findViewById(R.id.btnEditWantInfo);
        Button btnDeleteWantInfo = findViewById(R.id.btnDeleteWantInfo);
        Button btnBackWantInfo = findViewById(R.id.btnBackWantInfo);
        TextView tvTitle = findViewById(R.id.tvTitleWantInfo);
        TextView tvGenre = findViewById(R.id.tvGenreWantInfo);
        TextView tvComments = findViewById(R.id.tvCommentWantInfo);
        TextView tvDay = findViewById(R.id.tvDayWantInfo);
        TextView tvMonth = findViewById(R.id.tvMonthWantInfo);
        TextView tvYear = findViewById(R.id.tvYearWantInfo);
        btnEditWantInfo.setOnClickListener(this);
        btnDeleteWantInfo.setOnClickListener(this);
        btnBackWantInfo.setOnClickListener(this);

        Intent intent = getIntent();

        id = intent.getStringExtra("id");
        state = intent.getStringExtra("state");
        title = intent.getStringExtra("title");
        genre = intent.getStringExtra("genre");
        comments = intent.getStringExtra("comments");
        day = intent.getStringExtra("day");
        month = intent.getStringExtra("month");
        year = intent.getStringExtra("year");

        tvTitle.setText(title);
        tvGenre.setText(genre);
        tvComments.setText(comments);
        tvDay.setText(id);
        tvMonth.setText(month);
        tvYear.setText(year);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnEditWantInfo:
                Intent intentWant = new Intent(this, WantInfoEditActivity.class);
                intentWant.putExtra("id", id);
                intentWant.putExtra("title", title);
                intentWant.putExtra("genre", genre);
                intentWant.putExtra("comments", comments);
                intentWant.putExtra("day", day);
                intentWant.putExtra("month", month);
                intentWant.putExtra("year", year);
                startActivity(intentWant);
                break;
            case R.id.btnDeleteWantInfo:
                MovieActionsWant actions = new MovieActionsWant(getApplicationContext());
                actions.delete(Integer.parseInt(id));
                Toast.makeText(this, "Movie Deleted", Toast.LENGTH_LONG).show();
                Intent intentMain = new Intent(this, MainActivity.class);
                startActivity(intentMain);
                break;
            case R.id.btnBackWantInfo:
                intentMain = new Intent(this, MainActivity.class);
                startActivity(intentMain);
                break;
        }
    }
}
