package com.sample.instantsonar.tracks;

import com.sample.instantsonar.ApplicationComponent;
import dagger.Component;

@TrackScope
@Component(dependencies = ApplicationComponent.class)
public interface TrackComponent {
    void inject(TrackFragment fragment);
}
