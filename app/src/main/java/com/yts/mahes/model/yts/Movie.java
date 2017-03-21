package com.yts.mahes.model.yts;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mahes on 12-Feb-17.
 */

public class Movie  {

    @SerializedName("id")
    private Integer id;
    @SerializedName("imdb_code")
    private String imdb_code;
    @SerializedName("title")
    private String title;
    @SerializedName("title_english")
    private String title_english;
    @SerializedName("title_long")
    private String title_long;
    @SerializedName("slug")
    private String slug;
    @SerializedName("year")
    private Integer year;
    @SerializedName("rating")
    private Double rating;
    @SerializedName("runtime")
    private Integer runtime;
    @SerializedName("background_image_original")
    private String originalImage;
    @SerializedName("medium_cover_image")
    private String mediumImage;
    @SerializedName("genres")
    private List<String> genre;
    @SerializedName("summary")
    private String summary;
    @SerializedName("yt_trailer_code")
    private String yt_trailer_code;
    @SerializedName("mpa_rating")
    private String mpa_rating;
    @SerializedName("torrents")
    List<Torrent> torrents;

    public String getOriginalImage() {
        return originalImage;
    }

    public String getMediumImage() {
        return mediumImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImdb_code() {
        return imdb_code;
    }

    public void setImdb_code(String imdb_code) {
        this.imdb_code = imdb_code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_english() {
        return title_english;
    }

    public void setTitle_english(String title_english) {
        this.title_english = title_english;
    }

    public String getTitle_long() {
        return title_long;
    }

    public void setTitle_long(String title_long) {
        this.title_long = title_long;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getYt_trailer_code() {
        return yt_trailer_code;
    }

    public void setYt_trailer_code(String yt_trailer_code) {
        this.yt_trailer_code = yt_trailer_code;
    }

    public String getMpa_rating() {
        return mpa_rating;
    }

    public void setMpa_rating(String mpa_rating) {
        this.mpa_rating = mpa_rating;
    }

    public List<Torrent> getTorrents() {
        return torrents;
    }

    public void setTorrents(List<Torrent> torrents) {
        this.torrents = torrents;
    }
}
