package com.example.testandoid;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIactivity extends AppCompatActivity {

    DadApi dadAPI;
    TextView textView;
    JokeData jokeData = new JokeData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);
        textView =findViewById(R.id.textView);
        String baseUrl = "https://icanhazdadjoke.com";
        new  ParseTask().execute();
    }

    @SuppressLint("StaticFieldLeak")
    private  class ParseTask extends AsyncTask {
        String baseUrl = "https://icanhazdadjoke.com";
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
        public DadApi joke_response = retrofit.create(DadApi.class);

        @Override
        protected JokeData doInBackground(Object[] objects) {
            // rest api call
            try {
                Log.e("@@@", joke_response.getJoke().request().url().toString());
                Log.e("@@@", joke_response.getJoke().request().headers().toString());
                Log.e("@@@", joke_response.getJoke().request().method());
                Response<JsonObject> response = joke_response.getJoke().execute();
                if(response.isSuccessful() && response.code() == 200){
                    Log.e("@@@" , "code " + response.code());
                    Log.e("@@@" , "code " + response.body());
                    JSONObject parentJson = new JSONObject(String.valueOf(response.body()));
                    Gson gson = new Gson();
                    JSONObject json = new JSONObject(String.valueOf(response.body()));
                    jokeData.setJoke(json.getString("joke"));
                    return jokeData;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Object o) {
            textView.setText(jokeData.getJoke());
        }
        public  <S> S createService(Class<S> servce){
            httpClient.interceptors().clear();
            httpClient.readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10,TimeUnit.SECONDS);
           // builder.client(httpClient.build());
            //Retrofit retrofit = builder.build();
            return retrofit.create(servce);
        }
        private DadApi getApiJoke(){
            return createService(DadApi.class);
        }
    }
}
