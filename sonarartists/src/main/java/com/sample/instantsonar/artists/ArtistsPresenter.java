package com.sample.instantsonar.artists;

import static com.sample.instantsonar.base.BuildConfig.USER_ID;

import com.sample.instantsonar.UserApi;
import com.sample.instantsonar.model.User;
import com.soundcloud.lightcycle.DefaultSupportFragmentLightCycle;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

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

        userApi.getUser(USER_ID)
               .subscribeOn(Schedulers.newThread())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Consumer<User>() {
                   @Override
                   public void accept(User user) throws Exception {
                       fragment.setContent(user.toString());
                   }
               }, new Consumer<Throwable>() {
                   @Override
                   public void accept(Throwable throwable) throws Exception {
                       fragment.setContent("fail");
                       throwable.printStackTrace();
                   }
               });
    }

    @Override
    public void onDestroy(ArtistsFragment fragment) {
        this.fragment = null;
        super.onDestroy(fragment);
    }
}