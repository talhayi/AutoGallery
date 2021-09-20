package com.example.otogaleri;

import  androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.otogaleri.Models.AddAdvertiseImagePojo;
import com.example.otogaleri.Models.AdvertisePojo;
import com.example.otogaleri.Models.AdvertiseResultPojo;
import com.example.otogaleri.RestApi.RestApi;
import com.example.otogaleri.RestApi.RestApiClient;

import java.io.ByteArrayOutputStream;

import java.io.IOException;



public class UploadImages extends AppCompatActivity {

    Button postAdvertiseButon,selectImageButon,addImageButon,uploadComeBackButon,uploadLogoutButon;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ImageView advertiseImageView;
    Bitmap bitmap;
    String uye_id2,ilan_id2,image2,sonuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_images);

        define();
        postAdvertise();
        selectImage();
        addImage();
        uploadComeBack();
        uploadLogout();
    }
    public void define(){
        sharedPreferences = getApplicationContext().getSharedPreferences("giris",0);
        AdvertiseResultPojo.setUye_id(sharedPreferences.getString("uye_id",null));
        postAdvertiseButon = findViewById(R.id.postAdvertiseButon);
        selectImageButon = findViewById(R.id.selectImageButon);
        addImageButon = findViewById(R.id.addImageButon);
        advertiseImageView = findViewById(R.id.advertiseImageView);
        uploadComeBackButon = findViewById(R.id.uploadComeBackButon);
        uploadLogoutButon = findViewById(R.id.uploadLogoutButon);

    }
    public void postAdvertise(){
        postAdvertiseButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 requestAdvertise(AdvertiseResultPojo.getUye_id(),AdvertiseResultPojo.getSehir(),AdvertiseResultPojo.getIlce(),
                         AdvertiseResultPojo.getFiyat(),AdvertiseResultPojo.getMarka(),AdvertiseResultPojo.getSeri(),
                         AdvertiseResultPojo.getModel(), AdvertiseResultPojo.getYil(),AdvertiseResultPojo.getVites(),
                         AdvertiseResultPojo.getKm(),AdvertiseResultPojo.getKasa_tipi(), AdvertiseResultPojo.getMotor_gucu(),
                         AdvertiseResultPojo.getIlan_tipi(),AdvertiseResultPojo.getKimden(),AdvertiseResultPojo.getBaslik(),
                         AdvertiseResultPojo.getAciklama(),AdvertiseResultPojo.getYakit_turu(),AdvertiseResultPojo.getOrtalama_yakit(),
                         AdvertiseResultPojo.getDepo_hacmi());
            }
        });
    }
    public void requestAdvertise(String uye_id, String sehir, String ilce, String fiyat, String marka, String seri, String model,
                                 String yil, String vites, String km, String kasa_tipi, String motor_gucu, String ilan_tipi,
                                 String kimden, String baslik, String aciklama, String yakit_turu, String ortalama_yakit, String depo_hacmi){
        RestApi restApi = RestApiClient.getClient().create(RestApi.class);
        Call<AdvertisePojo> call =restApi.advertise(uye_id,sehir,ilce,fiyat,marka,seri,model,yil,vites,km,
                kasa_tipi,motor_gucu,ilan_tipi,kimden,baslik,aciklama,yakit_turu,ortalama_yakit,depo_hacmi);
        call.enqueue(new Callback<AdvertisePojo>() {
            @Override
            public void onResponse(Call<AdvertisePojo> call, Response<AdvertisePojo> response) {
                if(response.body().isTf()){

                    Toast.makeText(UploadImages.this, "İlanınız Yayınlanmıştır", Toast.LENGTH_LONG).show();
                    uye_id2 = response.body().getUyeid();
                    ilan_id2 = response.body().getIlanid();

                    Log.i("test1",uye_id2+"      "+ilan_id2 );
                }else{
                    Toast.makeText(UploadImages.this, "İlanınız Yayınlanmamıştır", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<AdvertisePojo> call, Throwable t) {
                Toast.makeText(UploadImages.this, "Bağlantı Hatası ", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void addImage(){
        addImageButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image2 = imageToString();
                requestUploadImage(uye_id2,ilan_id2,image2);
            }
        });
    }
    public void requestUploadImage(final String uye_id, String ilan_id,String image){

        RestApi restApi = RestApiClient.getClient().create(RestApi.class);
        Call<AddAdvertiseImagePojo> call =restApi.addAdvertiseImage(uye_id,ilan_id,image);
        call.enqueue(new Callback<AddAdvertiseImagePojo>() {
            @Override
            public void onResponse(Call<AddAdvertiseImagePojo> call, Response<AddAdvertiseImagePojo> response) {
                if(response.body().isTf()){
                   uye_id2 =response.body().getUyeid();
                   ilan_id2=response.body().getIlanid();
                    sonuc = response.body().getSonuc();

                    Log.i("test2",uye_id2+"      "+ilan_id2+"            "+sonuc+"         "+image2);
                    Toast.makeText(UploadImages.this, "Resim Yüklendi", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(UploadImages.this, "Resim Yüklenmedi", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AddAdvertiseImagePojo> call, Throwable t) {
                Toast.makeText(UploadImages.this, "Bağlantı Hatası", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void showImage(){    //galeriyi açmak için

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,777);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==777 && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();
            Log.i("deneme", String.valueOf(path));
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                advertiseImageView.setImageBitmap(bitmap);
                advertiseImageView.setVisibility(View.VISIBLE);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public String imageToString(){      // resmi base64 Stringine çevirmek için

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        byte[] byt = byteArrayOutputStream.toByteArray();
        String imageToString = Base64.encodeToString(byt,Base64.DEFAULT);
        return  imageToString;

        /*
        Bitmap smallImage = makeSmallerImage(bitmap,300);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        smallImage.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] byt = byteArrayOutputStream.toByteArray();
        String imageToString = Base64.encodeToString(byt,Base64.DEFAULT);
        return  imageToString;*/
    }

    public void selectImage(){
        selectImageButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage();
            }
        });
    }
    /*
    public Bitmap makeSmallerImage(Bitmap image, int maximumSize){

        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width/ (float)height;
        if(bitmapRatio>1){

            width = maximumSize;
            height = (int) (width/bitmapRatio);
        }else{
            height = maximumSize;
            width = (int) (height*bitmapRatio);
        }

        return  Bitmap.createScaledBitmap(image,width,height,true);
    }
    */

    public void uploadComeBack(){

        uploadComeBackButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UploadImages.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                finish();
            }
        });
    }

    public  void uploadLogout(){
        uploadLogoutButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getApplicationContext().getSharedPreferences("giris",0);
                editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
