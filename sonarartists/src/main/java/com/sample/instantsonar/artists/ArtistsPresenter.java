package com.sample.instantsonar.artists;

import com.soundcloud.lightcycle.DefaultSupportFragmentLightCycle;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

class ArtistsPresenter extends DefaultSupportFragmentLightCycle<ArtistsFragment> {

    private ArtistsView view;
    private ArtistsOperations operations;

    @Inject
    public ArtistsPresenter(ArtistsOperations operations) {
        this.operations = operations;
    }

    @Override
    public void onCreate(ArtistsFragment fragment, Bundle bundle) {
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
                          view.setContent(artist.toString());
                      }
                  }, new Consumer<Throwable>() {
                      @Override
                      public void accept(Throwable throwable) throws Exception {
                          view.hideLoading();
                          view.setContent("fail");
                          throwable.printStackTrace();
                      }
                  });
    }

    @Override
    public void onDestroy(ArtistsFragment fragment) {
        this.view = null;
        super.onDestroy(fragment);
    }

    interface ArtistsView {
        void showLoading();

        void hideLoading();

        void setContent(String content);
    }
}