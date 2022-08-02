package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestaurantAPI {
    @GET("users")
    Call<List<Users>> getUsers();

    @POST("auth")
    Call<LoginResponse> login(@Body LoginRequest auth);

    @POST("register")
    Call<Users> register(@Body Users register);

    @GET("restaurants")
    Call<List<Restaurant>> getRestaurants();

    @POST("restaurant")
    Call<String> postRestaurant(@Body Restaurant restaurant);

}
