package com.sample.instantsonar.tracks;

import com.sample.instantsonar.api.TrackApi;
import com.sample.instantsonar.model.Track;
import io.reactivex.Observable;
import io.reactivex.Scheduler;

import javax.inject.Inject;

public class TrackOperations {

    private TrackApi trackApi;
    private Scheduler scheduler;

    @Inject
    public TrackOperations(TrackApi trackApi, Scheduler scheduler) {
        this.trackApi = trackApi;
        this.scheduler = scheduler;
    }

    public Observable<Track> track(long trackId) {
        return trackApi.getTrack(trackId)
                       .subscribeOn(scheduler);
    }
}
