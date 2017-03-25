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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artist artist = (Artist) o;

        if (user != null ? !user.equals(artist.user) : artist.user != null) return false;
        return tracks != null ? tracks.equals(artist.tracks) : artist.tracks == null;

    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (tracks != null ? tracks.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "user=" + user +
                ", tracks=" + tracks +
                '}';
    }
}
