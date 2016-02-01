package com.distancelearning.api;

/**
 * Created by v.odahovskiy on 01.02.2016.
 */
public class DistanceApiConsts {

    public static final String HOST = "http://distance-learning.herokuapp.com/api/";

    public static final String USER_LOGIN = HOST + "auth/login";
    public static final String USER_LIST = HOST + "users/";
    public static final String USER_TOKEN_AUTH = HOST + "auth/user";
    public static final String USER_CREATE = HOST + "auth/registration";


    public static final int HTTP_OK = 200;
}
