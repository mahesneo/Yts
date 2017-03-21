package com.yts.mahes.model.yts;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mahes on 14-Feb-17.
 */

public class Genres {

    @SerializedName("genres")
    private List<Movie> genres;

    public List<Movie> getGenres() {
        return genres;
    }

    public void setGenres(List<Movie> genres) {
        this.genres = genres;
    }
}
