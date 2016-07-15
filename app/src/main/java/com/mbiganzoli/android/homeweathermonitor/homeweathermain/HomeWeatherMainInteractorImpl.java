package com.mbiganzoli.android.homeweathermonitor.homeweathermain;

/**
 * Created by martin on 8/7/16.
 */
public class HomeWeatherMainInteractorImpl implements HomeWeatherMainInteractor{
    private HomeWeatherMainRepository repository;

    public HomeWeatherMainInteractorImpl(HomeWeatherMainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getRecordsByDay(String year, String month, String day) {
        repository.getRecordsByDay(year, month, day);
    }
}

