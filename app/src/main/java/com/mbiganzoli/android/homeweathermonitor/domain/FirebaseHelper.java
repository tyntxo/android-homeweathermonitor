package com.mbiganzoli.android.homeweathermonitor.domain;

import com.firebase.client.Firebase;
import com.firebase.client.Query;

public class FirebaseHelper {
    private Firebase dataReference;
    private final static String SEPARATOR = "/";
    private final static String FIREBASE_URL = "https://homeweathermonitor.firebaseio.com/";

    private static class SingletonHolder {
        private static final FirebaseHelper INSTANCE = new FirebaseHelper();
    }


    public FirebaseHelper(){
        dataReference = new Firebase(FIREBASE_URL);
    }

    public Firebase getDataReference() {
        return dataReference;
    }


    public Query getRecords(String year, String month, String day){

        Query query = dataReference.getRoot().child(year).child(month).child(day);
        query.keepSynced(true);
        return query;

    }


}
