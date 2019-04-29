package com.android.chickdrive.chicksdrive.uiActivities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.chickdrive.chicksdrive.R;
import com.android.chickdrive.chicksdrive.models.CityDM;
import com.android.chickdrive.chicksdrive.models.LoginResponseDM;
import com.android.chickdrive.chicksdrive.models.UserTypesDM;
import com.android.chickdrive.chicksdrive.retrofit.ApiClient;
import com.android.chickdrive.chicksdrive.retrofit.ApiInterface;
import com.android.chickdrive.chicksdrive.utils.Constants;
import com.daasuu.ahp.AnimateHorizontalProgressBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignupActivity extends AppCompatActivity {

    ApiInterface apiInterface;

    Button btn_signup;
    EditText et_email, et_pass, et_number,et_name,et_address;
    Spinner spinner_code, spinner_city, spinner_typeOfAccount;
    AnimateHorizontalProgressBar progressBar;
    ImageView img_back;

    Call<List<CityDM>> callCity;
    Call<List<UserTypesDM>> callUserTypes;
    Call<LoginResponseDM> callRegister;
    ArrayList<String> stringArrayListCityName;
    ArrayList<String> stringArrayListUserType;
    String selectedCityName;
    String selectedUserType;
    SharedPreferences prefLogIn;
    SharedPreferences.Editor editor;
    String userId;


    List<String> codeList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        img_back = findViewById(R.id.img_back);
        btn_signup = findViewById(R.id.btn_signup);
        progressBar = findViewById(R.id.animate_progress_bar);

        et_email = findViewById(R.id.et_email);
        et_pass = findViewById(R.id.et_pass);
        et_number = findViewById(R.id.et_number);
        et_name = findViewById(R.id.et_name);
        et_address = findViewById(R.id.et_address);

        spinner_code = findViewById(R.id.spinner_code);
        spinner_city = findViewById(R.id.spinner_city);
        spinner_typeOfAccount = findViewById(R.id.spinner_typeOfAccount);

        prefLogIn = getSharedPreferences(Constants.MY_PREF_LOGIN, Context.MODE_PRIVATE);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        sendingNetworkRequestCity();
        sendingNetworkRequestUserType();

        sendingNetworkRequestUserType();


        // call login activity
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.callLoginActivity(SignupActivity.this);
            }
        });
        codeList = new ArrayList<>();
        codeList();
        ArrayAdapter<String> codeadapter = new ArrayAdapter<>(SignupActivity.this, R.layout.spinner_item, codeList);
        codeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_code.setAdapter(codeadapter);

        spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCityName=spinner_city.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_typeOfAccount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUserType=spinner_typeOfAccount.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              final String name=et_name.getText().toString();
                String  email=et_email.getText().toString();
                String  password=et_pass.getText().toString();
                String phone1=et_number.getText().toString();
                String address=et_address.getText().toString();

                if (et_name.getText().toString().isEmpty()  || et_email.getText().toString().isEmpty()
                        || et_pass.toString().isEmpty() || et_number.getText().toString().isEmpty() || et_address.getText().toString().isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Some of the Fields Are Empty", Toast.LENGTH_SHORT).show();
                }
                else {

                    progressBar.invalidate();
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setMinimumWidth(2);
                    progressBar.setMax(1000);
                    progressBar.setProgress(0);
                    progressBar.setProgressWithAnim(1000);
                    progressBar.setMaxWithAnim(200);
                    progressBar.setAnimDuration(12000);


                    callRegister = apiInterface.registerUser(name, email, password, selectedUserType, Integer.parseInt(phone1),address,selectedCityName);
                    callRegister.enqueue(new Callback<LoginResponseDM>() {
                        @Override
                        public void onResponse(Call<LoginResponseDM> call, Response<LoginResponseDM> response) {
                            LoginResponseDM login = response.body();
                            if (response.isSuccessful()) {
                                String status = login.getStatus();
                                userId = login.getId();

                                progressBar.invalidate();
                                progressBar.setVisibility(View.GONE);


                                if (status != null && status.equalsIgnoreCase("Registration Successfull")) {


                                    Toast.makeText(SignupActivity.this, login.getStatus(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    editor = prefLogIn.edit();
                                    editor.putString(Constants.PREF_USER_ID_KEY, userId);
                                    editor.putString(Constants.PREF_USER_NAME, name);
                                    editor.commit();
                                    editor.apply();
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);


                                } else  {
                                    Toast.makeText(SignupActivity.this, login.getStatus(), Toast.LENGTH_SHORT).show();
                                    progressBar.invalidate();
                                    progressBar.setVisibility(View.GONE);

                                }


                            } else {
                                Toast.makeText(SignupActivity.this, "Some thing went wrong try again", Toast.LENGTH_SHORT).show();
                                progressBar.invalidate();
                                progressBar.setVisibility(View.GONE);


                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponseDM> call, Throwable t) {
                            Toast.makeText(SignupActivity.this, "Network Problem", Toast.LENGTH_LONG).show();
                            progressBar.invalidate();
                            progressBar.setVisibility(View.GONE);

                        }
                    });

                }


               /* if (haveNetworkConnection()) {
                    if (!tv_name.getText().toString().equals("") && !tv_pass.getText().toString().equals("")) {
                        if (!spinner_city.getSelectedItem().equals("Which City You Live Now ?")) {
                            if (tv_number.getText().toString().length() == 7) {
                                // now hav all the all the fields
                                callWebServiceforSignup(tv_name.getText().toString(), tv_email.getText().toString(),
                                        spinner_code.getSelectedItem().toString() + tv_number.getText().toString(),
                                        tempCityId, tv_pass.getText().toString());
                            } else {
                                Toast.makeText(SignupActivity.this, "Enter 7 digits of your mobile.", Toast.LENGTH_SHORT).show();
                            }
                        } else {

                            Toast.makeText(SignupActivity.this, "Select your city.", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(SignupActivity.this, "please fill all the fields.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(SignupActivity.this, "Have No Internet connection!!", Toast.LENGTH_SHORT).show();
                }*/




            }

        });
    }

    public void codeList() {
        codeList.add("0300");
        codeList.add("0301");
        codeList.add("0302");
        codeList.add("0303");
        codeList.add("0304");
        codeList.add("0305");
        codeList.add("0306");
        codeList.add("0307");
        codeList.add("0308");
        codeList.add("0310");
        codeList.add("0311");
        codeList.add("0312");
        codeList.add("0313");
        codeList.add("0314");
        codeList.add("0315");
        codeList.add("0316");
        codeList.add("0317");
        codeList.add("0318");
        codeList.add("0320");
        codeList.add("0321");
        codeList.add("0322");
        codeList.add("0323");
        codeList.add("0324");
        codeList.add("0325");
        codeList.add("0326");
        codeList.add("0327");
        codeList.add("0328");
        codeList.add("0330");
        codeList.add("0331");
        codeList.add("0332");
        codeList.add("0333");
        codeList.add("0334");
        codeList.add("0335");
        codeList.add("0336");
        codeList.add("0337");
        codeList.add("0338");
        codeList.add("0340");
        codeList.add("0341");
        codeList.add("0342");
        codeList.add("0343");
        codeList.add("0344");
        codeList.add("0345");
        codeList.add("0346");
        codeList.add("0347");
        codeList.add("0348");
    }

    public void sendingNetworkRequestCity() {
        callCity = apiInterface.getMeCity();
        callCity.enqueue(new Callback<List<CityDM>>() {
            @Override
            public void onResponse(Call<List<CityDM>> call, Response<List<CityDM>> response) {
                if (response.isSuccessful()) {
                    List<CityDM> cityDMs = response.body();

                    if (cityDMs == null || cityDMs.size() == 0) {
//                        progressDialog.dismiss();
//                        linearLayout.setVisibility(View.GONE);
                        Toast.makeText(SignupActivity.this, "City Not Found ", Toast.LENGTH_SHORT).show();

                    } else {
//                        progressDialog.dismiss();


                        stringArrayListCityName = new ArrayList<>();
                        for (int i = 0; i < cityDMs.size(); i++) {

                            CityDM classTypeDataModel = cityDMs.get(i);
                             String  cityName = classTypeDataModel.getCityName();
                            stringArrayListCityName.add(cityName);

                        }
                        setDataCitySpinner(stringArrayListCityName);


                    }
                } else {
                    Toast.makeText(SignupActivity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<CityDM>> call, Throwable t) {

            }
        });

    }

    private void setDataCitySpinner(ArrayList<String> stringArrayListCityName) {
        ArrayAdapter<String> classArrayadapter = new ArrayAdapter<String>(SignupActivity.this, android.R.layout.simple_dropdown_item_1line, stringArrayListCityName);
        spinner_city.setAdapter(classArrayadapter);
    }

    public void sendingNetworkRequestUserType() {
        callUserTypes = apiInterface.getMeUserType();
        callUserTypes.enqueue(new Callback<List<UserTypesDM>>() {
            @Override
            public void onResponse(Call<List<UserTypesDM>> call, Response<List<UserTypesDM>> response) {
                if (response.isSuccessful()) {
                    List<UserTypesDM> userTypesDMS = response.body();

                    if (userTypesDMS == null || userTypesDMS.size() == 0) {
//                        progressDialog.dismiss();
//                        linearLayout.setVisibility(View.GONE);
                        Toast.makeText(SignupActivity.this, "City Not Found ", Toast.LENGTH_SHORT).show();

                    } else {
//                        progressDialog.dismiss();


                        stringArrayListUserType = new ArrayList<>();
                        for (int i = 0; i < userTypesDMS.size(); i++) {

                            UserTypesDM classTypeDataModel = userTypesDMS.get(i);
                           String userTyp = classTypeDataModel.getTypeUser();
                            stringArrayListUserType.add(userTyp);

                        }
                        setDataUserTypeSpinner(stringArrayListUserType);


                    }
                } else {
                    Toast.makeText(SignupActivity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<UserTypesDM>> call, Throwable t) {

            }
        });

    }

    private void setDataUserTypeSpinner(ArrayList<String> stringArrayListCityName) {
        ArrayAdapter<String> classArrayadapter = new ArrayAdapter<String>(SignupActivity.this, android.R.layout.simple_dropdown_item_1line, stringArrayListCityName);
        spinner_typeOfAccount.setAdapter(classArrayadapter);
    }


    // call this method to open this activity.
    public static void callSignupActivity(Context context) {
        Intent intent = new Intent(context, SignupActivity.class);
        context.startActivity(intent);
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }


/*
    public void callWebServiceforSignup(String name, String email, String phone, int cityId, String password) {
        progressBar.invalidate();
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setMinimumWidth(2);
        progressBar.setMax(1000);
        progressBar.setProgress(0);
        progressBar.setProgressWithAnim(1000);
        progressBar.setMaxWithAnim(200);
        progressBar.setAnimDuration(12000);
        SignUpApi.RetrofitAdapter.CustomApi().getSignupProfile(name, phone, cityId, email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LogindataStructre>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LogindataStructre logindataStructre) {
                        progressBar.invalidate();
                        progressBar.setVisibility(View.GONE);
                        if (logindataStructre.getProfile().getMessage().equals("Successfull")) {
                           userProfile.userProfile = logindataStructre.getProfile();
                            SelectionActivity.startSeletionActivity(SignupActivity.this);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressBar.invalidate();
                        progressBar.setVisibility(View.GONE);
                        Log.d("check123", "check123" + e);

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
*/

}
