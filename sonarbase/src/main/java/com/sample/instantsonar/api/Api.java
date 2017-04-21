package com.sample.instantsonar.api;

import com.google.gson.Gson;
import com.sample.instantsonar.base.BuildConfig;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.Callable;

abstract class Api {

    private static final String TAG = "Api";
    private static final String CLIENT_ID = BuildConfig.CLIENT_ID;
    private static final String CLIENT_ID_QUERY_STRING_KEY = "?client_id=";

    private OkHttpClient okHttpClient;
    private Gson gson;

    Api(OkHttpClient okHttpClient, Gson gson) {
        this.okHttpClient = okHttpClient;
        this.gson = gson;
    }

    public final String clientIdQueryString() {
        return CLIENT_ID_QUERY_STRING_KEY + CLIENT_ID;
    }

    <ReturnType> Observable<ReturnType> request(final String url, final Type typeOfT) {
        Log.e(TAG, "Performing request to " + url);
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
