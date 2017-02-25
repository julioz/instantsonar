package com.sample.instantsonar;

import com.sample.instantsonar.base.BuildConfig;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import javax.inject.Inject;

public class UserApi {

    private static final String HOST = "https://api.soundcloud.com/";
    private static final String ENDPOINT = "users/";
    private static final String CLIENT_ID = BuildConfig.CLIENT_ID;
    private static final String CLIENT_ID_QUERY_STRING = "?client_id=" + CLIENT_ID;

    @Inject OkHttpClient okHttpClient;

    @Inject
    public UserApi() {
    }

    private String buildUrl(long userId) {
        return new StringBuilder(HOST)
                .append(ENDPOINT)
                .append(userId)
                .append(CLIENT_ID_QUERY_STRING)
                .toString();
    }

    public void getUser(long userId, Callback callback) {
        final String url = buildUrl(userId);
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
