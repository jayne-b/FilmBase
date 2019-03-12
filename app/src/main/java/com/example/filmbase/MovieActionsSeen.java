package com.example.filmbase;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.filmbase.MoviesSeen.TABLE;

public class MovieActionsSeen {
    private static final String TAG = "";
    //private MoviesSeen moviedb;
    private MovieDBHelper movieDBHelper;

    public MovieActionsSeen(Context ctx) {
        //moviedb = new MoviesSeen();
        movieDBHelper  = new MovieDBHelper(ctx);
    }


    public int addSeen(MoviesSeen moviesSeen) {

        SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(MoviesSeen.KEY_ID, moviesSeen.getId());
        values.put(MoviesSeen.KEY_state, moviesSeen.getState());
        values.put(MoviesSeen.KEY_title, moviesSeen.getTitle());
        values.put(MoviesSeen.KEY_genre, moviesSeen.getGenre());
        values.put(MoviesSeen.KEY_comments, moviesSeen.getComments());
        values.put(MoviesSeen.KEY_ratings, moviesSeen.getRatings());

        long id = db.insert(TABLE, null, values);
        db.close();
        return (int) id;
    }


    public void delete(int id) {

        SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        Log.d(TAG, "delete: id = " + id);
        db.delete(MoviesSeen.TABLE, MoviesSeen.KEY_ID + "= ? ", new String[] {String.valueOf((id)) });
        db.close();
    }

    public void updateSeen(MoviesSeen movies) {

        SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MoviesSeen.KEY_ID, movies.getId());
        values.put(MoviesSeen.KEY_state, movies.getState());
        values.put(MoviesSeen.KEY_title, movies.getTitle());
        values.put(MoviesSeen.KEY_genre, movies.getGenre());
        values.put(MoviesSeen.KEY_comments, movies.getComments());
        values.put(MoviesSeen.KEY_ratings, movies.getRatings());

        db.update(MoviesSeen.TABLE, values, MoviesSeen.KEY_ID + "= ?",new String[] { String.valueOf(movies.id)});
        db.close();
    }

    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM " + MoviesSeen.TABLE + " WHERE " + MoviesSeen.KEY_state + " =?";
        SQLiteDatabase db = movieDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, new String[] {"s"});
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


    public MoviesSeen getMovieSeen(int id) {
        //MoviesSeen moviesSeenList = new MoviesSeen();
        MoviesSeen moviesSeenList = new MoviesSeen();

        SQLiteDatabase db = movieDBHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                MoviesSeen.KEY_ID + "," +
                MoviesSeen.KEY_state + "," +
                MoviesSeen.KEY_title + "," +
                MoviesSeen.KEY_genre + "," +
                MoviesSeen.KEY_comments + "," +
                MoviesSeen.KEY_ratings +
                " FROM " + TABLE +
                " WHERE " + MoviesSeen.KEY_ID + " =?";

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(id)});

        if (cursor.moveToFirst()) {
            do {
                moviesSeenList.setTitle(cursor.getString(cursor.getColumnIndex(MoviesSeen.KEY_title)));
                moviesSeenList.setGenre(cursor.getString(cursor.getColumnIndex(MoviesSeen.KEY_genre)));
                moviesSeenList.setComments(cursor.getString(cursor.getColumnIndex(MoviesSeen.KEY_comments)));
                moviesSeenList.setRatings(cursor.getFloat(cursor.getColumnIndex(MoviesSeen.KEY_ratings)));

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
                MoviesSeen.KEY_title + "," +
                MoviesSeen.KEY_genre + "," +
                MoviesSeen.KEY_comments + "," +
                MoviesSeen.KEY_ratings +
                " FROM " + TABLE +
                " WHERE " + MoviesSeen.KEY_title + " = ?" +
                " AND " + MoviesSeen.KEY_state + " = ?";
                //+                 " AND " + MoviesSeen.KEY_genre + " = ?";


        Cursor cursor = db.rawQuery(searchQuery, new String[] {search, "s"});

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> moviesSeen = new HashMap<String, String>();
                moviesSeen.put("id", cursor.getString(cursor.getColumnIndex(MoviesSeen.KEY_ID)));
                moviesSeen.put("title", cursor.getString(cursor.getColumnIndex(MoviesSeen.KEY_title)));
                moviesSeen.put("state", cursor.getString(cursor.getColumnIndex(MoviesSeen.KEY_state)));
                moviesSeen.put("genre", cursor.getString(cursor.getColumnIndex(MoviesSeen.KEY_genre)));
                moviesSeen.put("comments", cursor.getString(cursor.getColumnIndex(MoviesSeen.KEY_comments)));
                moviesSeen.put("ratings", cursor.getString(cursor.getColumnIndex(MoviesSeen.KEY_ratings)));
                moviesSeenList.add(moviesSeen);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return moviesSeenList;
    }

}
