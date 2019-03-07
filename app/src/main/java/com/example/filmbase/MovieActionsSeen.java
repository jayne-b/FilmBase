package com.example.filmbase;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class MovieActionsSeen  {
    private MoviesSeen moviedb;

    public MovieActionsSeen() {
        moviedb = new MoviesSeen();
    }


    public int addSeen(MoviesSeen movies) {

        SQLiteDatabase db = MovieDBHelper.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(MoviesSeen.KEY_state, movies.getState());
        values.put(MoviesSeen.KEY_title, movies.getTitle());
        values.put(MoviesSeen.KEY_genre, movies.getGenre());
        values.put(MoviesSeen.KEY_comments, movies.getComments());
        values.put(MoviesSeen.KEY_ratings, movies.getRatings());

        long id = db.insert(MoviesSeen.TABLE, null, values);
        db.close();
        return (int) id;
    }


    public void delete(int id) {

        SQLiteDatabase db = MovieDBHelper.getInstance().openDatabase();
        db.delete(MoviesSeen.TABLE, null, null);
        MovieDBHelper.getInstance().closeDatabase();
    }

    public void updateSeen(MoviesSeen movies) {

        SQLiteDatabase db = MovieDBHelper.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(MoviesSeen.KEY_state, movies.getState());
        values.put(MoviesSeen.KEY_title, movies.getTitle());
        values.put(MoviesSeen.KEY_genre, movies.getGenre());
        values.put(MoviesSeen.KEY_comments, movies.getComments());
        values.put(MoviesSeen.KEY_ratings, movies.getRatings());

        long id = (long) db.update(MoviesSeen.TABLE, values, null, null);
        db.close();
    }


    public List<MoviesSeen> getMovieList() {
        MoviesSeen moviesSeenList = new MoviesSeen();
        List<MoviesSeen> moviesSeenLists = new ArrayList<>();

        SQLiteDatabase db = MovieDBHelper.getInstance().openDatabase();
        String selectQuery = "SELECT TITLE " +
                MoviesSeen.KEY_title + "," +
                MoviesSeen.KEY_state + "," +
                MoviesSeen.KEY_genre + "," +
                MoviesSeen.KEY_comments + "," +
                MoviesSeen.KEY_ratings +
                " FROM " + MoviesSeen.TABLE;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                moviesSeenList = new MoviesSeen();
                moviesSeenList.setTitle(cursor.getString(cursor.getColumnIndex(MoviesSeen.KEY_title)));
                moviesSeenList.setState(cursor.getString(cursor.getColumnIndex(MoviesSeen.KEY_state)));
                moviesSeenList.setComments(cursor.getString(cursor.getColumnIndex(MoviesSeen.KEY_comments)));
                moviesSeenList.setRatings(cursor.getInt(cursor.getColumnIndex(MoviesSeen.KEY_ratings)));
                moviesSeenLists.add(moviesSeenList);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        MovieDBHelper.getInstance().closeDatabase();
        return moviesSeenLists;
    }

    public List<MoviesSeen> getMovieSeenSearch() {
        MoviesSeen moviesSeenList = new MoviesSeen();
        ArrayList<MoviesSeen> moviesSeenLists = new ArrayList<>();
        SharedPreferences input = PreferenceManager.getDefaultSharedPreferences(null);
        String search = input.getString("input", null);

        SQLiteDatabase db = MovieDBHelper.getInstance().openDatabase();
        String searchQuery = "SELECT " +
                MoviesSeen.KEY_title + "," +
                MoviesSeen.KEY_state +
                " FROM " + MoviesSeen.TABLE;


        Cursor cursor = db.rawQuery(searchQuery, new String[] {search, "s"});

        if (cursor.moveToFirst()) {
            do {
                moviesSeenList.setTitle(cursor.getString(cursor.getColumnIndex(MoviesSeen.KEY_title)));
                moviesSeenLists.add(moviesSeenList);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        MovieDBHelper.getInstance().closeDatabase();
        return moviesSeenLists;
    }

}
