package com.sample.instantsonar;

import android.app.Application;

public class SampleApplication extends Application {

    private static ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = DaggerApplicationComponent.builder()
                                               .appModule(new AppModule(this))
                                               .build();
    }

    public static ApplicationComponent getComponent() {
        return mComponent;
    }
}