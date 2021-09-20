package com.example.otogaleri.RestApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiClient {

    public  static  final  String BaseUrl = "http://yazilimgunlukleri.com";
    public static Retrofit retrofit = null;
    public  static  Retrofit getClient(){

        if(retrofit ==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient().newBuilder()
                            .connectTimeout(40, TimeUnit.SECONDS)
                            .readTimeout(15,TimeUnit.SECONDS)
                            .writeTimeout(15,TimeUnit.SECONDS)
                            .build())
                    .build();
        }
        return retrofit;
    }
}
