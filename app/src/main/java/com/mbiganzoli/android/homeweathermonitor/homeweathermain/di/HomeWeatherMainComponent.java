package com.mbiganzoli.android.homeweathermonitor.homeweathermain.di;

import com.mbiganzoli.android.homeweathermonitor.homeweathermain.HomeWeatherMainPresenter;
import com.mbiganzoli.android.homeweathermonitor.homeweathermain.ui.adapters.InfoRecordsAdapter;
import com.mbiganzoli.android.homeweathermonitor.lib.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;



@Singleton
@Component(modules = {HomeWeatherMainModule.class, LibsModule.class})
public interface HomeWeatherMainComponent {

    HomeWeatherMainPresenter getPresenter();
    InfoRecordsAdapter getAdapter();
}
