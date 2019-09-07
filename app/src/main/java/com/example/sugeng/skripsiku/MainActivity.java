package com.example.sugeng.skripsiku;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;

import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.example.sugeng.skripsiku.Adapter.MainAdapter;
import com.example.sugeng.skripsiku.Data.Model.CallResponse;
import com.example.sugeng.skripsiku.Data.Model.KulinerModel;
import com.example.sugeng.skripsiku.Rest.Api;
import com.example.sugeng.skripsiku.Rest.ApiInterface;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        //private Button btnMap ;
     //  private MainAdapter adapter;

        private GridView gridView;
        private EditText editText;
        private ProgressBar progressBar;
        private TextView textView;

        public static String KULINER_KATEGORI= "";

    private void getKulinerModel(String nama_kuliner, String id_kategori){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<CallResponse> call;
        if (id_kategori == ""){
          call = apiInterface.getKuliner(nama_kuliner);
        }else {
            call= apiInterface.postKategori(id_kategori);
        }


        call.enqueue(new Callback<CallResponse>() {
            @Override
            public void onResponse(Call<CallResponse> call, Response<CallResponse> response) {
                Log.e("_resp", response.toString());

                final List<KulinerModel> kulinerModelList = response.body().getKulinerModelList();
                if (kulinerModelList.size()>0){
                    textView.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);

                    gridView.setVisibility(View.VISIBLE);
                    gridView.setAdapter(new MainAdapter(MainActivity.this,kulinerModelList));
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                            intent.putExtra("NAMA_KULINER", kulinerModelList.get(i).getNama_kuliner());
                            intent.putExtra("ID_KULINER", kulinerModelList.get(i).getId_kuliner());
                            intent.putExtra("LATITUDE", kulinerModelList.get(i).getLat());
                            intent.putExtra("LONGITUDE", kulinerModelList.get(i).getLng());
                            startActivity(intent);
                        }
                    });
                }else{
                    progressBar.setVisibility(View.GONE);
                    gridView.setVisibility(View.GONE);
                    textView.setVisibility(View.VISIBLE);
                }
//                for (int i=0; i< kulinerList.size(); i++){
//                    Log.e("NAMA_KULINER",kulinerList.get(i).getNama_kuliner());
//                }
            }

            @Override
            public void onFailure(Call<CallResponse> call, Throwable t) {
                Log.e("_erroraPa",t.toString());
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (!KULINER_KATEGORI.equals("")){
            editText.setText("");getKulinerModel("",KULINER_KATEGORI);
            KULINER_KATEGORI="";
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        gridView = findViewById(R.id.gridView);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);


        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if ( i == EditorInfo.IME_ACTION_SEARCH){
                    getKulinerModel(editText.getText().toString(),"");
                    return true;
                }
                return false;
            }
        });
        getKulinerModel("","");

    }

    //        getKuliner("");
//        btnMap = findViewById(R.id.btnMap);
//        btnMap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent mapIntent = new Intent(MainActivity.this,MapsActivity.class);
//                startActivity();
//            }
//        });


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //@Override
  //  public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            textView.setText(""); getKulinerModel("","");
        } else if (id == R.id.nav_kategori) {
            startActivity(new Intent(MainActivity.this,CategoryActivity.class));
           // Toast.makeText(getApplicationContext(),"kategori",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(MainActivity.this,MapsActivity.class));

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}