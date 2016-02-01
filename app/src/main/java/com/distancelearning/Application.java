package com.distancelearning;

import com.distancelearning.api.DistanceApi;

/**
 * Created by v.odahovskiy on 01.02.2016.
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DistanceApi.initialize(getApplicationContext());
    }
}
