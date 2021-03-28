package com.example.testandoid;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface  DadApi {
    @GET("https://icanhazdadjoke.com/")
    @Headers("Accept: application/json")
    Call<JSONObject> getJoke();
}
