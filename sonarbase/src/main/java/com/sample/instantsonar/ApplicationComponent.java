package com.sample.instantsonar;

import dagger.Component;
import okhttp3.OkHttpClient;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface ApplicationComponent {
    OkHttpClient okHttpClient();
}