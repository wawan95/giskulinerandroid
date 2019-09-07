package com.example.sugeng.skripsiku;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sugeng.skripsiku.Data.Model.CallResponse;
import com.example.sugeng.skripsiku.Data.Model.KulinerModel;
import com.example.sugeng.skripsiku.Rest.Api;
import com.example.sugeng.skripsiku.Rest.ApiInterface;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private List<KulinerModel> mDetailMarker = new ArrayList<>();


    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        bundle = getIntent().getExtras();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getDetailMaps();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                ){
            mMap.setMyLocationEnabled(true);
        }
        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private void getDetailMaps(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<CallResponse> call = apiInterface.getLocation();
        call.enqueue(new Callback<CallResponse>(){
            @Override
            public void onResponse(Call<CallResponse> call, Response<CallResponse> Response) {
            mDetailMarker = Response.body().getKulinerModelList();
            initMarker(mDetailMarker);
            }

            @Override
            public void onFailure(Call<CallResponse> call, Throwable t) {

                Toast.makeText(DetailMapsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initMarker(List<KulinerModel> detailData){

        LatLng kudus = new LatLng(bundle.getDouble("LATITUDE"),bundle.getDouble("LONGITUDE"));
        mMap.addMarker(new MarkerOptions().position(kudus)
                .title(bundle.getString("NAMA_KULINER")).icon(BitmapDescriptorFactory.fromResource(R.drawable.map_mbiru)));
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(kudus));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(kudus,13),2000,null);

//            LatLng location = new LatLng(bundle.getDouble("LATITUDE"), bundle.getDouble("LONGITUDE"));
            //LatLng location = new LatLng( lat,lng);
           // mMap.addMarker(new MarkerOptions().position(location).icon(BitmapDescriptorFactory.fromResource(R.drawable.map_mbiru)));
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
            //mMap.animateCamera(CameraUpdateFactory.zoomTo(13));

    }
}
