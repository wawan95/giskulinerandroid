package com.example.sugeng.skripsiku.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sugeng.skripsiku.Data.Model.Category;
import com.example.sugeng.skripsiku.R;

import java.util.List;

/**
 * Created by USER on 15/08/2018.
 */

public class CategoryAdapter extends ArrayAdapter<Category> {

    public CategoryAdapter(@NonNull Context context, @NonNull List<Category> objects) {
        super(context,0 , objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Category kategori = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate( R.layout.adapter_category,parent,false);
        }
        TextView txtId = convertView.findViewById(R.id.txtId);
        TextView txtCategory = convertView.findViewById(R.id.txtCategory);


        txtId.setText( kategori.getId_kategori());
        txtCategory.setText( kategori.getNama_kategori());


        return convertView;
    }
}
