package com.distancelearning.api;

import com.distancelearning.api.model.response.UserResponseBody;
import com.distancelearning.api.model.response.SimpleResponseBody;
import com.distancelearning.api.model.user.NewUser;
import com.distancelearning.api.model.user.User;
import com.distancelearning.api.model.user.UserLogin;
import com.distancelearning.api.rest.UserRest;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by v.odahovskiy on 01.02.2016.
 */
public class UserApi {

    private UserRest userRest;

    public UserApi(Retrofit retrofit) {
        userRest = retrofit.create(UserRest.class);
    }

    public void create(NewUser user, final ResponseCallback<UserResponseBody> callback) {
        checkCallbackImpl(callback);

        userRest.create(user).enqueue(new Callback<UserResponseBody>() {
            @Override
            public void onResponse(Response<UserResponseBody> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    callback.onSuccess(new UserResponseBody(response.isSuccess(), response.code(), response.message(), response.body().getToken(), response.body().getUser()));
                }else {
                    callback.onFailure(new SimpleResponseBody(response.isSuccess(), response.code(), response.message()));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onFailure(new SimpleResponseBody(false, 0, "Problem with creating new user")); //todo change status code
            }
        });
    }

    public void auth(UserLogin login, final ResponseCallback<UserResponseBody> callback){
        checkCallbackImpl(callback);

        userRest.auth(login).enqueue(new Callback<UserResponseBody>() {
            @Override
            public void onResponse(Response<UserResponseBody> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    callback.onSuccess(new UserResponseBody(response.isSuccess(), response.code(), response.message(), response.body().getToken(), response.body().getUser()));
                }else {
                    callback.onFailure(new SimpleResponseBody(response.isSuccess(), response.code(), response.message()));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onFailure(new SimpleResponseBody(false, 0, "Problem with creating new user")); //todo change status code
            }
        });
    }

    private void checkCallbackImpl(ResponseCallback<UserResponseBody> callback) {
        if (null == callback) {
            throw new IllegalArgumentException("Must implement callback");
        }
    }
}
