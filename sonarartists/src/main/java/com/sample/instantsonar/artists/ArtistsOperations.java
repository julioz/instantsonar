package com.sample.instantsonar.artists;

import static com.sample.instantsonar.base.BuildConfig.USER_ID;

import com.sample.instantsonar.api.UserApi;
import com.sample.instantsonar.artists.viewmodel.TrackEntityMapper;
import com.sample.instantsonar.model.Track;
import com.sample.instantsonar.model.User;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;
import java.util.List;

public class ArtistsOperations {

    private UserApi userApi;

    @Inject
    public ArtistsOperations(UserApi userApi) {
        this.userApi = userApi;
    }

    public Observable<Artist> artist() {
        return userApi.getUser(USER_ID)
                      .zipWith(userApi.getUserTracks(USER_ID), new BiFunction<User, List<Track>, Artist>() {
                          @Override
                          public Artist apply(User user, List<Track> tracks) throws Exception {
                              return new Artist(user, TrackEntityMapper.map(tracks));
                          }
                      })
                      .subscribeOn(Schedulers.newThread());
    }
}
