package com.sample.instantsonar.artists;

import com.soundcloud.lightcycle.LightCycle;
import com.soundcloud.lightcycle.LightCycleSupportFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ArtistsFragment extends LightCycleSupportFragment<ArtistsFragment> {

    @LightCycle ArtistsPresenter presenter = new ArtistsPresenter();

    public static ArtistsFragment newInstance() {
        return new ArtistsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_artists, null);
    }
}
