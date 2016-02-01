package com.distancelearning.api.rest;

import com.distancelearning.api.DistanceApiConsts;
import com.distancelearning.api.model.response.UserResponseBody;
import com.distancelearning.api.model.user.NewUser;
import com.distancelearning.api.model.user.UserLogin;
import com.distancelearning.api.model.user.User;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;

/**
 * This interface describe work with api for user model
 */
public interface UserRest {


    @POST(DistanceApiConsts.USER_CREATE)
    Call<UserResponseBody> create(@Body NewUser user);

    @POST(DistanceApiConsts.USER_LOGIN)
    Call<UserResponseBody> auth(@Body UserLogin user);

    @GET(DistanceApiConsts.USER_TOKEN_AUTH)
    Call<UserResponseBody> getUser(@Header("Authorization") String authorization);

}
