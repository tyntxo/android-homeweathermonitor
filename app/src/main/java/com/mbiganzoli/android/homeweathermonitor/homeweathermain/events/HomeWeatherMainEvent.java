package com.mbiganzoli.android.homeweathermonitor.homeweathermain.events;

import com.mbiganzoli.android.homeweathermonitor.entities.InfoRecord;

import java.util.List;

/**
 * Created by martin on 8/7/16.
 */
public class HomeWeatherMainEvent {

    private int type;
    private String error;
    private List<InfoRecord> records;
    public final static int READ_EVENT = 0;
    public final static int ERROR_EVENT = 1;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<InfoRecord> getRecords() {
        return records;
    }

    public void setRecords(List<InfoRecord> records) {
        this.records = records;
    }


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

