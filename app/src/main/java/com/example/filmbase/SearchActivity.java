package com.example.filmbase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

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
        String input = editText.getText().toString().trim();
        SharedPreferences search = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        search.edit().putString("input", input);

        if(output.getCheckedRadioButtonId() == findViewById(R.id.rbSeenSearch).getId()) {
            MovieActionsSeen actions = new MovieActionsSeen(getApplicationContext());
            //ArrayList<String> movieList = actions.getMovieSeenSearch();;
            ArrayAdapter arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, actions.getMovieSeenSearch());
            listView.setAdapter(arrayAdapter);

        }
    }


}
