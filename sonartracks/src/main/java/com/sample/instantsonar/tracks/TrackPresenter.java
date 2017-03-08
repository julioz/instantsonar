package com.sample.instantsonar.tracks;

import com.soundcloud.lightcycle.DefaultSupportFragmentLightCycle;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

class TrackPresenter extends DefaultSupportFragmentLightCycle<TrackFragment> {

    private long trackId;

    @Inject
    public TrackPresenter() {
    }

    @Override
    public void onCreate(TrackFragment host, Bundle bundle) {
        super.onCreate(host, bundle);

        this.trackId = host.getTrackId();
    }

    @Override
    public void onViewCreated(TrackFragment host, View view, Bundle savedInstanceState) {
        super.onViewCreated(host, view, savedInstanceState);

        Toast.makeText(host.getActivity(), "Track " + trackId, Toast.LENGTH_SHORT).show();
    }
}
