package com.yts.mahes.yts;

/**
 * Created by mahes on 11-Feb-17.
 */

public class List_Item {

    private String movie;
    private String genre;
    private String year;
    private String duration;

    public List_Item(String movie, String genre, String year, String duration) {
        this.movie = movie;
        this.genre = genre;
        this.year = year;
        this.duration = duration;
    }

    public String getMovie() {
        return movie;
    }

    public String getGenre() {
        return genre;
    }

    public String getYear() {
        return year;
    }

    public String getDuration() {
        return duration;
    }
}
