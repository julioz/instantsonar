package com.sample.instantsonar.tracks;

import com.sample.instantsonar.SampleApplication;
import com.soundcloud.lightcycle.LightCycle;
import com.soundcloud.lightcycle.LightCycleSupportFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

public class TrackFragment extends LightCycleSupportFragment<TrackFragment> implements TrackPresenter.TrackView {

    private static final String EXTRA_TRACK_ID = "track_id";

    @Inject @LightCycle TrackPresenter presenter;

    public static TrackFragment newInstance(long trackId) {
        Bundle b = new Bundle();
        b.putLong(EXTRA_TRACK_ID, trackId);
        TrackFragment fragment = new TrackFragment();
        fragment.setArguments(b);
        return fragment;
    }

    public TrackFragment() {
        DaggerTrackComponent
                .builder()
                .applicationComponent(SampleApplication.getComponent())
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_track, null);
        findViews(view);
        return view;
    }

    private void findViews(View view) {

    }

    public long getTrackId() {
        return getArguments().getLong(EXTRA_TRACK_ID);
    }
}
