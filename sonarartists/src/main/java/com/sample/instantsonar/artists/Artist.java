package com.sample.instantsonar.artists;

import com.sample.instantsonar.model.Track;
import com.sample.instantsonar.model.User;

import java.util.List;

public class Artist {
    private final User user;
    private final List<Track> tracks;

    public Artist(User user, List<Track> tracks) {
        this.user = user;
        this.tracks = tracks;
    }

    public String getName() {
        return user.getFullName();
    }

    public String getImageUrl() {
        return user.getAvatarUrl();
    }

    public String getCity() {
        return user.getCity();
    }

    public List<Track> getTracks() {
        return tracks;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "user=" + user +
                ", tracks=" + tracks +
                '}';
    }
}
