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

    public long getId() {
        return id;
    }

    public long getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }

    public String getArtworkUrl() {
        return artworkUrl;
    }

    public long getPlaybackCount() {
        return playbackCount;
    }

    public long getFavoritingsCount() {
        return favoritingsCount;
    }

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
