package com.sample.instantsonar;

import dagger.Module;
import dagger.Provides;

import android.app.Application;

import javax.inject.Singleton;

@Module
public class AppModule {

    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return application;
    }
}