package com.example.filmbase;

public class MoviesWant {

    public static final String TABLE = "Movie";

    public static final String KEY_ID = "id";
    public static final String KEY_state = "state";
    public static final String KEY_title = "title";
    public static final String KEY_genre = "genre";
    public static final String KEY_comments = "comments";
    public static final String KEY_day = "day";
    public static final String KEY_month = "month";
    public static final String KEY_year = "year";

    public int id;
    public String state;
    public String title;
    public String genre;
    public String comments;
    public int day;
    public String month;
    public int year;

    public MoviesWant(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}

