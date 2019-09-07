package com.example.sugeng.skripsiku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sugeng.skripsiku.Adapter.CategoryAdapter;
import com.example.sugeng.skripsiku.Adapter.MainAdapter;
import com.example.sugeng.skripsiku.Data.Model.CallResponse;
import com.example.sugeng.skripsiku.Data.Model.Category;
import com.example.sugeng.skripsiku.Rest.Api;
import com.example.sugeng.skripsiku.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {

    private ListView listView;

    private void getCategory(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);

        Call<CallResponse> call = apiInterface.getKategori();

        call.enqueue(new Callback<CallResponse>() {
            @Override
            public void onResponse(Call<CallResponse> call, Response<CallResponse> response) {
                Log.e("_resp", response.toString());
                final List<Category> kategori = response.body().getKategoris();

                listView.setAdapter(new CategoryAdapter(CategoryActivity.this,kategori));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //Toast.makeText(getApplicationContext(),kategori.get(i).getId_kategori(),Toast.LENGTH_LONG).show();
                        MainActivity.KULINER_KATEGORI = kategori.get(i).getId_kategori();
                        finish();
                    }
                });



//                for (int i=0; i< kategori.size(); i++){
//                    Log.e("KATEGORI",kategori.get(i).getNama_kategori());
//                }

            }

            @Override
            public void onFailure(Call<CallResponse> call, Throwable t) {
                Log.e("_err",t.toString());
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        listView  = findViewById(R.id.listView);
        getCategory();
        getSupportActionBar().setTitle("Kategori");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
