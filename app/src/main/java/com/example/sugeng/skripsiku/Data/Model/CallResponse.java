package com.example.sugeng.skripsiku.Data.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by USER on 11/08/2018.
 */

public class CallResponse {



    @SerializedName("response")
    private String response;

    public void setResponse(String response) {
        this.response = response;
    }

    public void setKuliners(String kuliners) {
        this.kuliners = kuliners;
    }

    public void setKulinerModelList(List<KulinerModel> kulinerModelList) {
        this.kulinerModelList = kulinerModelList;
    }

    public void setKategoris(List<Category> kategoris) {
        this.kategoris = kategoris;
    }

    @SerializedName("kuliners")
    private String kuliners;

    @SerializedName("kuliner")
    private List<KulinerModel> kulinerModelList;

    @SerializedName("kategori")
    private List<Category> kategoris;


    public String getResponse() {
        return response;
    }

    public String getKuliners() {
        return kuliners;
    }

    public List<KulinerModel> getKulinerModelList() {
        return kulinerModelList;
    }

    public List<Category> getKategoris() {
        return kategoris;
    }







}