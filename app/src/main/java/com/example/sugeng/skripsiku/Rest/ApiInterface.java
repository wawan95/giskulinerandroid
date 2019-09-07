package com.example.sugeng.skripsiku.Rest;

import com.example.sugeng.skripsiku.Data.Model.CallResponse;

import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by USER on 11/08/2018.
 */

public interface ApiInterface {

    @GET("service")
    Call<CallResponse> getKuliner(@Query("nama_kuliner") String nama_kuliner);

    @GET("service")
    Call<CallResponse> getDetail(@Query("id_kuliner") String id_kuliner
    );

    @GET("service/kuliner")
    Call<CallResponse> getAllLocation();

    @GET("service/kuliner")
    Call<CallResponse> getLocation();


    @GET("service/kategori")
    Call<CallResponse> getKategori();

    @FormUrlEncoded
    @POST("service/kategori")
    Call<CallResponse> postKategori(
        @Field("id_kategori")String id_kategori);

}
