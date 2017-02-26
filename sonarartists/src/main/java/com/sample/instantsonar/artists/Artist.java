package com.sample.instantsonar.artists;

import com.sample.instantsonar.artists.viewmodel.Track;
import com.sample.instantsonar.model.User; // needs to be entity mapped

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
