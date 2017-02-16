package com.sample.instantsonar.artists;

import com.soundcloud.lightcycle.DefaultSupportFragmentLightCycle;

import android.os.Bundle;
import android.util.Log;

class ArtistsPresenter extends DefaultSupportFragmentLightCycle<ArtistsFragment> {

    private static final String TAG = "fragment_LOG";

    public ArtistsPresenter() {}

    @Override
    public void onCreate(ArtistsFragment fragment, Bundle bundle) {
        Log.i(TAG, "Creating fragment:" + fragment);
    }

    @Override
    public void onStart(ArtistsFragment fragment) {
        Log.i(TAG, "Starting fragment:" + fragment);
    }

    @Override
    public void onResume(ArtistsFragment fragment) {
        Log.i(TAG, "Resuming fragment:" + fragment);
    }

    @Override
    public void onPause(ArtistsFragment fragment) {
        Log.i(TAG, "Pausing fragment:" + fragment);
    }

    @Override
    public void onStop(ArtistsFragment fragment) {
        Log.i(TAG, "Stopping fragment:" + fragment);
    }

    @Override
    public void onDestroy(ArtistsFragment fragment) {
        Log.i(TAG, "Destroying fragment:" + fragment);
    }
}