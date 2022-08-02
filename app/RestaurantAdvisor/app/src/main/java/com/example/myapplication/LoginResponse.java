package com.example.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("user")
    @Expose
    private Users user;
    @SerializedName("token")
    @Expose
    private String token;

    public LoginResponse(Users user, String token){
        this.user = user;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public Users getUser() {
        return user;
    }
}
