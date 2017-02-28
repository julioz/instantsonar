package com.sample.instantsonar.tracks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class TrackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Uri data = intent.getData();
        Toast.makeText(this, "track " + data.getLastPathSegment(), Toast.LENGTH_SHORT).show();
    }
}
