package com.example.masterdetailsmvvm.network;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {private static final String BASE_URL = "https://api.nytimes.com/";
      private static ApiClient instance;

    public static ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }

    private static OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request.Builder builder = chain.request().newBuilder();
                    builder.addHeader("Accept", "application/json");
                    Request request = builder.build();
                    return chain.proceed(request);
                })
                .build();
    }

    public static Retrofit getClient() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

}