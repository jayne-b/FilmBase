package com.example.filmbase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static  final String DATABASE_NAME = "movies.db";

    private Integer mOpenCounter = 0;
    private static MovieDBHelper instance;
    private static SQLiteOpenHelper MovieDBHelper;
    private SQLiteDatabase mDatabase;

    public MovieDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*public static synchronized MovieDBHelper getInstance() {
        return instance;
    }


    public synchronized SQLiteDatabase openDatabase() {
        mOpenCounter += 1;
        if(mOpenCounter == 1) {
            mDatabase = MovieDBHelper.getWritableDatabase();
        }
        return mDatabase;
    }

    public synchronized void closeDatabase() {
        mOpenCounter -= 1;
        if(mOpenCounter == 0) {
            mDatabase.close();
        }
    } */


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_MOVIE_SEEN = "CREATE TABLE IF NOT EXISTS " + MoviesSeen.TABLE + "("
                + MoviesSeen.KEY_state + " TEXT, "
                + MoviesSeen.KEY_title + " TEXT, "
                + MoviesSeen.KEY_genre + " TEXT, "
                + MoviesSeen.KEY_comments + " TEXT, "
                + MoviesSeen.KEY_ratings + " INTEGER )";

        db.execSQL(CREATE_TABLE_MOVIE_SEEN);

        String CREATE_TABLE_MOVIE_WANT = "CREATE TABLE IF NOT EXISTS " + MoviesWant.TABLE + "("
                + MoviesWant.KEY_state + " TEXT, "
                + MoviesWant.KEY_title + " TEXT, "
                + MoviesWant.KEY_genre + " TEXT, "
                + MoviesWant.KEY_comments + " TEXT, "
                + MoviesWant.KEY_day + " INTEGER, "
                + MoviesWant.KEY_month + " TEXT, "
                + MoviesWant.KEY_year + " INTEGER )";

        db.execSQL(CREATE_TABLE_MOVIE_WANT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + MoviesSeen.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MoviesWant.TABLE);

        onCreate(db);

    }

}
