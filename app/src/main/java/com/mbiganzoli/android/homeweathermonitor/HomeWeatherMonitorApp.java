package com.mbiganzoli.android.homeweathermonitor;

import android.app.Application;


import com.firebase.client.Firebase;
import com.mbiganzoli.android.homeweathermonitor.homeweathermain.di.DaggerHomeWeatherMainComponent;
import com.mbiganzoli.android.homeweathermonitor.homeweathermain.di.HomeWeatherMainComponent;
import com.mbiganzoli.android.homeweathermonitor.homeweathermain.di.HomeWeatherMainModule;
import com.mbiganzoli.android.homeweathermonitor.homeweathermain.ui.HomeWeatherMainActivity;
import com.mbiganzoli.android.homeweathermonitor.homeweathermain.ui.HomeWeatherMainView;
import com.mbiganzoli.android.homeweathermonitor.homeweathermain.ui.adapters.OnItemClickListener;
import com.mbiganzoli.android.homeweathermonitor.lib.di.LibsModule;

public class HomeWeatherMonitorApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
    }

    private void setupFirebase(){
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(false);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public HomeWeatherMainComponent getHomeWeatherMainComponent(HomeWeatherMainActivity activity, HomeWeatherMainView view, OnItemClickListener onItemClickListener) {
       return DaggerHomeWeatherMainComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .homeWeatherMainModule(new HomeWeatherMainModule(view, onItemClickListener))
                .build();

    }

}