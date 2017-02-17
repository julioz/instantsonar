package com.sample.instantsonar.artists;

import com.sample.instantsonar.SampleApplication;
import com.soundcloud.lightcycle.LightCycle;
import com.soundcloud.lightcycle.LightCycleSupportFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

public class ArtistsFragment extends LightCycleSupportFragment<ArtistsFragment> {

    @Inject @LightCycle ArtistsPresenter presenter;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_artists, null);
    }

    public void setContent(String content) {
        ((TextView) getView().findViewById(R.id.fragment_artists_content)).setText(content);
    }
}
