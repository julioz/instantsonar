package com.sample.instantsonar.tracks;

import com.sample.instantsonar.api.TrackApi;
import com.sample.instantsonar.model.Track;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;

public class TrackOperations {

    private TrackApi trackApi;

    @Inject
    public TrackOperations(TrackApi trackApi) {
        this.trackApi = trackApi;
    }

    public Observable<Track> track(long trackId) {
        return trackApi.getTrack(trackId)
                       .subscribeOn(Schedulers.newThread());
    }
}
