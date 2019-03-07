package com.example.filmbase;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import static com.example.filmbase.MoviesSeen.*;

public class MovieActionsSeen {
    private MoviesSeen moviedb;
    private MovieDBHelper movieDBHelper;

    public MovieActionsSeen(Context ctx) {
        moviedb = new MoviesSeen();
        movieDBHelper  = new MovieDBHelper(ctx);
    }


    public int addSeen(MoviesSeen movies) {

        SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_state, movies.getState());
        values.put(KEY_title, movies.getTitle());
        values.put(KEY_genre, movies.getGenre());
        values.put(KEY_comments, movies.getComments());
        values.put(KEY_ratings, movies.getRatings());

        long id = db.insert(TABLE, null, values);
        db.close();
        return (int) id;
    }


    public void delete(int id) {

        SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        db.delete(TABLE, null, null);
        db.close();
    }

    public void updateSeen(MoviesSeen movies) {

        SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_state, movies.getState());
        values.put(KEY_title, movies.getTitle());
        values.put(KEY_genre, movies.getGenre());
        values.put(KEY_comments, movies.getComments());
        values.put(KEY_ratings, movies.getRatings());

        long id = (long) db.update(TABLE, values, null, null);
        db.close();
    }


    public List<MoviesSeen> getMovieList() {
        MoviesSeen moviesSeenList = new MoviesSeen();
        List<MoviesSeen> moviesSeenLists = new ArrayList<>();

        SQLiteDatabase db = movieDBHelper.getReadableDatabase();
        String selectQuery = "SELECT TITLE " +
                KEY_title + "," +
                KEY_state + "," +
                KEY_genre + "," +
                KEY_comments + "," +
                KEY_ratings +
                " FROM " + TABLE;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                moviesSeenList = new MoviesSeen();
                moviesSeenList.setTitle(cursor.getString(cursor.getColumnIndex(KEY_title)));
                moviesSeenList.setState(cursor.getString(cursor.getColumnIndex(KEY_state)));
                moviesSeenList.setComments(cursor.getString(cursor.getColumnIndex(KEY_comments)));
                moviesSeenList.setRatings(cursor.getInt(cursor.getColumnIndex(KEY_ratings)));
                moviesSeenLists.add(moviesSeenList);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return moviesSeenLists;
    }

    public ArrayList<MoviesSeen> getMovieSeenSearch() {
        MoviesSeen moviesSeenList = new MoviesSeen();
        ArrayList<MoviesSeen> moviesSeenLists = new ArrayList<>();
        Context applicationContext = SearchActivity.getContextOfApplication();
        SharedPreferences input = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        String search = input.getString("input", null);

        SQLiteDatabase db = movieDBHelper.getReadableDatabase();
        String searchQuery = "SELECT state,title FROM Movie WHERE title =? AND state =?";


        Cursor cursor = db.rawQuery(searchQuery, new String[] {"s", search.trim()});

        if (cursor.moveToFirst()) {
            do {
                moviesSeenList.setTitle(cursor.getString(cursor.getColumnIndex(KEY_title)));
                moviesSeenLists.add(moviesSeenList);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return moviesSeenLists;
    }

}
