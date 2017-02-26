package com.sample.instantsonar.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sample.instantsonar.base.BuildConfig;
import com.sample.instantsonar.model.Track;
import com.sample.instantsonar.model.User;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.inject.Inject;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Callable;

public class UserApi {

    private static final String SEPARATOR = "/";
    private static final String HOST = "https://api.soundcloud.com" + SEPARATOR;
    private static final String USERS_RESOURCE = "users";
    private static final String TRACKS_RESOURCE = "tracks";
    private static final String CLIENT_ID = BuildConfig.CLIENT_ID;
    private static final String CLIENT_ID_QUERY_STRING = "?client_id=" + CLIENT_ID;

    private OkHttpClient okHttpClient;
    private Gson gson;

    @Inject
    public UserApi(OkHttpClient okHttpClient, Gson gson) {
        this.okHttpClient = okHttpClient;
        this.gson = gson;
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

    private <ReturnType> Observable<ReturnType> request(final String url, final Type typeOfT) {
        return Observable.defer(new Callable<ObservableSource<Response>>() {
            @Override
            public ObservableSource<Response> call() throws Exception {
                try {
                    Request request = new Request.Builder().url(url).build();
                    Response response = okHttpClient.newCall(request).execute();
                    return Observable.just(response);
                } catch (IOException e) {
                    return Observable.error(e);
                }
            }
        }).flatMap(new Function<Response, ObservableSource<ReturnType>>() {
            @Override
            public ObservableSource<ReturnType> apply(Response response) throws Exception {
                ReturnType parsed = gson.fromJson(response.body().charStream(), typeOfT);
                return Observable.just(parsed);
            }
        });
    }
}
