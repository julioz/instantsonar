package com.sample.instantsonar.artists;

import com.sample.instantsonar.ApplicationComponent;
import dagger.Component;

@Component(dependencies = ApplicationComponent.class)
public interface ArtistsComponent {
    void inject(ArtistsFragment fragment);
}
