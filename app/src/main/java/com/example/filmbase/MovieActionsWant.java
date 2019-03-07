package com.example.filmbase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.filmbase.MoviesWant.TABLE;

public class MovieActionsWant {
    private MoviesWant moviedb;

    public MovieActionsWant() {
        moviedb = new MoviesWant();
    }

    public int addWant(MoviesWant movies) {

        SQLiteDatabase db = MovieDBHelper.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(MoviesWant.KEY_state, moviedb.getState());
        values.put(MoviesWant.KEY_title, moviedb.getTitle());
        values.put(MoviesWant.KEY_genre, moviedb.getGenre());
        values.put(MoviesWant.KEY_comments, moviedb.getComments());
        values.put(MoviesWant.KEY_day, moviedb.getDay());
        values.put(MoviesWant.KEY_month, moviedb.getMonth());
        values.put(MoviesWant.KEY_year, moviedb.getYear());

        long id = db.insert(MoviesWant.TABLE, null, values);
        db.close();
        return (int) id;
    }


    public void delete(int id) {

        SQLiteDatabase db = MovieDBHelper.getInstance().openDatabase();
        db.delete(TABLE, null, null);
        db.close();
    }

    public void updateWant(MoviesWant movies) {

        SQLiteDatabase db = MovieDBHelper.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(MoviesWant.KEY_state, movies.getState());
        values.put(MoviesWant.KEY_title, movies.getTitle());
        values.put(MoviesWant.KEY_genre, movies.getGenre());
        values.put(MoviesWant.KEY_comments, movies.getComments());
        values.put(MoviesWant.KEY_day, movies.getDay());
        values.put(MoviesWant.KEY_month, movies.getMonth());
        values.put(MoviesWant.KEY_year, movies.getYear());

        long id = (long) db.update(MoviesWant.TABLE, values, null, null);
        db.close();
    }
    public List<MoviesWant> getMovieList() {
        MoviesWant moviesWantList = new MoviesWant();
        List<MoviesWant> moviesWantLists = new ArrayList<>();

        SQLiteDatabase db = MovieDBHelper.getInstance().openDatabase();
        String selectQuery = "SELECT TITLE " +
                MoviesWant.KEY_title + "," +
                MoviesWant.KEY_state + "," +
                MoviesWant.KEY_genre + "," +
                MoviesWant.KEY_comments + "," +
                MoviesWant.KEY_day + "," +
                MoviesWant.KEY_month + "," +
                MoviesWant.KEY_year +
                " FROM " + MoviesWant.TABLE;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                moviesWantList = new MoviesWant();
                moviesWantList.setTitle(cursor.getString(cursor.getColumnIndex(MoviesWant.KEY_title)));
                moviesWantList.setState(cursor.getString(cursor.getColumnIndex(MoviesWant.KEY_state)));
                moviesWantList.setComments(cursor.getString(cursor.getColumnIndex(MoviesWant.KEY_comments)));
                moviesWantList.setDay(cursor.getInt(cursor.getColumnIndex(MoviesWant.KEY_day)));
                moviesWantList.setMonth(cursor.getString(cursor.getColumnIndex(MoviesWant.KEY_month)));
                moviesWantList.setYear(cursor.getInt(cursor.getColumnIndex(MoviesWant.KEY_year)));
                moviesWantLists.add(moviesWantList);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        MovieDBHelper.getInstance().closeDatabase();
        return moviesWantLists;
    }






}


