package com.mbiganzoli.android.homeweathermonitor.lib;

/**
 * Created by ykro.
 */
public interface CustomEventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);

}
