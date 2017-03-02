package com.sample.instantsonar.artists;

import com.sample.instantsonar.SampleApplication;
import com.sample.instantsonar.artists.ui.CircleTransform;
import com.sample.instantsonar.model.Track;
import com.soundcloud.lightcycle.LightCycle;
import com.soundcloud.lightcycle.LightCycleSupportFragment;
import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;
import java.util.List;

public class ArtistsFragment extends LightCycleSupportFragment<ArtistsFragment> implements ArtistsPresenter.ArtistsView, View.OnClickListener {

    @Inject @LightCycle ArtistsPresenter presenter;

    private ViewGroup contentGroup;
    private TextView userName;
    private TextView userCity;
    private ImageView userImage;
    private ProgressBar progressBar;
    private RecyclerView tracksRecyclerView;
    private TracksAdapter tracksAdapter;

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
        View view = inflater.inflate(R.layout.fragment_artists, null);
        findViews(view);
        tracksRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onDestroyView() {
        this.tracksAdapter = null;
        super.onDestroyView();
    }

    @Override
    public void onClick(View view) {
        final int adapterPosition = tracksRecyclerView.getChildAdapterPosition(view);
        final Track track = tracksAdapter.getItem(adapterPosition);
        presenter.onTrackClicked(track);
    }

    private void findViews(View view) {
        contentGroup = (ViewGroup) view.findViewById(R.id.fragment_artists_content);
        progressBar = (ProgressBar) view.findViewById(R.id.fragment_artists_loading);
        userName = (TextView) view.findViewById(R.id.fragment_artists_user_name);
        userCity = (TextView) view.findViewById(R.id.fragment_artists_user_city);
        userImage = (ImageView) view.findViewById(R.id.fragment_artists_user_image);
        tracksRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_artists_tracklist);
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

    @Override
    public void setTracks(List<Track> tracks) {
        this.tracksAdapter = new TracksAdapter(tracks, this);
        tracksRecyclerView.setAdapter(tracksAdapter);
    }

    @Override
    public void hideCity() {
        userCity.setVisibility(View.GONE);
    }

    @Override
    public void showCity(String city) {
        userCity.setText(city);
        userCity.setVisibility(View.VISIBLE);
    }
}
