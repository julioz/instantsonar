package com.sample.instantsonar.artists;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ArtistsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_artists);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, ArtistsFragment.newInstance()).commit();
        }
    }
}
