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
    public void onViewCreated(TrackFragment fragment, View createdView, Bundle savedInstanceState) {
        super.onViewCreated(fragment, createdView, savedInstanceState);

        operations.track(trackId)
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Consumer<Track>() {
                      @Override
                      public void accept(Track track) throws Exception {
                          view.setTitle(track.getTitle());
                          view.setDescription(track.getDescription());
                          view.setArtwork(track.getArtworkUrl());
                          view.setCounts(track.getPlaybackCount(), track.getFavoritingsCount());
                          view.setGenre(track.getGenre());
                          view.setWaveform(track.getWaveformUrl());
                          view.setAuthor(track.getUser().getUsername(), track.getUser().getAvatarUrl());
                      }
                  }, new Consumer<Throwable>() {
                      @Override
                      public void accept(Throwable throwable) throws Exception {
                          view.showError();
                      }
                  });
    }

    @Override
    public void onDestroy(TrackFragment fragment) {
        this.view = null;
        super.onDestroy(fragment);
    }

    interface TrackView {

        void setTitle(String title);

        void setDescription(String description);

        void setArtwork(String artworkUrl);

        void setCounts(long playbackCount, long favoritingsCount);

        void setGenre(String genre);

        void setAuthor(String username, String avatarUrl);

        void setWaveform(String waveformUrl);

        void showError();
    }
}
