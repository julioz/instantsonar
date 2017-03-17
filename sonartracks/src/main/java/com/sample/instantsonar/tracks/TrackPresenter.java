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
    private TrackView view;

    @Inject
    public TrackPresenter(TrackOperations operations) {
        this.operations = operations;
    }

    @Override
    public void onCreate(TrackFragment fragment, Bundle bundle) {
        super.onCreate(fragment, bundle);

        this.trackId = fragment.getTrackId();
        this.view = fragment;
    }

    @Override
    public void onViewCreated(TrackFragment fragment, View view, Bundle savedInstanceState) {
        super.onViewCreated(fragment, view, savedInstanceState);

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

    @Override
    public void onDestroy(TrackFragment fragment) {
        this.view = null;
        super.onDestroy(fragment);
    }

    interface TrackView {

    }
}
