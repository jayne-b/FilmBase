package com.example.filmbase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static  final String DATABASE_NAME = "movies_db";


    public MovieDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_MOVIE_SEEN = "CREATE TABLE IF NOT EXISTS " + MoviesSeen.TABLE + "("
                + MoviesSeen.KEY_ID + " INTEGER PRIMARY KEY NOT NULL ,"
                + MoviesSeen.KEY_state + " TEXT, "
                + MoviesSeen.KEY_title + " TEXT NOT NULL, "
                + MoviesSeen.KEY_genre + " TEXT, "
                + MoviesSeen.KEY_comments + " TEXT, "
                + MoviesSeen.KEY_ratings + " FLOAT )";


        String CREATE_TABLE_MOVIE_WANT = " CREATE TABLE IF NOT EXISTS " + MoviesWant.TABLE + "("
                + MoviesWant.KEY_ID + " INTEGER PRIMARY KEY NOT NULL,"
                + MoviesWant.KEY_state + " TEXT, "
                + MoviesWant.KEY_title + " TEXT NOT NULL, "
                + MoviesWant.KEY_genre + " TEXT, "
                + MoviesWant.KEY_comments + " TEXT, "
                + MoviesWant.KEY_day + " INTEGER, "
                + MoviesWant.KEY_month + " TEXT, "
                + MoviesWant.KEY_year + " INTEGER )";

        db.execSQL(CREATE_TABLE_MOVIE_SEEN);
        db.execSQL(CREATE_TABLE_MOVIE_WANT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + MoviesSeen.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MoviesWant.TABLE);

        onCreate(db);

    }

}
