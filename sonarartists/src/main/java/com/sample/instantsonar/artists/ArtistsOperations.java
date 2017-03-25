package com.sample.instantsonar.artists;

import static com.sample.instantsonar.base.BuildConfig.USER_ID;

import com.sample.instantsonar.api.UserApi;
import com.sample.instantsonar.model.Track;
import com.sample.instantsonar.model.User;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.BiFunction;

import javax.inject.Inject;
import java.util.List;

public class ArtistsOperations {

    private UserApi userApi;
    private Scheduler scheduler;

    @Inject
    public ArtistsOperations(UserApi userApi, Scheduler scheduler) {
        this.userApi = userApi;
        this.scheduler = scheduler;
    }

    public Observable<Artist> artist() {
        return userApi.getUser(USER_ID)
                      .zipWith(userApi.getUserTracks(USER_ID), new BiFunction<User, List<Track>, Artist>() {
                          @Override
                          public Artist apply(User user, List<Track> tracks) throws Exception {
                              return new Artist(user, tracks);
                          }
                      })
                      .subscribeOn(scheduler);
    }
}
