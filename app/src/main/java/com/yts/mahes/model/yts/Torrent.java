package com.yts.mahes.model.yts;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mahes on 13-Feb-17.
 */

public class Torrent {

    @SerializedName("url")
    private String url;
    @SerializedName("quality")
    private String quality;
    @SerializedName("size")
    private String size;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
