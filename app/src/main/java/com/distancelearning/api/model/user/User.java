package com.distancelearning.api.model.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by v.odahovskiy on 01.02.2016.
 */
public class User implements Serializable{

    @SerializedName("id") private String id;
    @SerializedName("name") private String name;
    @SerializedName("surname") private String surname;
    @SerializedName("avatar") private String avatar;
    @SerializedName("birthday") private String birthday;
    @SerializedName("phone") private String phone;
    @SerializedName("slug") private String slug;
    @SerializedName("role") private String role;
    @SerializedName("email") private String email;

    public User(String name, String surname, String avatar, String birthday, String phone, String role, String email) {
        this.name = name;
        this.surname = surname;
        this.avatar = avatar;
        this.birthday = birthday;
        this.phone = phone;
        this.role = role;
        this.email = email;
    }

    public User(){}

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", birthday='" + birthday + '\'' +
                ", phone='" + phone + '\'' +
                ", slug='" + slug + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
