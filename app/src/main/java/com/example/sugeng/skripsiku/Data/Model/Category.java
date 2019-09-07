package com.example.sugeng.skripsiku.Data.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 15/08/2018.
 */

public class Category {
    @SerializedName("id_kategori")
    private String id_kategori;

    @SerializedName("nama_kategori")
    private String nama_kategori;

    public String getId_kategori() {
        return id_kategori;
    }

    public String getNama_kategori() {
        return nama_kategori;
    }


}
