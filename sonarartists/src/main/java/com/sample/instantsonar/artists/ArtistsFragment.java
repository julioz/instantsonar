package com.sample.instantsonar.artists;

import com.sample.instantsonar.SampleApplication;
import com.soundcloud.lightcycle.LightCycle;
import com.soundcloud.lightcycle.LightCycleSupportFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

public class ArtistsFragment extends LightCycleSupportFragment<ArtistsFragment> implements ArtistsPresenter.ArtistsView {

    @Inject @LightCycle ArtistsPresenter presenter;

    private ViewGroup contentGroup;
    private TextView textView;
    private ProgressBar progressBar;

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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        findViews(view);
        super.onViewCreated(view, savedInstanceState);
    }

    private void findViews(View view) {
        contentGroup = (ViewGroup) view.findViewById(R.id.fragment_artists_content);
        progressBar = (ProgressBar) view.findViewById(R.id.fragment_artists_loading);
        textView = (TextView) view.findViewById(R.id.fragment_artists_textview);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    public void setContent(String content) {
        contentGroup.setVisibility(View.VISIBLE);
        textView.setText(content);
    }
}
