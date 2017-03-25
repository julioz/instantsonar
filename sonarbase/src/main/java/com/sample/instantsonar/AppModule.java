package com.sample.instantsonar;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

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

    @Provides
    Scheduler providesScheduler() {
        return Schedulers.newThread();
    }
}
