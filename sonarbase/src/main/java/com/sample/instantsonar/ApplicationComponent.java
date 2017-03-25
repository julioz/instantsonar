package com.sample.instantsonar;

import com.google.gson.Gson;
import dagger.Component;
import io.reactivex.Scheduler;
import okhttp3.OkHttpClient;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface ApplicationComponent {
    OkHttpClient okHttpClient();
    Gson gson();
    Scheduler scheduler();
}
