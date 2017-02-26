package com.sample.instantsonar.artists.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class TrackEntityMapper {

    public static Track map(com.sample.instantsonar.model.Track trackEntity) {
        return new Track(trackEntity.getId(),
                         trackEntity.getDuration(),
                         trackEntity.getTitle(),
                         trackEntity.getArtworkUrl(),
                         trackEntity.getPlaybackCount(),
                         trackEntity.getFavoritingsCount());
    }

    public static List<Track> map(List<com.sample.instantsonar.model.Track> trackEntities) {
        List<Track> tracks = new ArrayList<>(trackEntities.size());
        for (com.sample.instantsonar.model.Track trackEntity : trackEntities) {
            tracks.add(map(trackEntity));
        }
        return tracks;
    }
}
