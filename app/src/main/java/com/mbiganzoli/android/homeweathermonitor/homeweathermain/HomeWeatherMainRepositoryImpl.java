package com.mbiganzoli.android.homeweathermonitor.homeweathermain;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.mbiganzoli.android.homeweathermonitor.domain.FirebaseHelper;
import com.mbiganzoli.android.homeweathermonitor.entities.InfoRecord;
import com.mbiganzoli.android.homeweathermonitor.homeweathermain.events.HomeWeatherMainEvent;
import com.mbiganzoli.android.homeweathermonitor.lib.CustomEventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martin on 8/7/16.
 */
public class HomeWeatherMainRepositoryImpl implements HomeWeatherMainRepository {

    private ValueEventListener redcordEventListener;
    private CustomEventBus eventBus;
    private FirebaseHelper firebaseHelper;
    List<InfoRecord> records = new ArrayList<>() ;


    public HomeWeatherMainRepositoryImpl(CustomEventBus eventBus, FirebaseHelper firebaseHelper) {
        this.eventBus = eventBus;
        this.firebaseHelper = firebaseHelper;
    }

    @Override
    public void getRecordsByDay(String year, String month, String day) {

        try {
            if (redcordEventListener == null) {
                redcordEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        List<InfoRecord> records = new ArrayList<>();
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            records.add(child.getValue(InfoRecord.class));
                        }
                        post(records);
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                };

            }

            firebaseHelper.getRecords(year,month,day).addListenerForSingleValueEvent(redcordEventListener);
        }
        catch (Exception e){
            post(e.getLocalizedMessage());
        }
    }


    private void post(List<InfoRecord> records) {
        HomeWeatherMainEvent event = new HomeWeatherMainEvent();
        event.setRecords(records);
        event.setType(HomeWeatherMainEvent.READ_EVENT);
        eventBus.post(event);
    }


    private void post(String error) {
        HomeWeatherMainEvent event = new HomeWeatherMainEvent();
        event.setType(HomeWeatherMainEvent.ERROR_EVENT);
        event.setError(error);
        eventBus.post(event);
    }


}
