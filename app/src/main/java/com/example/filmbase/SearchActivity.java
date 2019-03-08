package com.example.filmbase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    //String movie = findViewById(R.id.etTitleSearch).toString();
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Button btnSearchSearch = findViewById(R.id.btnSearchSearch);
        Button btnBackSearch = findViewById(R.id.btnBackSearch);
        btnSearchSearch.setOnClickListener(this);
        btnBackSearch.setOnClickListener(this);
        context = getApplicationContext();
    }

    public static Context getContextOfApplication(){
        return context;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSearchSearch:
                search();

                break;
            case R.id.btnBackSearch:
                Intent intentMain = new Intent(this, MainActivity.class);
                startActivity(intentMain);
                break;
        }

    }

    private void search() {
        EditText editText = findViewById(R.id.etTitleSearch);
        RadioGroup output = findViewById(R.id.rgSearch);
        ListView listView = findViewById(R.id.lvMoviesSearch);
        Spinner spinner = findViewById(R.id.spGenreSearch);
        String input = editText.getText().toString().trim();
        String genre = spinner.getSelectedItem().toString();
        SharedPreferences search = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = search.edit();
        editor.putString("input", input);
        //editor.putString("genre", genre);
        editor.commit();

        if(output.getCheckedRadioButtonId() == findViewById(R.id.rbSeenSearch).getId()) {
            MovieActionsSeen actions = new MovieActionsSeen(getApplicationContext());
            ArrayList<HashMap<String, String>> movieList = actions.getMovieSeenSearch();
            ListAdapter arrayAdapter = new SimpleAdapter(this,
                    movieList,
                    android.R.layout.simple_list_item_1,
                    new String[] { "title"},
                    new int[] { android.R.id.text1});
            listView.setAdapter(arrayAdapter);
            ((SimpleAdapter) arrayAdapter).notifyDataSetChanged();

        } else if(output.getCheckedRadioButtonId() == findViewById(R.id.rbWantSearch).getId()) {
            MovieActionsWant actions = new MovieActionsWant(getApplicationContext());
            ArrayList<HashMap<String, String>> movieList = actions.getMovieWantSearch();
            ListAdapter arrayAdapter = new SimpleAdapter( this,
                    movieList,
                    android.R.layout.simple_list_item_1,
                    new String[] { "title"},
                    new int[] { android.R.id.text1});
            listView.setAdapter(arrayAdapter);
            ((SimpleAdapter) arrayAdapter).notifyDataSetChanged();

        } else if(output.getCheckedRadioButtonId() == findViewById(R.id.rbAllSearch).getId()) {
            MovieActionsWant actions = new MovieActionsWant(getApplicationContext());
            MovieActionsSeen actionsSeen = new MovieActionsSeen(getApplicationContext());
            ArrayList<HashMap<String, String>> movieListWant = actions.getMovieWantSearch();
            ArrayList<HashMap<String, String>> movieListSeen = actionsSeen.getMovieSeenSearch();
            ArrayList<HashMap<String, String>> movieList = new ArrayList<>();
            movieList.addAll(movieListWant);
            movieList.addAll(movieListSeen);
            ListAdapter arrayAdapter = new SimpleAdapter( this,
                    movieList,
                    android.R.layout.simple_list_item_1,
                    new String[] { "title"},
                    new int[] { android.R.id.text1});
            listView.setAdapter(arrayAdapter);
            ((SimpleAdapter) arrayAdapter).notifyDataSetChanged();

        }
    }
}
