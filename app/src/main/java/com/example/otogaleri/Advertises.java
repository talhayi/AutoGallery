package com.example.otogaleri;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.otogaleri.Adepters.AdvertisesAdapter;
import com.example.otogaleri.Adepters.MyAdvertiseAdapter;
import com.example.otogaleri.Models.AdvertisesPojo;
import com.example.otogaleri.Models.MyAdvertisePojo;
import com.example.otogaleri.RestApi.RestApi;
import com.example.otogaleri.RestApi.RestApiClient;

import java.util.ArrayList;
import java.util.List;

public class Advertises extends AppCompatActivity {

    ListView listView;
    List<AdvertisesPojo> advertisesPojoList;
    AdvertisesAdapter advertisesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertises);


        define();
        requestAdvertises();

    }

    public  void  define(){

        listView = findViewById(R.id.advertisesListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(Advertises.this,DetailAdvertise.class);
                intent.putExtra("ilanid",advertisesPojoList.get(position).getIlanid());
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                finish();
            }
        });
    }

    public void requestAdvertises(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("İlanlar");
        progressDialog.setMessage("İlanlar Yükleniyor , Lütfen Bekleyiniz");
        progressDialog.setCancelable(false);
        progressDialog.show();
        advertisesPojoList = new ArrayList<>();
        RestApi restApi = RestApiClient.getClient().create(RestApi.class);
        Call<List<AdvertisesPojo>> call =restApi.advertises();
        call.enqueue(new Callback<List<AdvertisesPojo>>() {
            @Override
            public void onResponse(Call<List<AdvertisesPojo>> call, Response<List<AdvertisesPojo>> response) {
                if(response.isSuccessful()){
                    //advertisesPojoList=response.body();
                    if(response.body().get(0).isTf()){

                        advertisesPojoList = response.body();
                        advertisesAdapter = new AdvertisesAdapter(advertisesPojoList, Advertises.this);
                        listView.setAdapter(advertisesAdapter);

                        Toast.makeText(Advertises.this, response.body().get(0).getSayi()+" tane ilan bulunmaktadır.", Toast.LENGTH_LONG).show();
                        progressDialog.cancel();
                    }else{
                        Toast.makeText(Advertises.this, "İlanınız Bulunmamaktadır.", Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                        Intent intent = new Intent(Advertises.this,MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<AdvertisesPojo>> call, Throwable t) {
                Toast.makeText(Advertises.this, "Bağlantı Hatası", Toast.LENGTH_SHORT).show();
                progressDialog.cancel();
            }
        });

    }

}
