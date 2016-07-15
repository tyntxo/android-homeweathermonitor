package com.mbiganzoli.android.homeweathermonitor.entities;

import com.mbiganzoli.android.homeweathermonitor.R;

import java.util.Date;

/**
 * Created by martin on 8/7/16.
 */

public class InfoRecord {


    public double temperature;
    public double humidity;
    public double pressure;
    public double altitude;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long timestamp;


    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

}
