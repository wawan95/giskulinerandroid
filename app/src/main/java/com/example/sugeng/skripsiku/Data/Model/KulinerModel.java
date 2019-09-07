package com.example.sugeng.skripsiku.Data.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 17/08/2018.
 */

public class KulinerModel {
    @SerializedName("id_kuliner")
    private String id_kuliner;
    @SerializedName("id_kategori")
    private String id_kategori;
    @SerializedName("nama_kategori")
    private String nama_kategori;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("images")
    private String images;
    @SerializedName("keterangan")
    private String keterangan;
    @SerializedName("lat")
    private String lat;
    @SerializedName("lng")
    private String lng;
    @SerializedName("nama_kuliner")
    private String nama_kuliner;

    public KulinerModel(String id_kuliner, String id_kategori, String nama_kategori,String alamat,String images,String keterangan,String lat,String lng,String nama_kuliner) {
        this.id_kuliner = id_kuliner;
        this.id_kategori = id_kategori;
        this.nama_kategori = nama_kategori;
        this.alamat = alamat;
        this.images = images;
        this.keterangan = keterangan;
        this.lat = lat;
        this.lng = lng;
        this.nama_kuliner = nama_kuliner;
    }



    public String getId_kuliner() {
        return id_kuliner;
    }
    public void setId_kuliner(String id_kuliner) {
        this.id_kuliner = id_kuliner;
    }

    public String getId_kategori() {
        return id_kategori;
    }
    public void setId_kategori(String id_kategori) {
        this.id_kategori = id_kategori;
    }

    public String getNama_kategori() {
        return nama_kategori;
    }
    public void setNama_kategori(String nama_kategori) {
        this.nama_kategori = nama_kategori;
    }

    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getImages() {
        return images;
    }
    public void setImages(String images) {
        this.images = images;
    }

    public String getKeterangan() {
        return keterangan;
    }
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getLat() {
        return lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }
    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getNama_kuliner() {
        return nama_kuliner;
    }
    public void setNama_kuliner(String nama_kuliner) {
        this.nama_kuliner = nama_kuliner;
    }

}
