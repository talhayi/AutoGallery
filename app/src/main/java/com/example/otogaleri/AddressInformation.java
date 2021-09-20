package com.example.otogaleri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.otogaleri.Models.AdvertiseResultPojo;

public class AddressInformation extends AppCompatActivity {

    EditText sehirEditText,ilceEditText;
    Button addressInformationButon,addressInformationButonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_information);
        define();
        addressInformation();
        addressInformationBack();
    }

    public void define(){

        sehirEditText = findViewById(R.id.sehirEditText);
        ilceEditText = findViewById(R.id.ilceEditText);
        addressInformationButon = findViewById(R.id.addressInformationButon);
        addressInformationButonBack = findViewById(R.id.addressInformationButonBack);
    }
    public void addressInformation(){

        sehirEditText.setText(AdvertiseResultPojo.getSehir());
        ilceEditText.setText(AdvertiseResultPojo.getIlce());

        addressInformationButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //    if(!sehirEditText.getText().toString().equals("") && !ilceEditText.getText().toString().equals("")){

                    AdvertiseResultPojo.setSehir(sehirEditText.getText().toString());
                    AdvertiseResultPojo.setIlce(ilceEditText.getText().toString());

                    Intent intent = new Intent(AddressInformation.this,CarInformation.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                    finish();
              /*  }
                else{
                    Toast.makeText(AddressInformation.this, "Tüm Bilgileri Giriniz", Toast.LENGTH_SHORT).show();
                }
*/
            }
        });

    }

    public void addressInformationBack(){
        addressInformationButonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressInformation.this,AdvertisementInformations.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_back,R.anim.anim_out_back);
                finish();
            }
        });

    }
}
