package com.sample.instantsonar.api;

import com.google.gson.Gson;
import com.sample.instantsonar.base.BuildConfig;
import com.sample.instantsonar.model.User;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.inject.Inject;
import java.io.IOException;
import java.util.concurrent.Callable;

public class UserApi {

    private static final String HOST = "https://api.soundcloud.com/";
    private static final String ENDPOINT = "users/";
    private static final String CLIENT_ID = BuildConfig.CLIENT_ID;
    private static final String CLIENT_ID_QUERY_STRING = "?client_id=" + CLIENT_ID;

    private OkHttpClient okHttpClient;
    private Gson gson;

    @Inject
    public UserApi(OkHttpClient okHttpClient, Gson gson) {
        this.okHttpClient = okHttpClient;
        this.gson = gson;
    }

    private String buildUrl(long userId) {
        return new StringBuilder(HOST)
                .append(ENDPOINT)
                .append(userId)
                .append(CLIENT_ID_QUERY_STRING)
                .toString();
    }

    public Observable<User> getUser(final long userId) {
        return Observable.defer(new Callable<ObservableSource<Response>>() {
            @Override
            public ObservableSource<Response> call() throws Exception {
                try {
                    final String url = buildUrl(userId);
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
}
