package com.sample.instantsonar.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sample.instantsonar.base.BuildConfig;
import com.sample.instantsonar.model.Track;
import com.sample.instantsonar.model.User;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;

import javax.inject.Inject;
import java.util.List;

public class UserApi extends Api {

    private static final String SEPARATOR = "/";
    private static final String HOST = "https://api.soundcloud.com" + SEPARATOR;
    private static final String USERS_RESOURCE = "users";
    private static final String TRACKS_RESOURCE = "tracks";
    private static final String CLIENT_ID = BuildConfig.CLIENT_ID;
    private static final String CLIENT_ID_QUERY_STRING = "?client_id=" + CLIENT_ID;

    @Inject
    public UserApi(OkHttpClient okHttpClient, Gson gson) {
        super(okHttpClient, gson);
    }

    private String baseUrl(final long userId) {
        return HOST + USERS_RESOURCE + SEPARATOR + userId;
    }

    private String userUrl(final long userId) {
        return baseUrl(userId) + CLIENT_ID_QUERY_STRING;
    }

    private String userTracksUrl(final long userId) {
        return baseUrl(userId) + SEPARATOR + TRACKS_RESOURCE + CLIENT_ID_QUERY_STRING;
    }

    public Observable<User> getUser(final long userId) {
        String url = userUrl(userId);
        return request(url, User.class);
    }

    public Observable<List<Track>> getUserTracks(final long userId) {
        String url = userTracksUrl(userId);
        return request(url, new TypeToken<List<Track>>() {
        }.getType());
    }
}
