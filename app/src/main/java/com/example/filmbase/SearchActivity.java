package com.example.filmbase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<HashMap<String, String>> movieListWant;
    private ArrayList<HashMap<String, String>> movieListSeen;
    private ArrayList<HashMap<String, String>> movieList;

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
        final ListView listView = findViewById(R.id.lvMoviesSearch);
        final String input = editText.getText().toString().trim();
        ListView lvMoviesSearch = findViewById(R.id.lvMoviesSearch);
        SharedPreferences search = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final SharedPreferences.Editor editor = search.edit();
        editor.putString("input", input);
        //editor.putString("genre", genre);
        editor.commit();
        //lvMoviesSearch.setOnItemClickListener(this);


        if(output.getCheckedRadioButtonId() == findViewById(R.id.rbSeenSearch).getId()) {
            MovieActionsSeen actions = new MovieActionsSeen(getApplicationContext());
            movieListSeen = actions.getMovieSeenSearch();
            movieList = movieListSeen;
            ListAdapter arrayAdapter = new SimpleAdapter(this,
                    movieList,
                    android.R.layout.simple_list_item_1,
                    new String[] { "title"},
                    new int[] { android.R.id.text1});
            listView.setAdapter(arrayAdapter);
            ((SimpleAdapter) arrayAdapter).notifyDataSetChanged();

        } else if(output.getCheckedRadioButtonId() == findViewById(R.id.rbWantSearch).getId()) {
            MovieActionsWant actions = new MovieActionsWant(getApplicationContext());
            movieListWant = actions.getMovieWantSearch();
            movieList = movieListWant;
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
            movieListWant = actions.getMovieWantSearch();
            movieListSeen = actionsSeen.getMovieSeenSearch();
            movieList = new ArrayList<>();
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

      /*  @Override
        public void onItemClick(AdapterView<?> l, View v, int position, long id) {
            //Cursor c = (Cursor)listView.getItemAtPosition(position);
            //String state = c.getString(c.getColumnIndex(MoviesSeen.KEY_state));
        });
    } */

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            HashMap<String, String> result = movieList.get(position);
            String Id = result.get("id");
            String state = result.get("state");
            String title = result.get("title");
            String genre = result.get("genre");
            String comments = result.get("comments");
            if(state.equals("s")) {
                String ratings = result.get("ratings");
                Intent intent = new Intent(getApplicationContext(), SeenInfoActivity.class);
                intent.putExtra("id", Id);
                intent.putExtra("title", title);
                intent.putExtra("genre", genre);
                intent.putExtra("comments", comments);
                intent.putExtra("ratings", ratings);
                startActivity(intent);
            } else if(state.equals("w")) {
                String day = result.get("day");
                String month = result.get("month");
                String year = result.get("year");
                Intent intent = new Intent(getApplicationContext(), WantInfoActivity.class);
                intent.putExtra("id", Id);
                intent.putExtra("title", title);
                intent.putExtra("genre", genre);
                intent.putExtra("comments", comments);
                intent.putExtra("day", day);
                intent.putExtra("month", month);
                intent.putExtra("year", year);
                startActivity(intent);
            }

        }
    });
}}
