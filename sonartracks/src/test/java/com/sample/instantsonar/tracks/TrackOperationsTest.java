package com.sample.instantsonar.tracks;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.sample.instantsonar.api.TrackApi;
import com.sample.instantsonar.model.Track;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TrackOperationsTest {

    private static final long TRACK_ID = 23456L;
    private static final Track TRACK = new Track();

    @Mock TrackApi trackApi;
    private TrackOperations operations;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(trackApi.getTrack(TRACK_ID)).thenReturn(Observable.just(TRACK));

        operations = new TrackOperations(trackApi);
    }

    @Test
    public void emittedTrackUsesAccessToApiToFetchCorrectTrackById() throws Exception {
        TestObserver<Track> testObserver = operations.track(TRACK_ID).test();
        testObserver.awaitTerminalEvent();

        testObserver.assertNoErrors();
        testObserver.assertValue(TRACK);
    }

    @Test
    public void streamUrlIsComposedWithClientIdQueryString() {
        final Track track = mock(Track.class);

        String actual = operations.getStreamUrl(track);

        assertTrue(actual.contains(trackApi.clientIdQueryString()));
    }
}
