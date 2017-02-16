package com.sample.instantsonar;

import dagger.Component;

import android.support.v4.app.Fragment;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface ApplicationComponent {
    void inject(SampleApplication app);
    void inject(Fragment fragment);
}