package com.example.otogaleri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.otogaleri.Models.AdvertiseResultPojo;

public class AdvertisementInformations extends AppCompatActivity {

    EditText ilanBaslikEditText,ilanAciklamaEditText;
    Button advertisementInformationButon,advertisementInformationButonBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement_informations);

        define();
        advertisementInformation();
        advertisementInformationBack();
    }

    public  void define(){
        ilanBaslikEditText = findViewById(R.id.ilanBaslikEditText);
        ilanAciklamaEditText = findViewById(R.id.ilanAciklamaEditText);
        advertisementInformationButon = findViewById(R.id.advertisementInformationButon);
        advertisementInformationButonBack = findViewById(R.id.advertisementInformationButonBack);
    }

    public void advertisementInformation(){

        ilanBaslikEditText.setText(AdvertiseResultPojo.getBaslik());
        ilanAciklamaEditText.setText(AdvertiseResultPojo.getAciklama());

        advertisementInformationButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!ilanBaslikEditText.getText().toString().equals("") && !ilanAciklamaEditText.getText().toString().equals("")){

                    AdvertiseResultPojo.setBaslik(ilanBaslikEditText.getText().toString());
                    AdvertiseResultPojo.setAciklama(ilanAciklamaEditText.getText().toString());

                    Intent intent = new Intent(AdvertisementInformations.this,AddressInformation.class);
                    startActivity(intent);

                    overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                    finish();
                }else{
                    Toast.makeText(AdvertisementInformations.this, "Tüm Bilgileri Giriniz", Toast.LENGTH_SHORT).show();
                }
                
            }
        });

    }
    public void advertisementInformationBack(){
        advertisementInformationButonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdvertisementInformations.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_back,R.anim.anim_out_back);
                finish();
            }
        });
    }


}
