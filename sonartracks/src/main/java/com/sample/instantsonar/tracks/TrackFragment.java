package com.sample.instantsonar.tracks;

import com.sample.instantsonar.SampleApplication;
import com.sample.instantsonar.tracks.ui.WaveformView;
import com.soundcloud.lightcycle.LightCycle;
import com.soundcloud.lightcycle.LightCycleSupportFragment;
import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

public class TrackFragment extends LightCycleSupportFragment<TrackFragment> implements TrackPresenter.TrackView {

    private static final String EXTRA_TRACK_ID = "track_id";

    @Inject @LightCycle TrackPresenter presenter;

    private ImageView artworkImage;
    private TextView trackTitle;
    private TextView trackDescription;
    private TextView trackAuthorName;
    private TextView trackGenre;
    private TextView favoriteCount;
    private WaveformView waveform;

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

    public long getTrackId() {
        return getArguments().getLong(EXTRA_TRACK_ID);
    }

    private void findViews(View view) {
        artworkImage = (ImageView) view.findViewById(R.id.fragment_track_artwork);
        waveform = (WaveformView) view.findViewById(R.id.fragment_track_waveform);
        trackTitle = (TextView) view.findViewById(R.id.fragment_track_title);
        trackDescription = (TextView) view.findViewById(R.id.fragment_track_description);
        trackAuthorName = (TextView) view.findViewById(R.id.fragment_track_author_name);
        trackGenre = (TextView) view.findViewById(R.id.fragment_track_genre);
        favoriteCount = (TextView) view.findViewById(R.id.fragment_track_favorite_count);
    }

    @Override
    public void setTitle(String title) {
        trackTitle.setText(title);
    }

    @Override
    public void setDescription(String description) {
        trackDescription.setText(description);
    }

    @Override
    public void setArtwork(String artworkUrl) {
        Picasso.with(getActivity()).load(artworkUrl).into(artworkImage);
    }

    @Override
    public void setCounts(long playbackCount, long favoritingsCount) {
        favoriteCount.setText(String.valueOf(favoritingsCount));
    }

    @Override
    public void setGenre(String genre) {
        trackGenre.setText(genre);
    }

    @Override
    public void setAuthor(String username, String avatarUrl) {
        trackAuthorName.setText(username);
    }

    @Override
    public void setWaveform(String waveformUrl) {
        Picasso.with(getActivity()).load(waveformUrl).into(waveform);
    }

    @Override
    public void showError() {

    }
}
