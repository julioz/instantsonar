package com.sample.instantsonar.artists;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sample.instantsonar.model.Track;
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

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public class ArtistsPresenterTest {

    private String username = "fakeUsername";
    private String imageUrl = "fakeImageUrl";
    private List<Track> tracks = Collections.singletonList(new Track());

    @Mock ArtistsOperations operations;
    @Mock ArtistsFragment fragment;
    @Mock ArtistsPresenter.ArtistsView view;
    @Mock Artist artist;
    private ArtistsPresenter artistsPresenter;

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

        when(artist.getName()).thenReturn(username);
        when(artist.getImageUrl()).thenReturn(imageUrl);
        when(artist.getTracks()).thenReturn(tracks);
        artistsPresenter = new ArtistsPresenter(operations);
        artistsPresenter.onCreate(fragment, null);
    }

    @Test
    public void showLoadingBeforeStartingTheArtistDataRequest() {
        when(operations.artist()).thenReturn(Observable.<Artist>empty());

        onViewCreated();

        verify(view).showLoading();
    }

    @Test
    public void setArtistDataAfterSuccessfulFetch() {
        when(operations.artist()).thenReturn(Observable.just(artist));

        onViewCreated();

        verify(view).setUserInfo(username, imageUrl);
        verify(view).setTracks(tracks);
    }

    @Test
    public void hideCityFromViewIfArtistHasNoCity() {
        when(artist.getCity()).thenReturn(null);
        when(operations.artist()).thenReturn(Observable.just(artist));

        onViewCreated();

        verify(view).hideCity();
    }

    @Test
    public void showCityInViewIfArtistHasCity() {
        String city = "fakeCity";
        when(artist.getCity()).thenReturn(city);
        when(operations.artist()).thenReturn(Observable.just(artist));

        onViewCreated();

        verify(view).showCity(city);
    }

    @Test
    public void setErrorMessageWhenArtistFetchingFails() {
        when(operations.artist()).thenReturn(Observable.<Artist>error(mock(Exception.class)));

        onViewCreated();

        verify(view).setUserInfo("fail", null);
    }

    private void onViewCreated() {
        artistsPresenter.onViewCreated(fragment, null, null);
    }

    @After
    public void tearDown() throws Exception {
        RxAndroidPlugins.reset();
    }
}
