package com.sample.instantsonar.artists;

import com.sample.instantsonar.SampleApplication;
import com.soundcloud.lightcycle.LightCycle;
import com.soundcloud.lightcycle.LightCycleSupportFragment;
import okhttp3.OkHttpClient;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

public class ArtistsFragment extends LightCycleSupportFragment<ArtistsFragment> {

    @Inject @LightCycle ArtistsPresenter presenter;

    @Inject OkHttpClient client;

    public static ArtistsFragment newInstance() {
        return new ArtistsFragment();
    }

    public ArtistsFragment() {
        DaggerArtistsComponent
                .builder()
                .applicationComponent(SampleApplication.getComponent())
                .build()
                .inject(this);
    }

    public static final String TAG = "ArtistsFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artists, null);
        Log.e(TAG, "onCreateView: " + client);
        return view;
    }
}
