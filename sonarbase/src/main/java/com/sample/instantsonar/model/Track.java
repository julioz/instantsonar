package com.sample.instantsonar.model;

import com.google.gson.annotations.SerializedName;

public class Track {
    private long id;
    private long duration;
    private String title;
    @SerializedName("artwork_url") private String artworkUrl;
    private String description;
    private String genre;
    @SerializedName("playback_count") private long playbackCount;
    @SerializedName("favoritings_count") private long favoritingsCount;
    @SerializedName("waveform_url") private String waveformUrl;
    @SerializedName("stream_url") private String streamUrl;
    private User user;

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

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre;
    }

    public long getPlaybackCount() {
        return playbackCount;
    }

    public long getFavoritingsCount() {
        return favoritingsCount;
    }

    public String getWaveformUrl() {
        return waveformUrl;
    }

    public String getStreamUrl() {
        return streamUrl;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Track track = (Track) o;

        return id == track.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", duration=" + duration +
                ", title='" + title + '\'' +
                ", artworkUrl='" + artworkUrl + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", playbackCount=" + playbackCount +
                ", favoritingsCount=" + favoritingsCount +
                ", waveformUrl=" + waveformUrl +
                ", streamUrl='" + streamUrl + '\'' +
                ", user=" + user +
                '}';
    }
}
