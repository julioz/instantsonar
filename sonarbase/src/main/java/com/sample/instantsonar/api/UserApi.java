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

import android.util.Log;

import javax.inject.Inject;
import java.io.IOException;
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
        return Observable.defer(new Callable<ObservableSource<Response>>() {
            @Override
            public ObservableSource<Response> call() throws Exception {
                try {
                    String url = userUrl(userId);
                    Log.d("Julio", url);
                    Request request = new Request.Builder().url(url).build();
                    Response response = okHttpClient.newCall(request).execute();
                    return Observable.just(response);
                } catch (IOException e) {
                    return Observable.error(e);
                }
            }
        }).flatMap(new Function<Response, ObservableSource<User>>() {
            @Override
            public ObservableSource<User> apply(Response response) throws Exception {
                User user = gson.fromJson(response.body().charStream(), User.class);
                return Observable.just(user);
            }
        });
    }

    public Observable<List<Track>> getUserTracks(final long userId) {
        return Observable.defer(new Callable<ObservableSource<Response>>() {
            @Override
            public ObservableSource<Response> call() throws Exception {
                try {
                    String url = userTracksUrl(userId);
                    Request request = new Request.Builder().url(url).build();
                    Response response = okHttpClient.newCall(request).execute();
                    return Observable.just(response);
                } catch (IOException e) {
                    return Observable.error(e);
                }
            }
        }).flatMap(new Function<Response, ObservableSource<List<Track>>>() {
            @Override
            public ObservableSource<List<Track>> apply(Response response) throws Exception {
                List<Track> tracks = gson.fromJson(response.body().charStream(), new TypeToken<List<Track>>() {
                }.getType());
                return Observable.just(tracks);
            }
        });
    }
}
