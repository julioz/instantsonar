package com.sample.instantsonar.artists;

import com.sample.instantsonar.SampleApplication;
import okhttp3.OkHttpClient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

public class ArtistsFragment extends Fragment {

    @Inject OkHttpClient client;

    public static ArtistsFragment newInstance() {
        return new ArtistsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((SampleApplication) getActivity().getApplication())
                .getComponent()
                .inject(this);
    }

    private static final String TAG = "ArtistsFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artists, null);
        Log.e(TAG, "onCreateView: " + client);
        return view;
    }
}
