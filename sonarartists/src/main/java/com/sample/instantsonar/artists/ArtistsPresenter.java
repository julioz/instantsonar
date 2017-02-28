package com.sample.instantsonar.artists;

import com.sample.instantsonar.Navigator;
import com.sample.instantsonar.artists.viewmodel.Track;
import com.soundcloud.lightcycle.DefaultSupportFragmentLightCycle;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;
import java.util.List;

class ArtistsPresenter extends DefaultSupportFragmentLightCycle<ArtistsFragment> {

    private Context context;
    private ArtistsView view;
    private ArtistsOperations operations;

    @Inject
    public ArtistsPresenter(ArtistsOperations operations) {
        this.operations = operations;
    }

    @Override
    public void onCreate(ArtistsFragment fragment, Bundle bundle) {
        this.context = fragment.getActivity();
        this.view = fragment;
    }

    @Override
    public void onViewCreated(ArtistsFragment host, View createdView, Bundle savedInstanceState) {
        super.onViewCreated(host, createdView, savedInstanceState);
        view.showLoading();

        operations.artist()
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Consumer<Artist>() {
                      @Override
                      public void accept(Artist artist) throws Exception {
                          view.hideLoading();
                          setArtistOnView(artist);
                      }
                  }, new Consumer<Throwable>() {
                      @Override
                      public void accept(Throwable throwable) throws Exception {
                          view.hideLoading();
                          view.setUserInfo("fail", null);
                          throwable.printStackTrace();
                      }
                  });
    }

    public void onTrackClicked(Track track) {
        Navigator.track(context, track.getId());
    }

    private void setArtistOnView(Artist artist) {
        if (artist.getCity() == null || artist.getCity().trim().isEmpty()) {
            view.hideCity();
        } else {
            view.showCity(artist.getCity());
        }

        view.setUserInfo(artist.getName(), artist.getImageUrl());
        view.setTracks(artist.getTracks());
    }

    @Override
    public void onDestroy(ArtistsFragment fragment) {
        this.view = null;
        this.context = null;
        super.onDestroy(fragment);
    }

    interface ArtistsView {
        void showLoading();

        void hideLoading();

        void setUserInfo(String username, String userImageUrl);

        void setTracks(List<Track> tracks);

        void hideCity();

        void showCity(String city);
    }
}