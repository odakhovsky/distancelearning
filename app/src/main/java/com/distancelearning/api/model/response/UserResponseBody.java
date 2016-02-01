package com.distancelearning.api.model.response;

import com.distancelearning.api.model.user.User;
import com.google.gson.annotations.SerializedName;

/**
 * Created by v.odahovskiy on 01.02.2016.
 */
public class UserResponseBody extends SimpleResponseBody{
    @SerializedName("user")
    private User user;
    @SerializedName("token")
    private String token;

    public UserResponseBody(boolean isSucces, int code, String message, String token, User user) {
        super(isSucces, code, message);
        this.token = token;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserResponseBody() {
    }

    public UserResponseBody(boolean isSucces, int code, String message) {
        super(isSucces, code, message);
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
