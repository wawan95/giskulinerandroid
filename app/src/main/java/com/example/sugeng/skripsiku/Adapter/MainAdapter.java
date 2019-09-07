package com.example.sugeng.skripsiku.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sugeng.skripsiku.Data.Constant;
//import com.example.sugeng.skripsiku.Data.Model.Kuliner;
import com.example.sugeng.skripsiku.Data.Model.KulinerModel;
import com.example.sugeng.skripsiku.R;
import com.example.sugeng.skripsiku.Utils.ImgDownload;

import java.util.List;

/**
 * Created by USER on 14/08/2018.
 */

public class MainAdapter extends ArrayAdapter<KulinerModel> {

    public MainAdapter(@NonNull Context context, @NonNull List<KulinerModel> objects) {
        super(context,0 , objects);
    }


    @NonNull
    @Override
    //
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        KulinerModel kulinerModel = getItem(position);//parameter getView
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate( R.layout.adapter_main,parent,false);
        }
        TextView txtNama = convertView.findViewById(R.id.txtNama);
        TextView kategori = convertView.findViewById(R.id.kategori);
        ImageView imageView = convertView.findViewById(R.id.imageView);

        txtNama.setText( kulinerModel.getNama_kuliner());
        kategori.setText( kulinerModel.getAlamat());
        ImgDownload.picasso(Constant.IMAGE_PATH + kulinerModel.getImages(),imageView);


        return convertView;
    }
}
