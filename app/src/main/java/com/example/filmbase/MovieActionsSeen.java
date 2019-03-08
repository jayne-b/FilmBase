package com.example.filmbase;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.filmbase.MoviesSeen.KEY_ID;
import static com.example.filmbase.MoviesSeen.KEY_comments;
import static com.example.filmbase.MoviesSeen.KEY_genre;
import static com.example.filmbase.MoviesSeen.KEY_ratings;
import static com.example.filmbase.MoviesSeen.KEY_state;
import static com.example.filmbase.MoviesSeen.KEY_title;

public class MovieActionsSeen {
    //private MoviesSeen moviedb;
    private MovieDBHelper movieDBHelper;

    public MovieActionsSeen(Context ctx) {
        //moviedb = new MoviesSeen();
        movieDBHelper  = new MovieDBHelper(ctx);
    }


    public int addSeen(MoviesSeen moviesSeen) {

        SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MoviesSeen.KEY_state, moviesSeen.getState());
        values.put(MoviesSeen.KEY_title, moviesSeen.getTitle());
        values.put(MoviesSeen.KEY_genre, moviesSeen.getGenre());
        values.put(MoviesSeen.KEY_comments, moviesSeen.getComments());
        values.put(MoviesSeen.KEY_ratings, moviesSeen.getRatings());

        long id = db.insert(MoviesSeen.TABLE, null, values);
        db.close();
        return (int) id;
    }


    public void delete(int id) {

        SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        db.delete(MoviesSeen.TABLE, MoviesSeen.KEY_ID + "= ?", new String[] { String.valueOf(id)});
        db.close();
    }

    public void updateSeen(MoviesSeen movies) {

        SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ID, movies.getId());
        values.put(KEY_state, movies.getState());
        values.put(KEY_title, movies.getTitle());
        values.put(KEY_genre, movies.getGenre());
        values.put(KEY_comments, movies.getComments());
        values.put(KEY_ratings, movies.getRatings());

        db.update(MoviesSeen.TABLE, values, MoviesSeen.KEY_ID + "= ?",new String[] { String.valueOf(movies.id)});
        db.close();
    }


    public ArrayList<HashMap<String, String>> getMovieList() {
        //MoviesSeen moviesSeenList = new MoviesSeen();
        ArrayList<HashMap<String, String>> moviesSeenList = new ArrayList<>();

        SQLiteDatabase db = movieDBHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                MoviesSeen.KEY_ID + "," +
                MoviesSeen.KEY_state + "," +
                MoviesSeen.KEY_title + "," +
                MoviesSeen.KEY_state + "," +
                MoviesSeen.KEY_genre + "," +
                MoviesSeen.KEY_comments + "," +
                MoviesSeen.KEY_ratings +
                " FROM " + MoviesSeen.TABLE;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> moviesSeen = new HashMap<String, String>();
                moviesSeen.put("id", cursor.getString(cursor.getColumnIndex(MoviesSeen.KEY_ID)));
                moviesSeen.put("title", cursor.getString(cursor.getColumnIndex(MoviesSeen.KEY_title)));
                moviesSeenList.add(moviesSeen);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return moviesSeenList;
    }

    public ArrayList<HashMap<String, String>> getMovieSeenSearch() {
        //MoviesSeen moviesSeenList = new MoviesSeen();
        ArrayList<HashMap<String, String>> moviesSeenList = new ArrayList<HashMap<String, String>>();
        Context applicationContext = SearchActivity.getContextOfApplication();
        SharedPreferences input = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        String search = input.getString("input", null);
        //String genre = input.getString("genre", null);

        SQLiteDatabase db = movieDBHelper.getReadableDatabase();
        String searchQuery = "SELECT " +
                MoviesSeen.KEY_ID + "," +
                MoviesSeen.KEY_state + "," +
                MoviesSeen.KEY_title +
                //"," +                 MoviesSeen.KEY_genre +
                //"," +
                //MoviesSeen.KEY_comments + "," +
                //MoviesSeen.KEY_ratings +
                " FROM " + MoviesSeen.TABLE +
                " WHERE " + MoviesSeen.KEY_title + " = ?" +
                " AND " + MoviesSeen.KEY_state + " = ?";
                //+                 " AND " + MoviesSeen.KEY_genre + " = ?";


        Cursor cursor = db.rawQuery(searchQuery, new String[] {search, "s"});

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> moviesSeen = new HashMap<String, String>();
                moviesSeen.put("id", cursor.getString(cursor.getColumnIndex(MoviesSeen.KEY_ID)));
                moviesSeen.put("title", cursor.getString(cursor.getColumnIndex(MoviesSeen.KEY_title)));
                moviesSeenList.add(moviesSeen);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return moviesSeenList;
    }

}
