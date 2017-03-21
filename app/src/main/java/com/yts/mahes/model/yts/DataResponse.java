package com.yts.mahes.model.yts;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mahes on 13-Feb-17.
 */

public class DataResponse {

    @SerializedName("movies")
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }
}
