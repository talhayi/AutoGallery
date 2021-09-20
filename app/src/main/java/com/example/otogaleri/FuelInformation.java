package com.example.otogaleri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.otogaleri.Models.AdvertiseResultPojo;

public class FuelInformation extends AppCompatActivity {

    EditText yakitTuruEditText,ortalamaYakitEditText,depoHacmiEditText;
    Button fuelInformationButon,fuelInformationButonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_information);

        define();
        fuelInformation();
        fuelInformationBack();
    }
    public void define(){

        yakitTuruEditText = findViewById(R.id.yakitTuruEditText);
        ortalamaYakitEditText = findViewById(R.id.ortalamaYakitEditText);
        depoHacmiEditText = findViewById(R.id.depoHacmiEditText);
        fuelInformationButon = findViewById(R.id.fuelInformationButon);
        fuelInformationButonBack = findViewById(R.id.fuelInformationButonBack);
    }

    public  void fuelInformation(){

        yakitTuruEditText.setText(AdvertiseResultPojo.getYakit_turu());
        ortalamaYakitEditText.setText(AdvertiseResultPojo.getOrtalama_yakit());
        depoHacmiEditText.setText(AdvertiseResultPojo.getDepo_hacmi());

        fuelInformationButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             /*   if(!yakitTuruEditText.getText().toString().equals("") && !ortalamaYakitEditText.getText().toString().equals("") &&
                        !depoHacmiEditText.getText().toString().equals("")){
*/
                    AdvertiseResultPojo.setYakit_turu(yakitTuruEditText.getText().toString());
                    AdvertiseResultPojo.setOrtalama_yakit(ortalamaYakitEditText.getText().toString());
                    AdvertiseResultPojo.setDepo_hacmi(depoHacmiEditText.getText().toString());

                    Intent intent = new Intent(FuelInformation.this,UploadImages.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                    finish();
               /* }else{
                    Toast.makeText(FuelInformation.this, "Tüm Bilgileri Giriniz", Toast.LENGTH_SHORT).show();
                }
*/
            }
        });
    }
    public  void fuelInformationBack(){
        fuelInformationButonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FuelInformation.this,CarInformation.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_back,R.anim.anim_out_back);
                finish();
            }
        });
    }
}
