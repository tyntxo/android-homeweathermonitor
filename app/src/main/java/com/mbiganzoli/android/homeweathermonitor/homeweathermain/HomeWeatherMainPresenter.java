package com.mbiganzoli.android.homeweathermonitor.homeweathermain;

import com.mbiganzoli.android.homeweathermonitor.homeweathermain.events.HomeWeatherMainEvent;
import com.mbiganzoli.android.homeweathermonitor.homeweathermain.ui.HomeWeatherMainView;

/**
 * Created by martin on 8/7/16.
 */
public interface HomeWeatherMainPresenter {

    void onCreate();
    void onDestroy();

    void getRecordsByDay(String year, String month, String day);
    void onEventMainThread(HomeWeatherMainEvent event);

    HomeWeatherMainView getView();
}
