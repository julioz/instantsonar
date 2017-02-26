package com.sample.instantsonar.artists;

import com.sample.instantsonar.SampleApplication;
import com.sample.instantsonar.artists.ui.CircleTransform;
import com.soundcloud.lightcycle.LightCycle;
import com.soundcloud.lightcycle.LightCycleSupportFragment;
import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

public class ArtistsFragment extends LightCycleSupportFragment<ArtistsFragment> implements ArtistsPresenter.ArtistsView {

    @Inject @LightCycle ArtistsPresenter presenter;

    private ViewGroup contentGroup;
    private TextView userName;
    private ImageView userImage;
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
        userName = (TextView) view.findViewById(R.id.fragment_artists_user_name);
        userImage = (ImageView) view.findViewById(R.id.fragment_artists_user_image);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    public void setUserInfo(String username, String userImageUrl) {
        contentGroup.setVisibility(View.VISIBLE);
        userName.setText(username);
        Picasso.with(getActivity()).load(userImageUrl).transform(new CircleTransform()).into(userImage);
    }
}
