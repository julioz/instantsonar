package com.sample.instantsonar.artists.viewmodel;

public class Track {
    private long id;
    private long duration;
    private String title;
    private String artworkUrl;
    private long playbackCount;
    private long favoritingsCount;

    public Track(long id, long duration, String title, String artworkUrl, long playbackCount, long favoritingsCount) {
        this.id = id;
        this.duration = duration;
        this.title = title;
        this.artworkUrl = artworkUrl;
        this.playbackCount = playbackCount;
        this.favoritingsCount = favoritingsCount;
    }

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
}
