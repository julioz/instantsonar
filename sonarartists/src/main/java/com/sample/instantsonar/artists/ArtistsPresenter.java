package com.sample.instantsonar.artists;

import com.soundcloud.lightcycle.DefaultSupportFragmentLightCycle;

import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

class ArtistsPresenter extends DefaultSupportFragmentLightCycle<ArtistsFragment> {

    private static final String TAG = "ArtistsPresenter";

    private ArtistsFragment fragment;

    @Inject
    public ArtistsPresenter() {
    }

    @Override
    public void onCreate(ArtistsFragment fragment, Bundle bundle) {
        this.fragment = fragment;
    }

    @Override
    public void onViewCreated(ArtistsFragment host, View view, Bundle savedInstanceState) {
        super.onViewCreated(host, view, savedInstanceState);
        fragment.setContent("Hello World");
    }

    @Override
    public void onDestroy(ArtistsFragment fragment) {
        this.fragment = null;
        super.onDestroy(fragment);
    }
}