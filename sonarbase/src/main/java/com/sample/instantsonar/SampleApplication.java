package com.sample.instantsonar;

import android.app.Application;

public class SampleApplication extends Application {

    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = DaggerApplicationComponent.builder()
                                               .appModule(new AppModule(this))
                                               .build();
    }

    public ApplicationComponent getComponent() {
        return mComponent;
    }
}