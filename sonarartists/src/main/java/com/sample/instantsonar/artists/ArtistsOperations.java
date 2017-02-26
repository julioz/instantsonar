package com.sample.instantsonar.artists;

import static com.sample.instantsonar.base.BuildConfig.USER_ID;

import com.sample.instantsonar.UserApi;
import com.sample.instantsonar.model.User;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;

public class ArtistsOperations {

    private UserApi userApi;

    @Inject
    public ArtistsOperations(UserApi userApi) {
        this.userApi = userApi;
    }

    public Observable<User> user() {
        return userApi.getUser(USER_ID)
                      .subscribeOn(Schedulers.newThread());
    }
}
