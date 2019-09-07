package com.example.sugeng.skripsiku;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sugeng.skripsiku.Adapter.MainAdapter;
import com.example.sugeng.skripsiku.Data.Constant;
import com.example.sugeng.skripsiku.Data.Model.CallResponse;
import com.example.sugeng.skripsiku.Data.Model.KulinerModel;
import com.example.sugeng.skripsiku.Rest.Api;
import com.example.sugeng.skripsiku.Rest.ApiInterface;
import com.example.sugeng.skripsiku.Utils.ImgDownload;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    TextView txtKeterangan;
    ImageView imageDkuliner;

    //pngky
//    private List<KulinerModel> kulinerModelList = new ArrayList<>();
//    private MainAdapter mAdapter;
    Intent intent;
     String myLat = null;
     String myLng = null;
    Button btnMap;
    private void getDetail(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);

        Call<CallResponse> call = apiInterface.getDetail(bundle.getString("ID_KULINER"));


        call.enqueue(new Callback<CallResponse>() {
            @Override
            public void onResponse(Call<CallResponse> call, Response<CallResponse> response) {
             //   Log.e("_resp", response.toString());
               final List<KulinerModel> kulinerModelList = response.body().getKulinerModelList();


                for (int i=0; i< kulinerModelList.size(); i++){
                    txtKeterangan.setText(kulinerModelList.get(i).getKeterangan());
                    ImgDownload.picasso(Constant.IMAGE_PATH+kulinerModelList.get(i).getImages(),imageDkuliner);
                }

//                btnMap.setAdapter(new MainAdapter(DetailActivity.this,kulinerModelList));
//                final Button button = (Button) findViewById(R.id.btnMap);
//                button.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        // Perform action on click
//                        Intent intent = new Intent(DetailActivity.this,DetailMapsActivity.class);
//
//                        startActivity(intent);
//                    }
//                });


            }

            @Override
            public void onFailure(Call<CallResponse> call, Throwable t) {
                Log.e("_err",t.toString());
            }
        });
        //coba

    }

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        intent = getIntent();
//        myLat = intent.getStringExtra("LATITUDE");
//        myLng = intent.getStringExtra("LONGITUDE");
        bundle  =  getIntent().getExtras();


        txtKeterangan = findViewById(R.id.txtKeterangan);
        imageDkuliner = findViewById(R.id.imageDkuliner);
        btnMap =  findViewById(R.id.btnMap);


        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            //        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:<"+myLat+">,<"+myLng+">?q=<"+kulinerModelList.get(0).getLat()+">,<"+kulinerModelList.get(0).getLng()+">("+kulinerModelList.get(0).getNama_kuliner()+")"));

            public void onClick(View view ) {
                Bundle bundle = new Bundle();
                bundle.putDouble("LATITUDE", Double.parseDouble(intent.getStringExtra("LATITUDE")));
                bundle.putDouble("LONGITUDE", Double.parseDouble(intent.getStringExtra("LONGITUDE")));
                bundle.putString("NAMA_KULINER",intent.getStringExtra("NAMA_KULINER"));
                Intent intent = new Intent(DetailActivity.this,DetailMapsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);


            }


        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getSupportActionBar().setTitle(bundle.getString("NAMA_KULINER"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getDetail();

}
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
