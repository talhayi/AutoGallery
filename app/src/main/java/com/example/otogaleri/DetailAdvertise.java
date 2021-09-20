package com.example.otogaleri;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.otogaleri.Adepters.SliderAdapter;
import com.example.otogaleri.Models.ChangeFavoritePojo;
import com.example.otogaleri.Models.DetailAdvertisePojo;
import com.example.otogaleri.Models.FavoriteAdvertisePojo;
import com.example.otogaleri.Models.SliderPojo;
import com.example.otogaleri.RestApi.RestApi;
import com.example.otogaleri.RestApi.RestApiClient;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailAdvertise extends AppCompatActivity {

    private TextView ilanDetayBaslik, ilanDetayFiyat, ilanDetayMarka, ilanDetayModel, ilanDetaySeri, ilanDetayYil, ilanDetaIlanTipi,
            ilanDetayKimden, ilanDetayVites, ilanDetayKm, ilanDetayKasaTipi, ilanDetayMotor, ilanDetayYakitTuru,
            İlanDetayOrtalamaYakit, ilanDetayDepoHacmi;
    private Button ilanDetayFavoriyeAlButon;

    private ViewPager ilanDetaySlider;

    String ilanId, uyeId;
    List<SliderPojo> list;
    SliderAdapter sliderAdapter;
    CircleIndicator circleIndicator;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_advertise);
        define();
        Bundle bundle = getIntent().getExtras();
        ilanId = bundle.getString("ilanid");
        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);

        uyeId = sharedPreferences.getString("uye_id", null);


        requestDetailAdvertise(ilanId);
        requestDetailAdvertiseImage(ilanId);
        requestFavoriteAdvertise(ilanId, uyeId);
        changeFavoriteButon();
    }

    public void define() {

        ilanDetayBaslik = findViewById(R.id.ilanDetayBaslik);
        ilanDetayFiyat = findViewById(R.id.ilanDetayFiyat);
        ilanDetayMarka = findViewById(R.id.ilanDetayMarka);
        ilanDetayModel = findViewById(R.id.ilanDetayModel);
        ilanDetaySeri = findViewById(R.id.ilanDetaySeri);
        ilanDetayYil = findViewById(R.id.ilanDetayYil);
        ilanDetaIlanTipi = findViewById(R.id.ilanDetaIlanTipi);
        ilanDetayKimden = findViewById(R.id.ilanDetayKimden);
        ilanDetayVites = findViewById(R.id.ilanDetayVites);
        ilanDetayKm = findViewById(R.id.ilanDetayKm);
        ilanDetayKasaTipi = findViewById(R.id.ilanDetayKasaTipi);
        ilanDetayMotor = findViewById(R.id.ilanDetayMotor);
        ilanDetayYakitTuru = findViewById(R.id.ilanDetayYakitTuru);
        İlanDetayOrtalamaYakit = findViewById(R.id.İlanDetayOrtalamaYakit);
        ilanDetayDepoHacmi = findViewById(R.id.ilanDetayDepoHacmi);

        ilanDetayFavoriyeAlButon = findViewById(R.id.ilanDetayFavoriyeAlButon);

        ilanDetaySlider = findViewById(R.id.ilanDetaySlider);

        circleIndicator = findViewById(R.id.sliderNokta);

    }

    public void requestDetailAdvertise(String ilan_id) {

        RestApi restApi = RestApiClient.getClient().create(RestApi.class);
        Call<DetailAdvertisePojo> call = restApi.detailAdvertise(ilan_id);
        call.enqueue(new Callback<DetailAdvertisePojo>() {
            @Override
            public void onResponse(Call<DetailAdvertisePojo> call, Response<DetailAdvertisePojo> response) {


                ilanDetayBaslik.setText(response.body().getBaslik());
                ilanDetayFiyat.setText(response.body().getFiyat());
                ilanDetayMarka.setText(response.body().getMarka());
                ilanDetayModel.setText(response.body().getModel());
                ilanDetaySeri.setText(response.body().getSeri());
                ilanDetayYil.setText(response.body().getYil());
                ilanDetaIlanTipi.setText(response.body().getIlantipi());
                ilanDetayKimden.setText(response.body().getKimden());
                ilanDetayVites.setText(response.body().getVites());
                ilanDetayKm.setText(response.body().getKm());
                ilanDetayKasaTipi.setText(response.body().getKasatipi());
                ilanDetayMotor.setText(response.body().getMotorgucu());
                ilanDetayYakitTuru.setText(response.body().getYakitturu());
                İlanDetayOrtalamaYakit.setText(response.body().getOrtalamayakit());
                ilanDetayDepoHacmi.setText(response.body().getDepohacmi());


            }

            @Override
            public void onFailure(Call<DetailAdvertisePojo> call, Throwable t) {
                Toast.makeText(DetailAdvertise.this, "Bağlantı Hatası", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void requestDetailAdvertiseImage(String ilan_id) {

        RestApi restApi = RestApiClient.getClient().create(RestApi.class);
        Call<List<SliderPojo>> call = restApi.detailAdvertiseImage(ilan_id);
        call.enqueue(new Callback<List<SliderPojo>>() {
            @Override
            public void onResponse(Call<List<SliderPojo>> call, Response<List<SliderPojo>> response) {
                list = response.body();
                sliderAdapter = new SliderAdapter(list, getApplicationContext());
                ilanDetaySlider.setAdapter(sliderAdapter);
                circleIndicator.setViewPager(ilanDetaySlider);
                circleIndicator.bringToFront();

            }

            @Override
            public void onFailure(Call<List<SliderPojo>> call, Throwable t) {
                Toast.makeText(DetailAdvertise.this, "Bağlantı Hatası", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void requestFavoriteAdvertise(String ilan_id, String uye_id) {

        RestApi restApi = RestApiClient.getClient().create(RestApi.class);
        Call<FavoriteAdvertisePojo> call = restApi.favoriteAdvertise(ilan_id, uye_id);

        call.enqueue(new Callback<FavoriteAdvertisePojo>() {
            @Override
            public void onResponse(Call<FavoriteAdvertisePojo> call, Response<FavoriteAdvertisePojo> response) {

                if (response.body().isTf()) {
                    ilanDetayFavoriyeAlButon.setText(response.body().getText());

                } else {
                    ilanDetayFavoriyeAlButon.setText(response.body().getText());
                }
            }

            @Override
            public void onFailure(Call<FavoriteAdvertisePojo> call, Throwable t) {
                Toast.makeText(DetailAdvertise.this, "Bağlantı Hatası", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void requestChangeFavorite(String ilan_id, String uye_id) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Favoriler");
        progressDialog.setMessage("Lütfen Bekleyiniz");
        progressDialog.setCancelable(false);
        progressDialog.show();

        RestApi restApi = RestApiClient.getClient().create(RestApi.class);
        Call<ChangeFavoritePojo> call = restApi.changeFavorite(ilan_id, uye_id);
        call.enqueue(new Callback<ChangeFavoritePojo>() {
            @Override
            public void onResponse(Call<ChangeFavoritePojo> call, Response<ChangeFavoritePojo> response) {
                if (response.body().isTf()) {
                    progressDialog.cancel();
                    requestFavoriteAdvertise(ilanId, uyeId);


                } else {
                    progressDialog.cancel();
                    requestFavoriteAdvertise(ilanId, uyeId);

                }
            }

            @Override
            public void onFailure(Call<ChangeFavoritePojo> call, Throwable t) {
                Toast.makeText(DetailAdvertise.this, "Bağlantı Hatası", Toast.LENGTH_SHORT).show();
                progressDialog.cancel();
            }
        });

    }

    public void changeFavoriteButon() {

        ilanDetayFavoriyeAlButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestChangeFavorite(ilanId, uyeId);
            }
        });
    }
}
