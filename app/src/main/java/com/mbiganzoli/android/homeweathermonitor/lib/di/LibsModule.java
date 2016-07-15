package com.mbiganzoli.android.homeweathermonitor.lib.di;

import android.app.Activity;

import com.mbiganzoli.android.homeweathermonitor.domain.FirebaseHelper;
import com.mbiganzoli.android.homeweathermonitor.lib.CustomEventBus;
import com.mbiganzoli.android.homeweathermonitor.lib.GreenRobotEventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class LibsModule {
    Activity activity;

    public LibsModule() {
    }
    public LibsModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    CustomEventBus provideEventBus() {
        return new GreenRobotEventBus();
    }

    @Provides
    @Singleton
    FirebaseHelper provideFireBaseHelper() {
        return new FirebaseHelper();
    }


    @Provides
    @Singleton
    Activity provideActivity(){
        return this.activity;
    }

}
