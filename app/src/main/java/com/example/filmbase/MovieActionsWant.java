package com.example.filmbase;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.filmbase.MoviesWant.*;


public class MovieActionsWant {
    //private MoviesWant moviesWant;
    private MovieDBHelper movieDBHelper;


    public MovieActionsWant(Context ctx) {
        //moviedb = new MoviesWant();
        movieDBHelper  = new MovieDBHelper(ctx);

    }

    public int addWant(MoviesWant moviesWant) {

        SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MoviesWant.KEY_state, moviesWant.getState());
        values.put(MoviesWant.KEY_title, moviesWant.getTitle());
        values.put(MoviesWant.KEY_genre, moviesWant.getGenre());
        values.put(MoviesWant.KEY_comments, moviesWant.getComments());
        values.put(MoviesWant.KEY_day, moviesWant.getDay());
        values.put(MoviesWant.KEY_month, moviesWant.getMonth());
        values.put(MoviesWant.KEY_year, moviesWant.getYear());

        long id = db.insert(MoviesWant.TABLE, null, values);
        db.close();
        return (int) id;
    }


    public void delete(int id) {

        SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        db.delete(TABLE, KEY_ID + "= ?", new String[] { String.valueOf(id)});
        db.close();
    }

    public void updateWant(MoviesWant movies) {

        SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ID, movies.getId());
        values.put(KEY_state, movies.getState());
        values.put(KEY_title, movies.getTitle());
        values.put(KEY_genre, movies.getGenre());
        values.put(KEY_comments, movies.getComments());
        values.put(KEY_day, movies.getDay());
        values.put(KEY_month, movies.getMonth());
        values.put(KEY_year, movies.getYear());

        db.update(TABLE, values, KEY_ID + "= ?", new String[] { String.valueOf(movies.id)});
        db.close();
    }
    public ArrayList<HashMap<String, String>> getMovieList() {
        //MoviesWant moviesWantList = new MoviesWant();
        ArrayList<HashMap<String, String>> moviesWantList = new ArrayList<HashMap<String, String>>();

        SQLiteDatabase db = movieDBHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                KEY_ID + "," +
                KEY_title + "," +
                KEY_state + "," +
                KEY_genre + "," +
                KEY_comments + "," +
                KEY_day + "," +
                KEY_month + "," +
                KEY_year +
                " FROM " + TABLE;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> moviesWant = new HashMap<String, String>();
                moviesWant.put("id", cursor.getString(cursor.getColumnIndex(KEY_ID)));
                moviesWant.put("title", cursor.getString(cursor.getColumnIndex(KEY_title)));
                moviesWantList.add(moviesWant);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return moviesWantList;
    }

    public ArrayList<HashMap<String, String>> getMovieWantSearch() {
        ArrayList<HashMap<String, String>> moviesWantList = new ArrayList<HashMap<String, String>>();
        Context applicationContext = SearchActivity.getContextOfApplication();
        SharedPreferences input = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        String search = input.getString("input", null);

        SQLiteDatabase db = movieDBHelper.getReadableDatabase();
        String searchQuery = "SELECT " +
                KEY_ID + "," +
                KEY_state + "," +
                KEY_title +
                " FROM " + TABLE +
                " WHERE " + KEY_title + " = ?" +
                " AND " + KEY_state + " = ?";


        Cursor cursor = db.rawQuery(searchQuery, new String[] {search, "w"});

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> moviesWant = new HashMap<String, String>();
                moviesWant.put("id", cursor.getString(cursor.getColumnIndex(KEY_ID)));
                moviesWant.put("title", cursor.getString(cursor.getColumnIndex(KEY_title)));
                moviesWantList.add(moviesWant);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return moviesWantList;
    }




}


