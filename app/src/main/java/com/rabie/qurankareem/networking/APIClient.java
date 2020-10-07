package com.rabie.qurankareem.networking;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static Retrofit retrofit = null;
    public APIClient(){
        Gson gson = new GsonBuilder()
            .setLenient()
            .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(RESTApi.baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

    }
   public static Retrofit getClient() {
        if (retrofit == null) new APIClient();
        return retrofit;
    }

}