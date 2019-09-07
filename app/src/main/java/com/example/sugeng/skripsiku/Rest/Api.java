package com.example.sugeng.skripsiku.Rest;

import com.example.sugeng.skripsiku.Data.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by USER on 11/08/2018.
 */

public class Api {

    private static Retrofit retrofit = null;
    public static Retrofit getUrl(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;

    }
}
