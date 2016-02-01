package com.distancelearning.api;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class DistanceApi {

    private static DistanceApi instance;

    private Retrofit retrofit;
    private UserApi userApi;

    public static DistanceApi getApi(){
        if (null == instance) {
            instance = new DistanceApi();
        }

        return instance;
    }

    private DistanceApi(){
        retrofit = new Retrofit.Builder()
                .baseUrl(DistanceApiConsts.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public UserApi getUserApi(){
        if (null == userApi){
            userApi = new UserApi(retrofit);
        }
        return userApi;
    }

}
