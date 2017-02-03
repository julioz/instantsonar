package com.sample.instantsonar.artists;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ArtistsFragment extends Fragment {

    public static ArtistsFragment newInstance() {
        return new ArtistsFragment();
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = new Button(getActivity());
        return view;
    }
}
