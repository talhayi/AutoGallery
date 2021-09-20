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

import com.example.otogaleri.Models.RegisterPojo;
import com.example.otogaleri.Models.VerificationPojo;
import com.example.otogaleri.RestApi.RestApi;
import com.example.otogaleri.RestApi.RestApiClient;

public class RegisterActivity extends AppCompatActivity {

    EditText emailEditText,passwordEditText,codeEditText;
    Button registerButon,verification;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        define();

        register();

        verification();

    }

    public void define(){

        emailEditText=findViewById(R.id.email);
        passwordEditText=findViewById(R.id.password);
        codeEditText=findViewById(R.id.code);
        registerButon=findViewById(R.id.registerButon);
        verification=findViewById(R.id.verificationButon);

    }


    public void verification(){

        verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String code = codeEditText.getText().toString();
                requestVerification(email,code);

            }
        });

    }
    public void register(){
        registerButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                requestRegister(email,password);

            }
        });
    }

    public  void requestRegister(String email, String password){

        RestApi restApi = RestApiClient.getClient().create(RestApi.class);
        Call<RegisterPojo> call =restApi.register(email,password);

        call.enqueue(new Callback<RegisterPojo>() {
            @Override
            public void onResponse(Call<RegisterPojo>call, Response<RegisterPojo>response) {

                if(response.body().isTf()){
                    Toast.makeText(RegisterActivity.this, "Lütfen doğrulama kodunu giriniz", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(RegisterActivity.this, "Boyle bir kayıt var", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<RegisterPojo> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Bağlantı Hatasıı", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void requestVerification(String email,String code){
        RestApi restApi = RestApiClient.getClient().create(RestApi.class);
        Call<VerificationPojo> call =restApi.verification(email,code);

        call.enqueue(new Callback<VerificationPojo>() {
            @Override
            public void onResponse(Call<VerificationPojo> call, Response<VerificationPojo>response) {
                if(response.body().isTf()){

                    String uye_id = response.body().getId();
                    String email = response.body().getEmail();

                    sharedPreferences = getApplicationContext().getSharedPreferences("giris",0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("uye_id",uye_id);
                    editor.putString("email",email);
                    editor.commit();
                    Intent intent= new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(getApplicationContext(), response.body().getResult(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<VerificationPojo> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Bağlantı Hatası", Toast.LENGTH_LONG).show();
            }
        });
    }
}
