package com.sample.instantsonar.model;

import com.google.gson.annotations.SerializedName;

public class Track {
    private long id;
    private long duration;
    private String title;
    @SerializedName("artwork_url")
    private String artworkUrl;
    @SerializedName("playback_count")
    private long playbackCount;
    @SerializedName("favoritings_count")
    private long favoritingsCount;

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", duration=" + duration +
                ", title='" + title + '\'' +
                ", artworkUrl='" + artworkUrl + '\'' +
                ", playbackCount=" + playbackCount +
                ", favoritingsCount=" + favoritingsCount +
                '}';
    }
}
