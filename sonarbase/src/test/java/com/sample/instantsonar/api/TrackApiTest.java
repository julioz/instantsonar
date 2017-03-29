package com.sample.instantsonar.api;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import com.google.gson.Gson;
import com.sample.instantsonar.base.BuildConfig;
import com.sample.instantsonar.model.Track;
import io.reactivex.observers.TestObserver;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TrackApiTest {

    @Mock OkHttpClient okhttp;
    private Gson gson = new Gson();
    @Captor ArgumentCaptor<Request> requestCaptor;

    private TrackApi trackApi;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        trackApi = new TrackApi(okhttp, gson);
    }

    @Test
    public void ensureCorrectUrlIsBuiltForRequest() {
        final long trackId = 3456789L;
        String expectedUrl = "https://api.soundcloud.com/tracks/" + trackId + "?client_id=" + BuildConfig.CLIENT_ID;

        TestObserver<Track> observer = trackApi.getTrack(trackId).test();
        observer.awaitTerminalEvent();

        verify(okhttp).newCall(requestCaptor.capture());
        Request request = requestCaptor.getValue();

        assertThat(request.url().toString(), is(expectedUrl));
    }
}
