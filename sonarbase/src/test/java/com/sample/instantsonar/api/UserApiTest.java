package com.sample.instantsonar.api;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import com.google.gson.Gson;
import com.sample.instantsonar.base.BuildConfig;
import com.sample.instantsonar.model.Track;
import com.sample.instantsonar.model.User;
import io.reactivex.observers.TestObserver;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class UserApiTest {

    private static final long USER_ID = 234567L;

    @Mock OkHttpClient okhttp;
    private Gson gson = new Gson();
    @Captor ArgumentCaptor<Request> requestCaptor;

    private UserApi userApi;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        userApi = new UserApi(okhttp, gson);
    }

    @Test
    public void ensureCorrectUrlIsBuiltForUserRequest() {
        String expectedUrl = "https://api.soundcloud.com/users/" + USER_ID + "?client_id=" + BuildConfig.CLIENT_ID;

        TestObserver<User> observer = userApi.getUser(USER_ID).test();
        observer.awaitTerminalEvent();

        verifyRequestedUrl(expectedUrl);
    }

    @Test
    public void ensureCorrectUrlIsBuiltForUserTracksRequest() {
        String expectedUrl = "https://api.soundcloud.com/users/" + USER_ID + "/tracks?client_id=" + BuildConfig.CLIENT_ID;

        TestObserver<List<Track>> observer = userApi.getUserTracks(USER_ID).test();
        observer.awaitTerminalEvent();

        verifyRequestedUrl(expectedUrl);
    }

    private void verifyRequestedUrl(String expectedUrl) {
        verify(okhttp).newCall(requestCaptor.capture());
        Request request = requestCaptor.getValue();

        assertThat(request.url().toString(), is(expectedUrl));
    }

}
