package com.android.chickdrive.chicksdrive.uiActivities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.chickdrive.chicksdrive.R;
import com.android.chickdrive.chicksdrive.models.LoginResponseDM;
import com.android.chickdrive.chicksdrive.retrofit.ApiClient;
import com.android.chickdrive.chicksdrive.retrofit.ApiInterface;
import com.android.chickdrive.chicksdrive.utils.Constants;
import com.daasuu.ahp.AnimateHorizontalProgressBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ForgetPassword extends AppCompatActivity {
    EditText ed_email;
    Button btn_recovaer;
    ImageView btn_back;
    AnimateHorizontalProgressBar progressBar;

    Call<LoginResponseDM> callRegister;
    ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        progressBar = findViewById(R.id.animate_progress_bar);
        ed_email = findViewById(R.id.ed_email);
        btn_recovaer = findViewById(R.id.btn_recover);
        btn_back = findViewById(R.id.btn_back);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        btn_recovaer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           String email = ed_email.getText().toString();

                if (ed_email.getText().toString().isEmpty() ) {
                    Toast.makeText(ForgetPassword.this, "Email is empty", Toast.LENGTH_SHORT).show();
                }
                if( !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    Toast.makeText(ForgetPassword.this, "Email is not valid", Toast.LENGTH_SHORT).show();

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


                    callRegister = apiInterface.callForgetPassword(email);
                    callRegister.enqueue(new Callback<LoginResponseDM>() {
                        @Override
                        public void onResponse(Call<LoginResponseDM> call, Response<LoginResponseDM> response) {
                            LoginResponseDM login = response.body();
                            if (response.isSuccessful()) {
                                String status = login.getStatus();
                               String userId = login.getId();

                                progressBar.invalidate();
                                progressBar.setVisibility(View.GONE);


                                if (status != null && status.equalsIgnoreCase("Your Password is sent to you on your registered email address!")) {


                                    Toast.makeText(ForgetPassword.this, login.getStatus(), Toast.LENGTH_SHORT).show();
                                    Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                                    emailIntent.setType("text/plain"); // <-- HERE
                                    startActivity(emailIntent); // <-- AND HERE


                                } else  {
                                    Toast.makeText(ForgetPassword.this, login.getStatus(), Toast.LENGTH_SHORT).show();
                                    progressBar.invalidate();
                                    progressBar.setVisibility(View.GONE);

                                }


                            } else {
                                Toast.makeText(ForgetPassword.this, "Some thing went wrong try again", Toast.LENGTH_SHORT).show();
                                progressBar.invalidate();
                                progressBar.setVisibility(View.GONE);


                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponseDM> call, Throwable t) {
                            Toast.makeText(ForgetPassword.this, "Network Problem", Toast.LENGTH_LONG).show();
                            progressBar.invalidate();
                            progressBar.setVisibility(View.GONE);

                        }
                    });

                }

            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             LoginActivity.callLoginActivity(ForgetPassword.this);
            }
        });
    }
    public static void startForgetActivity(Context context) {
        Intent intent = new Intent(context, ForgetPassword.class);
        context.startActivity(intent);
    }


/*
    public void callWEbserivice(String email) throws IOException {
        progressBar.invalidate();
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setMinimumWidth(2);
        progressBar.setMax(1000);
        progressBar.setProgress(0);
        progressBar.setProgressWithAnim(1000);
        progressBar.setMaxWithAnim(200);
        progressBar.setAnimDuration(12000);
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder httpurlbuilder = HttpUrl.parse("http://ilmkidunya.com/User_Register.asmx/SendForgetPassword").newBuilder();
        httpurlbuilder.addQueryParameter("email", email);
        String url = httpurlbuilder.build().toString();
        Request request = new Request.Builder()
                .url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                progressBar.invalidate();
                progressBar.setVisibility(View.GONE);
                Log.d("errorFe", "errorFe" + e);
                Toast.makeText(ForgetPassword.this, "Error happened", Toast.LENGTH_SHORT);
            }

            @Override
            public void onResponse(Call call, Response response) {
                progressBar.invalidate();
                progressBar.setVisibility(View.GONE);
                String responseString = response.body().toString();

                if (responseString.contains("false")) {
                    Toast.makeText(ForgetPassword.this, "Your Are Not A Member !", Toast.LENGTH_SHORT).show();

                }
                if (responseString.contains("true")) {
                    Toast.makeText(ForgetPassword.this, "Please check your email!", Toast.LENGTH_SHORT).show();
                }
                if (responseString.contains("Error")) {
                    Toast.makeText(ForgetPassword.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
*/
}
