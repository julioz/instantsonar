package com.sample.instantsonar.api;

import com.google.gson.Gson;
import com.sample.instantsonar.base.BuildConfig;
import com.sample.instantsonar.model.Track;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.util.Log;

import javax.inject.Inject;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.Callable;

public class TrackApi {

    private static final String TAG = "TrackApi";

    private static final String SEPARATOR = "/";
    private static final String HOST = "https://api.soundcloud.com" + SEPARATOR;
    private static final String TRACKS_RESOURCE = "tracks";
    private static final String CLIENT_ID = BuildConfig.CLIENT_ID;
    private static final String CLIENT_ID_QUERY_STRING = "?client_id=" + CLIENT_ID;

    private OkHttpClient okHttpClient;
    private Gson gson;

    @Inject
    public TrackApi(OkHttpClient okHttpClient, Gson gson) {
        this.okHttpClient = okHttpClient;
        this.gson = gson;
    }

    private String baseUrl(final long trackId) {
        return HOST + TRACKS_RESOURCE + SEPARATOR + trackId;
    }

    private String trackUrl(final long trackId) {
        return baseUrl(trackId) + CLIENT_ID_QUERY_STRING;
    }

    public Observable<Track> getTrack(final long trackId) {
        String url = trackUrl(trackId);
        return request(url, Track.class);
    }

    private <ReturnType> Observable<ReturnType> request(final String url, final Type typeOfT) {
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
