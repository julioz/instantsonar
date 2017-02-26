package com.sample.instantsonar.model;

import com.google.gson.annotations.SerializedName;

public class User {
    private String username;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("full_name")
    private String fullName;
    private String city;

    public String getUsername() {
        return username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", fullName='" + fullName + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
