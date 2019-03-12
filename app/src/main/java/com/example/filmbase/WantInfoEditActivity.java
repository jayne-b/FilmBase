package com.example.filmbase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class WantInfoEditActivity extends AppCompatActivity implements View.OnClickListener {

    public Context context;
    private String id;
    private String title;
    private String genre;
    private String comments;
    private String day;
    private String month;
    private String year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_info_edit);

        Button btnAddWantEdit = findViewById(R.id.btnAddWantEdit);
        Button btnBackWantEdit = findViewById(R.id.btnBackWantEdit);
        EditText etTitleWantEdit = findViewById(R.id.etTitleWantEdit);
        EditText etCommentsWantEdit = findViewById(R.id.etCommentWantEdit);
        EditText etDayWantEdit = findViewById(R.id.etDayWantEdit);
        EditText etYearWantEdit = findViewById(R.id.etYearWantEdit);
        btnAddWantEdit.setOnClickListener(this);
        btnBackWantEdit.setOnClickListener(this);

        Spinner spGenreWantEdit = findViewById(R.id.spGenreWantEdit);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.genre_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGenreWantEdit.setAdapter(adapter);

        Spinner spMonthWantEdit = findViewById(R.id.spMonthWantEdit);
        ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(this, R.array.month_array, android.R.layout.simple_spinner_item);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMonthWantEdit.setAdapter(monthAdapter);

        Intent intent = getIntent();

        if(intent != null) {
            id = intent.getStringExtra("id");
            title = intent.getStringExtra("title");
            genre = intent.getStringExtra("genre");
            comments = intent.getStringExtra("comments");
            day = intent.getStringExtra("day");
            month = intent.getStringExtra("month");
            year = intent.getStringExtra("year");
            etTitleWantEdit.setText(title);
            etCommentsWantEdit.setText(comments);
            spGenreWantEdit.setSelection(adapter.getPosition(genre));
            etDayWantEdit.setText(day);
            spMonthWantEdit.setSelection(adapter.getPosition(month));
            etYearWantEdit.setText(year);
        }
    }

    public void onClick(View view) {
        EditText etTitleWantEdit = findViewById(R.id.etTitleWantEdit);
        EditText etCommentsWantEdit = findViewById(R.id.etCommentWantEdit);
        EditText etDayWantEdit = findViewById(R.id.etDayWantEdit);
        EditText etYearWantEdit = findViewById(R.id.etYearWantEdit);
        Spinner spGenreWantEdit = findViewById(R.id.spGenreWantEdit);
        Spinner spMonthWantEdit = findViewById(R.id.spMonthWantEdit);
        CheckBox cbAddWantEdit = findViewById(R.id.cbAddWantEdit);

        switch (view.getId()) {
            case R.id.btnAddWantEdit:
                MovieActionsWant actions = new MovieActionsWant(getApplicationContext());
                MoviesWant movies = new MoviesWant();
                movies.state = "w";
                movies.title = etTitleWantEdit.getText().toString();
                movies.genre = spGenreWantEdit.getSelectedItem().toString();
                movies.comments = etCommentsWantEdit.getText().toString();
                movies.day = Integer.parseInt(etDayWantEdit.getText().toString());
                movies.month = spMonthWantEdit.getSelectedItem().toString();
                movies.year = Integer.parseInt(etYearWantEdit.getText().toString());
                if(cbAddWantEdit.isChecked()) {
                    Intent intentWant = new Intent(this, SeenActivity.class);
                    intentWant.putExtra("id", id);
                    intentWant.putExtra("title", title);
                    intentWant.putExtra("genre", genre);
                    intentWant.putExtra("comments", comments);
                    intentWant.putExtra("rating", "0");
                    intentWant.putExtra("edit", "edit");
                    startActivity(intentWant);

                } else {
                    actions.updateWant(movies);
                    Toast.makeText(this, "Movie Edited", Toast.LENGTH_LONG).show();
                    Intent intentMain = new Intent(this, MainActivity.class);
                    startActivity(intentMain);
                }

                break;
            case R.id.btnBackWantEdit:
                Intent intentMain = new Intent(this, MainActivity.class);
                startActivity(intentMain);
                break;
        }

    }
}
