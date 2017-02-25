package com.sample.instantsonar.artists;

import static com.sample.instantsonar.base.BuildConfig.USER_ID;

import com.sample.instantsonar.UserApi;
import com.soundcloud.lightcycle.DefaultSupportFragmentLightCycle;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;
import java.io.IOException;

class ArtistsPresenter extends DefaultSupportFragmentLightCycle<ArtistsFragment> {

    private static final String TAG = "ArtistsPresenter";

    private UserApi userApi;

    private ArtistsFragment fragment;

    @Inject
    public ArtistsPresenter(UserApi userApi) {
        this.userApi = userApi;
    }

    @Override
    public void onCreate(ArtistsFragment fragment, Bundle bundle) {
        this.fragment = fragment;
    }

    @Override
    public void onViewCreated(ArtistsFragment host, View view, Bundle savedInstanceState) {
        super.onViewCreated(host, view, savedInstanceState);
        fragment.setContent("Hello World");

        userApi.getUser(USER_ID, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                fragment.setContent("fail");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String body = response.body().string();
                fragment.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fragment.setContent(body);
                    }
                });
            }
        });
    }

    @Override
    public void onDestroy(ArtistsFragment fragment) {
        this.fragment = null;
        super.onDestroy(fragment);
    }
}