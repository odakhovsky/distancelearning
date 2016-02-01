package com.distancelearning.api.model.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by v.odahovskiy on 01.02.2016.
 */
public class NewUser implements Serializable{

    @SerializedName("name") private String name;
    @SerializedName("surname") private String surname;
    @SerializedName("birthday") private String birthday;
    @SerializedName("phone") private String phone;
    @SerializedName("email") private String email;
    @SerializedName("password") private String password;
    @SerializedName("password_confirmation") private String comfirmPassword;

    public NewUser(){}

    public NewUser(String name, String surname, String birthday, String phone, String email, String password, String comfirmPassword) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.comfirmPassword = comfirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getComfirmPassword() {
        return comfirmPassword;
    }

    public void setComfirmPassword(String comfirmPassword) {
        this.comfirmPassword = comfirmPassword;
    }

    public boolean isPasswordEquals() {
        return password.equals(comfirmPassword);
    }

    @Override
    public String toString() {
        return "NewUser{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday='" + birthday + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", comfirmPassword='" + comfirmPassword + '\'' +
                '}';
    }
}
