package com.sample.instantsonar.api;

import com.google.gson.Gson;
import com.sample.instantsonar.base.BuildConfig;
import com.sample.instantsonar.model.Track;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;

import javax.inject.Inject;

public class TrackApi extends Api {

    private static final String SEPARATOR = "/";
    private static final String HOST = "https://api.soundcloud.com" + SEPARATOR;
    private static final String TRACKS_RESOURCE = "tracks";

    @Inject
    public TrackApi(OkHttpClient okHttpClient, Gson gson) {
        super(okHttpClient, gson);
    }

    private String baseUrl(final long trackId) {
        return HOST + TRACKS_RESOURCE + SEPARATOR + trackId;
    }

    private String trackUrl(final long trackId) {
        return baseUrl(trackId) + clientIdQueryString();
    }

    public Observable<Track> getTrack(final long trackId) {
        String url = trackUrl(trackId);
        return request(url, Track.class);
    }
}
