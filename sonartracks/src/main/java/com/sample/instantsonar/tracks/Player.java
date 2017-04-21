package com.sample.instantsonar.tracks;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import android.content.Context;
import android.net.Uri;

import javax.inject.Inject;

public class Player {

    private SimpleExoPlayer exoPlayer;

    @Inject
    public Player() {
    }

    public void load(Context context, String url) {
        exoPlayer = ExoPlayerFactory.newSimpleInstance(context, new DefaultTrackSelector(), new DefaultLoadControl());
        exoPlayer.prepare(new ExtractorMediaSource(Uri.parse(url),
                                                   new DefaultHttpDataSourceFactory(Util.getUserAgent(context, "ExoPlayerDemo")),
                                                   new DefaultExtractorsFactory(), null, null));
    }

    public void play() {
        exoPlayer.setPlayWhenReady(true);
    }

    public void destroy() {
        exoPlayer.release();
    }
}
