package com.sample.instantsonar.tracks;

import com.sample.instantsonar.model.Track;
import com.soundcloud.lightcycle.DefaultSupportFragmentLightCycle;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

class TrackPresenter extends DefaultSupportFragmentLightCycle<TrackFragment> {

    private long trackId;
    private TrackOperations operations;

    @Inject
    public TrackPresenter(TrackOperations operations) {
        this.operations = operations;
    }

    @Override
    public void onCreate(TrackFragment host, Bundle bundle) {
        super.onCreate(host, bundle);

        this.trackId = host.getTrackId();
    }

    @Override
    public void onViewCreated(TrackFragment host, View view, Bundle savedInstanceState) {
        super.onViewCreated(host, view, savedInstanceState);

        operations.track(trackId)
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Consumer<Track>() {
                      @Override
                      public void accept(Track track) throws Exception {
                          // TODO
                      }
                  }, new Consumer<Throwable>() {
                      @Override
                      public void accept(Throwable throwable) throws Exception {
                          // TODO
                      }
                  });
    }
}
