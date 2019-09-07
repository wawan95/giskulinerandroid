package com.example.sugeng.skripsiku.Utils;

import android.widget.ImageView;

import com.example.sugeng.skripsiku.R;
import com.squareup.picasso.Picasso;

/**
 * Created by USER on 14/08/2018.
 */

public class ImgDownload {
    public static void picasso(String URL, ImageView imageView){
        Picasso.get().load(URL).placeholder(R.drawable.no_image).error(R.drawable.no_preview)
                .into(imageView);
    }
}
