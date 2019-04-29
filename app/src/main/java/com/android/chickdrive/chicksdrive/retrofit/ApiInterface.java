package com.android.chickdrive.chicksdrive.retrofit;

import com.android.chickdrive.chicksdrive.models.CityDM;
import com.android.chickdrive.chicksdrive.models.HomeListingDM;
import com.android.chickdrive.chicksdrive.models.LoginResponseDM;
import com.android.chickdrive.chicksdrive.models.UserTypesDM;
import com.android.chickdrive.chicksdrive.models.more.GenericMedFeedHatchDM;
import com.android.chickdrive.chicksdrive.models.rates.TodayRatesDM;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("HomepageBLGeneric")
    Call<HomeListingDM> gethomeList();

    @GET("userLogin/")
    Call<LoginResponseDM> logInCall(
            @Query("contact") String contact,
            @Query("password") String password);

    @GET("api/tblCities")
    Call<List<CityDM>> getMeCity();


    @GET("api/UserTypes")
    Call<List<UserTypesDM>> getMeUserType();

    @GET("ForgetPass")
    Call<LoginResponseDM> callForgetPassword(

            @Query("Email") String Email
    );


    @FormUrlEncoded
    @POST("userRegistration")
    Call<LoginResponseDM> registerUser(@Field("UserName") String UserName,
                               @Field("Email") String Email ,  @Field("Password") String Password ,   @Field("user_Type") String user_Type ,
                               @Field("ContactNo") int ContactNo ,@Field("Address") String Address , @Field("City") String City);


    @GET("api/TodayRates")
    Call<List<TodayRatesDM>> getTodayRates();


    @GET("searchTodayRates/")
    Call<List<TodayRatesDM>> getFilterRates(
            @Query("Date") String Date,
            @Query("City") String City);

    @GET("api/FeedMills")
    Call<List<GenericMedFeedHatchDM>> getfeedmills();
    @GET("api/Hatcheries")
    Call<List<GenericMedFeedHatchDM>> getHatcheries();

    @GET("api/MedicineCompanies")
    Call<List<GenericMedFeedHatchDM>> getMedicineCompanies();

    @GET("searchMedicineCompany")
    Call<List<GenericMedFeedHatchDM>> getCityBaseMedicineCompanies(

            @Query("city") String city
    );
    @GET("searchHatcheries")
    Call<List<GenericMedFeedHatchDM>> getCityBaseHatcheries(

            @Query("city") String city
    );
    @GET("searchFeedMills")
    Call<List<GenericMedFeedHatchDM>> getCityBasefeedmills(

            @Query("city") String city
    );

}
