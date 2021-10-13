package com.example.otogaleri;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.otogaleri.Models.LoginPojo;
import com.example.otogaleri.RestApi.RestApi;
import com.example.otogaleri.RestApi.RestApiClient;

public class LoginActivity extends AppCompatActivity {

    EditText emailEditText,passwordEditText;
    Button loginButon;
    TextView registerText;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        define();
        login();
        register();
        sharedPreferences();

    }
    public  void sharedPreferences(){
        sharedPreferences=getApplicationContext().getSharedPreferences("giris",0);

                if(sharedPreferences.getString("uye_id",null)!=null &&sharedPreferences.getString("email",null)!=null){
            Intent intent= new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }
    public void define(){

        emailEditText=findViewById(R.id.email);
        passwordEditText=findViewById(R.id.password);
        loginButon=findViewById(R.id.login);
        registerText=findViewById(R.id.registerText);
    }
    public  void register (){
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


    public void login(){

        loginButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email= emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                System.out.println(email);

                requestLogin(email,password);

            }
        });

    }
    public void requestLogin(String email,String password){

        RestApi restApi = RestApiClient.getClient().create(RestApi.class);
        Call<LoginPojo> call =restApi.login(email,password);
        call.enqueue(new Callback<LoginPojo>() {
            @Override
            public void onResponse(Call<LoginPojo> call, Response<LoginPojo> response) {

                if(response.isSuccessful()){
                    if(response.body().getKadi()!=null && response.body().getId()!=null){

                        String uyeId = response.body().getId();
                        String email = response.body().getKadi();
                        sharedPreferences = getApplicationContext().getSharedPreferences("giris",0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("uye_id",uyeId);
                        editor.putString("email",email);
                        editor.commit();
                        Intent intent= new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Başarılı", Toast.LENGTH_LONG).show();

                    }else {

                            Toast.makeText(getApplicationContext(), "Mailiniz veya Şifreniz Hatalıdır", Toast.LENGTH_LONG).show();
                        }
                    }else {
                            Toast.makeText(getApplicationContext(), "Sunucuya erişelemiyor", Toast.LENGTH_LONG).show();
                }
                }




            @Override
            public void onFailure(Call<LoginPojo> call, Throwable t) {
               // Intent intent= new Intent(LoginActivity.this,MainActivity.class);
               // startActivity(intent);

                Toast.makeText(getApplicationContext(), "Bağlantı Hatasıi", Toast.LENGTH_LONG).show();
            }
        });
    }
}
