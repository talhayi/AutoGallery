package com.example.otogaleri;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.otogaleri.Adepters.MyAdvertiseAdapter;
import com.example.otogaleri.Models.DeleteMyAdvertisePojo;
import com.example.otogaleri.Models.MyAdvertisePojo;
import com.example.otogaleri.RestApi.RestApi;
import com.example.otogaleri.RestApi.RestApiClient;

import java.util.ArrayList;
import java.util.List;

public class MyAdvertise extends AppCompatActivity {

    ListView listView;
    MyAdvertiseAdapter myAdvertiseAdapter;
    List<MyAdvertisePojo> myAdvertisePojos;
    SharedPreferences sharedPreferences;
    String uye_id2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_advertise);
        define();

        requestMyAdvertise(uye_id2);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                myAdvertiseAlertDialog(MyAdvertise.this,myAdvertisePojos.get(position).getIlanid());
            }
        });
    }

    public  void  define(){
        sharedPreferences=getApplicationContext().getSharedPreferences("giris",0);
        uye_id2 = sharedPreferences.getString("uye_id",null);
        listView = findViewById(R.id.myAdvertiseListView);
    }

    public void requestMyAdvertise(String uye_id){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("İlanlarım");
        progressDialog.setMessage("İlanlarınız Yükleniyor , Lütfen Bekleyiniz");
        progressDialog.setCancelable(false);
        progressDialog.show();
        myAdvertisePojos = new ArrayList<>();
        RestApi restApi = RestApiClient.getClient().create(RestApi.class);
        Call<List<MyAdvertisePojo>> call =restApi.myAdvertise(uye_id);
        call.enqueue(new Callback<List<MyAdvertisePojo>>() {
           @Override
           public void onResponse(Call<List<MyAdvertisePojo>> call, Response<List<MyAdvertisePojo>> response) {
               if (response.isSuccessful()){
                   if(response.body().get(0).isTf()){
                       myAdvertisePojos = response.body();
                       myAdvertiseAdapter = new MyAdvertiseAdapter(myAdvertisePojos,getApplicationContext(),MyAdvertise.this);
                       listView.setAdapter(myAdvertiseAdapter);
                       Toast.makeText(MyAdvertise.this, response.body().get(0).getSayi()+" tane ilanınız bulunmaktadır.", Toast.LENGTH_LONG).show();
                       progressDialog.cancel();
                   }
                   else{
                       Toast.makeText(MyAdvertise.this, "İlanınız Bulunmamaktadır.", Toast.LENGTH_SHORT).show();
                       progressDialog.cancel();
                       Intent intent = new Intent(MyAdvertise.this,MainActivity.class);
                       startActivity(intent);
                       overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                       finish();
                   }
               }

           }

           @Override
           public void onFailure(Call<List<MyAdvertisePojo>> call, Throwable t) {
               Toast.makeText(MyAdvertise.this, "Bağlantı Hatası", Toast.LENGTH_SHORT).show();
               progressDialog.cancel();
           }
       });
    }


    public  void myAdvertiseAlertDialog(Activity activity, final String ilan_id2){

        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.alertdialoglayout,null);

        Button deleteButon = view.findViewById(R.id.deleteButon);
        Button cancelButon = view.findViewById(R.id.cancelButon);


        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setView(view);
        alert.setCancelable(false);
        final AlertDialog dialog = alert.create();

        cancelButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        deleteButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                requestDeleteMyAdvertise(ilan_id2);
                dialog.cancel();
            }
        });
        dialog.show();
    }

    public void requestDeleteMyAdvertise(String ilan_id){

        RestApi restApi = RestApiClient.getClient().create(RestApi.class);
        Call<DeleteMyAdvertisePojo> call =restApi.deleteMyAdvertise(ilan_id);
        call.enqueue(new Callback<DeleteMyAdvertisePojo>() {
            @Override
            public void onResponse(Call<DeleteMyAdvertisePojo> call, Response<DeleteMyAdvertisePojo> response) {

                if(response.body().isTf()){

                    requestMyAdvertise(uye_id2);
                }
            }

            @Override
            public void onFailure(Call<DeleteMyAdvertisePojo> call, Throwable t) {
                Toast.makeText(MyAdvertise.this, "Bağlantı Hatası", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
