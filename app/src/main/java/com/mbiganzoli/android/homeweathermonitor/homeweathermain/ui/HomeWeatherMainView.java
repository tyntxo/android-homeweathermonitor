package com.mbiganzoli.android.homeweathermonitor.homeweathermain.ui;

import com.mbiganzoli.android.homeweathermonitor.entities.InfoRecord;
import java.util.List;

/**
 * Created by martin on 8/7/16.
 */
public interface HomeWeatherMainView {
    void showProgress();
    void hideProgress();
    void setInfoRecords(List<InfoRecord> data);
    void onGetInfoRecordsError(String error);
}
