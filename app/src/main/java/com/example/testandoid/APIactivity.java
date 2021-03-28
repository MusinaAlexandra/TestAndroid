package com.example.testandoid;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIactivity extends AppCompatActivity {

    DadApi dadAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);
        String baseUrl = "https://jsonparsingdemo-cec5b.firebaseapp.com";
        new  ParseTask().execute(baseUrl);
    }

    @SuppressLint("StaticFieldLeak")
    private  class ParseTask extends AsyncTask {
        String baseUrl = "https://jsonparsingdemo-cec5b.firebaseapp.com";
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl);

        @Override
        protected JokeData doInBackground(Object[] objects) {
            // rest api call
            try {
                Response<JSONObject> response = getApiJoke().getJoke().execute();
                if(response.isSuccessful() && response.code() == 200){
                    Log.e("@@@" , "code " + response.code());
                    Log.e("@@@" , "code " + response.body());
                    JSONObject parentJson = new JSONObject(String.valueOf(response.body()));
                    Gson gson = new Gson();
                    JSONObject json = new JSONObject(String.valueOf(response.body()));
                    JokeData jData = gson.fromJson(json.toString(),JokeData.class);
                    return jData;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        public  <S> S createService(Class<S> servce){
            httpClient.interceptors().clear();
            httpClient.readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10,TimeUnit.SECONDS);
            builder.client(httpClient.build());
            Retrofit retrofit = builder.build();
            return retrofit.create(servce);
        }
        private DadApi getApiJoke(){
            return createService(DadApi.class);
        }
    }
}