package com.example.testandoid;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface  DadApi {
    @GET("/")
    @Headers("Accept: application/json")
    Call<JsonObject> getJoke();
}
