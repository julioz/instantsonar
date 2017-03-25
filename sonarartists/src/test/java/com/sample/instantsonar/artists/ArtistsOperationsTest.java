package com.sample.instantsonar.artists;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.sample.instantsonar.api.UserApi;
import com.sample.instantsonar.model.Track;
import com.sample.instantsonar.model.User;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

public class ArtistsOperationsTest {

    @Mock UserApi userApi;
    private Scheduler scheduler = Schedulers.trampoline();
    private ArtistsOperations operations;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        operations = new ArtistsOperations(userApi, scheduler);
    }

    @Test
    public void emittedArtistUsesUserAndTrackSetFromApis() throws Exception {
        User user = mock(User.class);
        List<Track> tracks = Collections.singletonList(mock(Track.class));
        when(userApi.getUser(anyLong())).thenReturn(Observable.just(user));
        when(userApi.getUserTracks(anyLong())).thenReturn(Observable.just(tracks));

        TestObserver<Artist> testObserver = operations.artist().test();

        testObserver.assertNoErrors();
        List<Artist> artistList = testObserver.values();
        assertThat(artistList.isEmpty(), is(false));
        assertThat(artistList.get(0), is(new Artist(user, tracks)));
    }

}
