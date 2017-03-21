package com.yts.mahes.model.yts;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mahes on 12-Feb-17.
 */

public class MovieResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("status_message")
    private String status_message;
    @SerializedName("data")
    private DataResponse data;
    @SerializedName("movies")
    private List<Movie> movies;
    @SerializedName("genres")
    private List<String> genres;
    @SerializedName("torrents")
    List<Torrent> torrents;

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<Torrent> getTorrents() {
        return torrents;
    }

    public void setTorrents(List<Torrent> torrents) {
        this.torrents = torrents;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    public DataResponse  getData() {
        return data;
    }

    public void setData(DataResponse data) {
        this.data = data;
    }
}
