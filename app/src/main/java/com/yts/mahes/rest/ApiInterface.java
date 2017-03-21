package com.yts.mahes.rest;

import com.yts.mahes.model.yts.DataResponse;
import com.yts.mahes.model.yts.Genres;
import com.yts.mahes.model.yts.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mahes on 12-Feb-17.
 */

public interface ApiInterface {

    @GET("list_movies.json")
    Call<MovieResponse> getListMovie();
    @GET("list_movies.json?")
    Call<MovieResponse> getTrailer(@Query("query_term") String movie);
}
