package com.example.otogaleri.RestApi;

import com.example.otogaleri.Models.AddAdvertiseImagePojo;
import com.example.otogaleri.Models.AdvertisePojo;
import com.example.otogaleri.Models.AdvertisesPojo;
import com.example.otogaleri.Models.ChangeFavoritePojo;
import com.example.otogaleri.Models.DeleteMyAdvertisePojo;
import com.example.otogaleri.Models.DetailAdvertisePojo;
import com.example.otogaleri.Models.FavoriteAdvertisePojo;
import com.example.otogaleri.Models.FavoriteAdvertiseSliderPojo;
import com.example.otogaleri.Models.MyAdvertisePojo;
import com.example.otogaleri.Models.LoginPojo;
import com.example.otogaleri.Models.PersonalInformationPojo;
import com.example.otogaleri.Models.RegisterPojo;
import com.example.otogaleri.Models.SliderPojo;
import com.example.otogaleri.Models.UpdatePersonalInformationPojo;
import com.example.otogaleri.Models.VerificationPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestApi {

@FormUrlEncoded
    @POST("/autogallery/login.php")
    Call<LoginPojo> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("/autogallery/register.php")
    Call<RegisterPojo> register(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("/autogallery/verification.php")
    Call<VerificationPojo> verification(@Field("email") String email, @Field("code") String code);

    @FormUrlEncoded
    @POST("/autogallery/advertise.php")
    Call<AdvertisePojo> advertise(@Field("uye_id") String uye_id, @Field("sehir") String sehir,
                                  @Field("ilce") String ilce, @Field("fiyat") String fiyat,
                                  @Field("marka") String marka, @Field("seri") String seri,
                                  @Field("model") String model, @Field("yil") String yil,
                                  @Field("vites") String vites, @Field("km") String km,
                                  @Field("kasa_tipi") String kasa_tipi, @Field("motor_gucu") String motor_gucu,
                                  @Field("ilan_tipi") String ilan_tipi, @Field("kimden") String kimden,
                                  @Field("baslik") String baslik, @Field("aciklama") String aciklama,
                                  @Field("yakit_turu") String yakit_turu, @Field("ortalama_yakit") String ortalama_yakit,
                                  @Field("depo_hacmi") String depo_hacmi);

    @FormUrlEncoded
    @POST("/autogallery/addAdvertiseImage.php")
    Call<AddAdvertiseImagePojo> addAdvertiseImage(@Field("uye_id") String uye_id, @Field("ilan_id") String ilan_id, @Field("image") String image);


    @GET("/autogallery/myAdvertise.php")
    Call<List<MyAdvertisePojo>> myAdvertise(@Query("uye_id") String uye_id);

    @GET("/autogallery/deleteMyAdvertise.php")
    Call<DeleteMyAdvertisePojo>deleteMyAdvertise(@Query("ilan_id") String ilan_id);

    @GET("/autogallery/advertises.php")
    Call<List<AdvertisesPojo>>advertises();


    @GET("/autogallery/detailAdvertise.php")
    Call<DetailAdvertisePojo> detailAdvertise(@Query("ilan_id") String ilan_id);

    @GET("/autogallery/detailAdvertiseImage.php")
    Call<List<SliderPojo>>detailAdvertiseImage(@Query("ilan_id") String ilan_id);

    @GET("/autogallery/favoriteAdvertise.php")
    Call<FavoriteAdvertisePojo> favoriteAdvertise(@Query("ilan_id") String ilan_id,@Query("uye_id") String uye_id);

    @GET("/autogallery/changeFavorite.php")
    Call<ChangeFavoritePojo> changeFavorite(@Query("ilan_id") String ilan_id, @Query("uye_id") String uye_id);

    @GET("/autogallery/favoriteAdvertiseSlider.php")
    Call<List<FavoriteAdvertiseSliderPojo>> favoriteAdvertiseSlider(@Query("uye_id") String uye_id);

    @GET("/autogallery/personalInformation.php")
    Call<PersonalInformationPojo> personalInformation(@Query("uye_id") String uye_id);

    @GET("/autogallery/updatePersonalInformation.php")
    Call<UpdatePersonalInformationPojo> updatePersonalInformation(@Query("uye_id") String uye_id, @Query("email") String email,@Query("password") String password);

}
