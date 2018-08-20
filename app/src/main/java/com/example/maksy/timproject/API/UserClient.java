package com.example.maksy.timproject.API;

import com.example.maksy.timproject.User.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserClient {

    @POST("user")
    Call<TestUser> createAccount(@Body TestUser user);
}
