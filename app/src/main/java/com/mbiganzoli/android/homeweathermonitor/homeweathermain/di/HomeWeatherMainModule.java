package com.mbiganzoli.android.homeweathermonitor.homeweathermain.di;

import com.mbiganzoli.android.homeweathermonitor.domain.FirebaseHelper;
import com.mbiganzoli.android.homeweathermonitor.entities.InfoRecord;
import com.mbiganzoli.android.homeweathermonitor.homeweathermain.HomeWeatherMainInteractor;
import com.mbiganzoli.android.homeweathermonitor.homeweathermain.HomeWeatherMainInteractorImpl;
import com.mbiganzoli.android.homeweathermonitor.homeweathermain.HomeWeatherMainPresenter;
import com.mbiganzoli.android.homeweathermonitor.homeweathermain.HomeWeatherMainPresenterImpl;
import com.mbiganzoli.android.homeweathermonitor.homeweathermain.HomeWeatherMainRepository;
import com.mbiganzoli.android.homeweathermonitor.homeweathermain.HomeWeatherMainRepositoryImpl;
import com.mbiganzoli.android.homeweathermonitor.homeweathermain.ui.HomeWeatherMainView;
import com.mbiganzoli.android.homeweathermonitor.homeweathermain.ui.adapters.InfoRecordsAdapter;
import com.mbiganzoli.android.homeweathermonitor.homeweathermain.ui.adapters.OnItemClickListener;
import com.mbiganzoli.android.homeweathermonitor.lib.CustomEventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeWeatherMainModule {

    HomeWeatherMainView view;
    OnItemClickListener onItemClickListener;

    public HomeWeatherMainModule(HomeWeatherMainView view, OnItemClickListener onItemClickListener) {
        this.view = view;
        this.onItemClickListener = onItemClickListener;
    }

    @Provides @Singleton
    HomeWeatherMainView provideHomeWeatherMainView() {
        return this.view;
    }

    @Provides @Singleton
    HomeWeatherMainPresenter provideHomeWeatherPresenter(CustomEventBus eventBus, HomeWeatherMainView view, HomeWeatherMainInteractor listInteractor) {
        return new HomeWeatherMainPresenterImpl(eventBus, view, listInteractor);
    }

    @Provides @Singleton
    HomeWeatherMainInteractor provideHomeWeatherMainInteractor(HomeWeatherMainRepository repository) {
        return new HomeWeatherMainInteractorImpl(repository);
    }


    @Provides @Singleton
    HomeWeatherMainRepository provideHomeWeatherMainRepository(CustomEventBus eventBus, FirebaseHelper firebaseHelper) {
        return new HomeWeatherMainRepositoryImpl(eventBus, firebaseHelper) {
        };
    }

    @Provides @Singleton
    InfoRecordsAdapter provideInfoRecordsAdapter(List<InfoRecord> records, OnItemClickListener onItemClickListener) {
        return new InfoRecordsAdapter(records, onItemClickListener);
    }

    @Provides @Singleton
    OnItemClickListener provideOnItemClickListener() {
        return this.onItemClickListener;
    }

    @Provides @Singleton
    List<InfoRecord> provideInfoRecords() {
        return new ArrayList<InfoRecord>();
    }

}
