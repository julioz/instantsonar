package com.sample.instantsonar.tracks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class TrackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        if (savedInstanceState == null) {
            final long trackId = getTrackId();

            getSupportFragmentManager().beginTransaction()
                                       .replace(R.id.fragment_container, TrackFragment.newInstance(trackId)).commit();
        }
    }

    private long getTrackId() {
        Intent intent = getIntent();
        Uri data = intent.getData();
        return Long.valueOf(data.getLastPathSegment());
    }
}
