package com.example.otogaleri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.otogaleri.Models.AdvertiseResultPojo;

import java.util.ArrayList;
import java.util.List;

public class CarInformation extends AppCompatActivity {

    EditText fiyatEditText,markaEditText,seriEditText,modelEditText,yilEditText,vitesEditText,kmEditText,kasaTipiEditText,motorGücüEditText;
    Button carInformationButon,carInformationButonBack;
    Spinner ilanTuruSpinner,kimdenSpinner;

    List<String> ilanTuruList;
    List<String> kimdenList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_information);
        spinnerList();
        define();
        carInformation();
        carInformationBack();
    }

    public void define(){
        fiyatEditText = findViewById(R.id.fiyatEditText);
        markaEditText = findViewById(R.id.markaEditText);
        seriEditText = findViewById(R.id.seriEditText);
        modelEditText = findViewById(R.id.modelEditText);
        yilEditText = findViewById(R.id.yilEditText);
        vitesEditText = findViewById(R.id.vitesEditText);
        kmEditText = findViewById(R.id.kmEditText);
        kasaTipiEditText = findViewById(R.id.kasaTipiEditText);
        motorGücüEditText = findViewById(R.id.motorGücüEditText);

        ilanTuruSpinner = findViewById(R.id.ilanTuruSpinner);
        kimdenSpinner = findViewById(R.id.kimdenSpinner);

        carInformationButon = findViewById(R.id.carInformationButon);
        carInformationButonBack = findViewById(R.id.carInformationButonBack);

        ArrayAdapter<String> ilanTuruListAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,ilanTuruList);
        ilanTuruListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ilanTuruSpinner.setAdapter(ilanTuruListAdapter);

        ArrayAdapter<String> kimdenListAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,kimdenList);
        kimdenListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kimdenSpinner.setAdapter(kimdenListAdapter);
    }

    public void carInformation(){

        fiyatEditText.setText(AdvertiseResultPojo.getFiyat());
        markaEditText.setText(AdvertiseResultPojo.getMarka());
        seriEditText.setText(AdvertiseResultPojo.getSeri());
        modelEditText.setText(AdvertiseResultPojo.getModel());
        yilEditText.setText(AdvertiseResultPojo.getYil());
        vitesEditText.setText(AdvertiseResultPojo.getVites());
        kmEditText.setText(AdvertiseResultPojo.getKm());
        kasaTipiEditText.setText(AdvertiseResultPojo.getKasa_tipi());
        motorGücüEditText.setText(AdvertiseResultPojo.getMotor_gucu());

       // ilanTuruSpinner.setAdapter(ilanTuruListAdapter);
       // kimdenSpinner.setText(AdvertisePojo.getMotor_gucu());


        carInformationButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  if(!fiyatEditText.getText().toString().equals("") && !markaEditText.getText().toString().equals("") &&
                        !seriEditText.getText().toString().equals("") && !modelEditText.getText().toString().equals("") &&
                        !yilEditText.getText().toString().equals("") && !vitesEditText.getText().toString().equals("") &&
                        !kmEditText.getText().toString().equals("") && !kasaTipiEditText.getText().toString().equals("") &&
                        !motorGücüEditText.getText().toString().equals("")){
*/
                    AdvertiseResultPojo.setFiyat(fiyatEditText.getText().toString());
                    AdvertiseResultPojo.setMarka(markaEditText.getText().toString());
                    AdvertiseResultPojo.setSeri(seriEditText.getText().toString());
                    AdvertiseResultPojo.setModel(modelEditText.getText().toString());
                    AdvertiseResultPojo.setYil(yilEditText.getText().toString());
                    AdvertiseResultPojo.setVites(vitesEditText.getText().toString());
                    AdvertiseResultPojo.setKm(kmEditText.getText().toString());
                    AdvertiseResultPojo.setKasa_tipi(kasaTipiEditText.getText().toString());
                    AdvertiseResultPojo.setMotor_gucu(motorGücüEditText.getText().toString());

                    AdvertiseResultPojo.setIlan_tipi(ilanTuruSpinner.getSelectedItem().toString());
                    AdvertiseResultPojo.setKimden(kimdenSpinner.getSelectedItem().toString());

                    Intent intent = new Intent(CarInformation.this,FuelInformation.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                    finish();
             /*   }
                else{
                    Toast.makeText(CarInformation.this, "Tüm Bilgileri Giriniz", Toast.LENGTH_SHORT).show();
                }
*/
                }
        });
    }
    public void carInformationBack(){
        carInformationButonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CarInformation.this,AddressInformation.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_back,R.anim.anim_out_back);
                finish();
            }
        });
    }

    public void spinnerList(){
        ilanTuruList = new ArrayList<>();
        kimdenList = new ArrayList<>();

        ilanTuruList.add("Satılık");
        ilanTuruList.add("Kiralık");

        kimdenList.add("Sahibinden");
        kimdenList.add("Galeri");
        kimdenList.add("Rent A Car");
    }
}
