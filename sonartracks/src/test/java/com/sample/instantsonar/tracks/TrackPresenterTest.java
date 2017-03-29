package com.sample.instantsonar.tracks;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sample.instantsonar.model.Track;
import com.sample.instantsonar.model.User;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Callable;

public class TrackPresenterTest {

    private static final long TRACK_ID = 23456L;

    @Mock Track track;
    @Mock User user;
    @Mock TrackOperations operations;
    @Mock TrackFragment fragment;
    @Mock TrackPresenter.TrackView view;
    private TrackPresenter presenter;

    private String trackTitle = "title";
    private String trackDescription = "description";
    private String trackArtworkUrl = "url1";
    private long trackPlaybackCount = 4567;
    private long trackFavoritingsCount = 9876;
    private String trackGenre = "genre";
    private String trackWaveformUrl = "url2";
    private String username = "username";
    private String userAvatarUrl = "avatar";

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        view = fragment;

        populateMockTrackProperties();
        when(fragment.getTrackId()).thenReturn(TRACK_ID);
        presenter = new TrackPresenter(operations);
        presenter.onCreate(fragment, null);
    }

    private void populateMockTrackProperties() {
        when(track.getId()).thenReturn(TRACK_ID);
        when(track.getTitle()).thenReturn(trackTitle);
        when(track.getDescription()).thenReturn(trackDescription);
        when(track.getArtworkUrl()).thenReturn(trackArtworkUrl);
        when(track.getPlaybackCount()).thenReturn(trackPlaybackCount);
        when(track.getFavoritingsCount()).thenReturn(trackFavoritingsCount);
        when(track.getGenre()).thenReturn(trackGenre);
        when(track.getWaveformUrl()).thenReturn(trackWaveformUrl);
        when(user.getUsername()).thenReturn(username);
        when(user.getAvatarUrl()).thenReturn(userAvatarUrl);
        when(track.getUser()).thenReturn(user);
    }

    @Test
    public void setTrackDataAfterSuccessfulFetch() {
        when(operations.track(TRACK_ID)).thenReturn(Observable.just(track));

        presenter.onViewCreated(fragment, null, null);

        verify(view).setTitle(trackTitle);
        verify(view).setDescription(trackDescription);
        verify(view).setArtwork(trackArtworkUrl);
        verify(view).setCounts(trackPlaybackCount, trackFavoritingsCount);
        verify(view).setGenre(trackGenre);
        verify(view).setWaveform(trackWaveformUrl);
        verify(view).setAuthor(username, userAvatarUrl);
    }

    @Test
    public void showErrorInViewWhenTrackFetchingFails() {
        when(operations.track(TRACK_ID)).thenReturn(Observable.<Track>error(mock(Exception.class)));

        presenter.onViewCreated(fragment, null, null);

        verify(view).showError();
    }

    @After
    public void tearDown() throws Exception {
        RxAndroidPlugins.reset();
    }
}
