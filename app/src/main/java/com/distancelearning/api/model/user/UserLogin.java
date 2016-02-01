package com.distancelearning.api.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by v.odahovskiy on 01.02.2016.
 */
public class UserLogin implements Serializable{
    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;

    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserLogin() {
    }
}
