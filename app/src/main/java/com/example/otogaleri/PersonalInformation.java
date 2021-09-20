package com.example.otogaleri;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.otogaleri.Models.PersonalInformationPojo;
import com.example.otogaleri.Models.UpdatePersonalInformationPojo;
import com.example.otogaleri.RestApi.RestApi;
import com.example.otogaleri.RestApi.RestApiClient;

public class PersonalInformation extends AppCompatActivity {

    EditText emailUpdateEditText,passwordUpdateEditText;
    Button updateButon;
    SharedPreferences sharedPreferences;
    String uyeId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        define();

        requestPersonalInformation(uyeId);
        updatePersonalInformation();
    }

    public void define(){

        sharedPreferences=getApplicationContext().getSharedPreferences("giris",0);
        uyeId = sharedPreferences.getString("uye_id",null);
        emailUpdateEditText = findViewById(R.id.emailUpdateEditText);
        passwordUpdateEditText = findViewById(R.id.passwordUpdateEditText);
        updateButon = findViewById(R.id.updateButon);

    }

    public  void requestPersonalInformation(String uye_id){

        RestApi restApi = RestApiClient.getClient().create(RestApi.class);
        Call<PersonalInformationPojo> call =restApi.personalInformation(uye_id);
        call.enqueue(new Callback<PersonalInformationPojo>() {
            @Override
            public void onResponse(Call<PersonalInformationPojo> call, Response<PersonalInformationPojo> response) {

                if(response.isSuccessful()){

                    emailUpdateEditText.setText(response.body().getEmail());
                    passwordUpdateEditText.setText(response.body().getPassword());
                }

            }

            @Override
            public void onFailure(Call<PersonalInformationPojo> call, Throwable t) {

            }
        });
    }

    public  void requestUpdatePersonalInformation(String uye_id,String email,String password){
        RestApi restApi = RestApiClient.getClient().create(RestApi.class);
        Call<UpdatePersonalInformationPojo> call =restApi.updatePersonalInformation(uye_id,email,password);
        call.enqueue(new Callback<UpdatePersonalInformationPojo>() {
            @Override
            public void onResponse(Call<UpdatePersonalInformationPojo> call, Response<UpdatePersonalInformationPojo> response) {
                if(response.body().isTf()){

                    emailUpdateEditText.setText(response.body().getEmail());
                    passwordUpdateEditText.setText(response.body().getPassword());
                    Toast.makeText(PersonalInformation.this, "Güncellendi", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UpdatePersonalInformationPojo> call, Throwable t) {
                Toast.makeText(PersonalInformation.this, "Bağlantı Hatası", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void updatePersonalInformation(){

        updateButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestUpdatePersonalInformation(uyeId,emailUpdateEditText.getText().toString(),passwordUpdateEditText.getText().toString());
            }
        });
    }
}
