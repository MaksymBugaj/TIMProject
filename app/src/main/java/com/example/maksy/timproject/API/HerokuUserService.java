package com.example.maksy.timproject.API;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HerokuUserService {
    @POST()
    Call<TestUser> create(@Body TestUser testUser);
}
