package com.example.filmbase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.filmbase.movies.Titles;

public class MainActivity extends AppCompatActivity {
    private Titles titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titles= new Titles("");
        update();

    }
    public void update(){
    TextView tv = findViewById(R.id.Search);
    tv.setText(titles.getMovieName());

    }
    public void clicked(View v){
        titles.getMovieName();
        update();

    }
}
