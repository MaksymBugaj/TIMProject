package com.example.maksy.timproject.API;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface HerokuService {
    @GET("users/")
    Call<ResponseBody> hello();

    @POST(value = "users/")
    Call<TestUser> create(@Body TestUser testUser);
}
