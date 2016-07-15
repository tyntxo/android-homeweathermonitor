package com.mbiganzoli.android.homeweathermonitor.homeweathermain;

import com.mbiganzoli.android.homeweathermonitor.homeweathermain.events.HomeWeatherMainEvent;
import com.mbiganzoli.android.homeweathermonitor.homeweathermain.ui.HomeWeatherMainView;
import com.mbiganzoli.android.homeweathermonitor.lib.CustomEventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by martin on 8/7/16.
 */
public class HomeWeatherMainPresenterImpl implements HomeWeatherMainPresenter {

    private CustomEventBus eventBus;
    private HomeWeatherMainView view;
    private HomeWeatherMainInteractor listInteractor;

    public HomeWeatherMainPresenterImpl(CustomEventBus eventBus, HomeWeatherMainView view, HomeWeatherMainInteractor listInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.listInteractor = listInteractor;
    }


    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;
    }

    @Override
    public void getRecordsByDay(String year, String month, String day)  {
        if (this.view != null) {
            view.showProgress();
        }
        listInteractor.getRecordsByDay(year, month, day);
    }

    @Override
    @Subscribe
    public void onEventMainThread(HomeWeatherMainEvent event) {
        if (this.view != null) {
            switch (event.getType()){
                case HomeWeatherMainEvent.READ_EVENT:
                    view.hideProgress();
                    view.setInfoRecords(event.getRecords());
                    break;
                case HomeWeatherMainEvent.ERROR_EVENT:
                    view.hideProgress();
                    view.onGetInfoRecordsError(event.getError());
                    break;
            }
        }
    }

    @Override
    public HomeWeatherMainView getView() {
        return this.view;
    }
}
