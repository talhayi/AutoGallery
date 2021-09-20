package com.example.otogaleri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.otogaleri.Adepters.FavoriteSliderAdapter;
import com.example.otogaleri.Models.FavoriteAdvertiseSliderPojo;
import com.example.otogaleri.Models.PersonalInformationPojo;
import com.example.otogaleri.RestApi.RestApi;
import com.example.otogaleri.RestApi.RestApiClient;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button logoutButon,advertiseButon,myAdvertiseButon,advertisesButon,personalInformationButon;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    ViewPager mainActivitySliderFavori;
    CircleIndicator mainActivitySliderCircle;
    String uyeId;
    FavoriteSliderAdapter favoriteSliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        define();
        logout();
        advertise();
        myAdvertise();
        advertises();
        requestFavoriteSlider(uyeId);
        //personalInformation();
    }

    public void define(){
        sharedPreferences=getApplicationContext().getSharedPreferences("giris",0);
        uyeId = sharedPreferences.getString("uye_id",null);
        logoutButon = findViewById(R.id.logoutButon);
        advertiseButon = findViewById(R.id.advertiseButon);
        myAdvertiseButon = findViewById(R.id.myAdvertiseButon);
        advertisesButon = findViewById(R.id.advertisesButon);
      //  personalInformationButon = findViewById(R.id.personalInformationButon);

        mainActivitySliderFavori = findViewById(R.id.mainActivitySliderFavori);

        mainActivitySliderCircle = findViewById(R.id.mainActivitySliderCircle);

    }

    public  void advertises(){
        advertisesButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Advertises.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });
    }
    public void myAdvertise(){
        myAdvertiseButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,MyAdvertise.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });

    }
    public void logout(){

        logoutButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    public void advertise(){
        advertiseButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AdvertisementInformations.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });

    }

    public void requestFavoriteSlider(String uye_id){
        RestApi restApi = RestApiClient.getClient().create(RestApi.class);
        Call<List<FavoriteAdvertiseSliderPojo>> call =restApi.favoriteAdvertiseSlider(uye_id);
        call.enqueue(new Callback<List<FavoriteAdvertiseSliderPojo>>() {
            @Override
            public void onResponse(Call<List<FavoriteAdvertiseSliderPojo>> call, Response<List<FavoriteAdvertiseSliderPojo>> response) {

                if(response.body().get(0).isTf()){
                    if(response.body().size()>0){
                        favoriteSliderAdapter = new FavoriteSliderAdapter(response.body(),MainActivity.this,MainActivity.this);
                        mainActivitySliderFavori.setAdapter(favoriteSliderAdapter);
                        mainActivitySliderCircle.setViewPager(mainActivitySliderFavori);
                        mainActivitySliderCircle.bringToFront();
                    }


                }else{
                    favoriteSliderAdapter = new FavoriteSliderAdapter(response.body(),MainActivity.this,MainActivity.this);
                    mainActivitySliderFavori.setAdapter(favoriteSliderAdapter);
                    mainActivitySliderCircle.setViewPager(mainActivitySliderFavori);
                    mainActivitySliderCircle.bringToFront();

                }

            }

            @Override
            public void onFailure(Call<List<FavoriteAdvertiseSliderPojo>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Bağlantı Hatası", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        requestFavoriteSlider(uyeId);
    }
/*
    public void personalInformation(){
        personalInformationButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PersonalInformation.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });
    }*/
}
