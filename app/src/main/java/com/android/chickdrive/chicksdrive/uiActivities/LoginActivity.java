package com.android.chickdrive.chicksdrive.uiActivities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.chickdrive.chicksdrive.R;
import com.android.chickdrive.chicksdrive.models.LoginResponseDM;
import com.android.chickdrive.chicksdrive.retrofit.ApiClient;
import com.android.chickdrive.chicksdrive.retrofit.ApiInterface;
import com.android.chickdrive.chicksdrive.utils.Constants;
import com.daasuu.ahp.AnimateHorizontalProgressBar;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    TextView txt_signup;
    EditText email, password;
    String userEmail, userPassword;
    AnimateHorizontalProgressBar progressBar;
    Button btn_login, facebookbutton;
    LoginButton loginButton;
    LinearLayout linearLayout, lil_forgetpass;
    private CallbackManager callbackManager;
    private static final String EMAIL = "email";

    SharedPreferences prefLogIn;
    SharedPreferences.Editor editor;
    ApiInterface apiInterfaceLogin;
    Call<LoginResponseDM> logInDMCall;
    String userId;
    boolean isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        lil_forgetpass = findViewById(R.id.lil_forgetpass);
        linearLayout = findViewById(R.id.lil_signup);
        progressBar = findViewById(R.id.animate_progress_bar);
        btn_login = findViewById(R.id.btn_login);
        txt_signup = findViewById(R.id.txt_signup);
//        facebookbutton = findViewById(R.id.loginwithfb);
//        loginButton = findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
//        loginButton = (LoginButton) findViewById(R.id.login_button);
//        loginButton.setReadPermissions(Arrays.asList(EMAIL));

        prefLogIn = getSharedPreferences(Constants.MY_PREF_LOGIN, Context.MODE_PRIVATE);
        apiInterfaceLogin = ApiClient.getApiClient().create(ApiInterface.class);

        linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, SelectionSignupActivity.class);
                startActivity(intent);
            }
        });
        lil_forgetpass = findViewById(R.id.lil_forgetpass);
        lil_forgetpass.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgetPassword.class);
                startActivity(intent);
            }
        });
    /*    facebookbutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButton.callOnClick();
            }
        });*/

        btn_login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                userEmail = email.getText().toString();
                userPassword = password.getText().toString();

                progressBar.invalidate();
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setMinimumWidth(2);
                progressBar.setMax(1000);
                progressBar.setProgress(0);
                progressBar.setProgressWithAnim(1000);
                progressBar.setMaxWithAnim(200);
                progressBar.setAnimDuration(12000);


                getLoginInfo(userEmail, userPassword);
//                loginWebservice(userEmail, userPassword);
            }
        });


        // register the callback for the facbook Integreation
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(final LoginResult loginResult) {
//                loginResult.getAccessToken().getToken();
//                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(JSONObject object, GraphResponse response) {
//                        // Get facebook data from login
////                        getFacebookData(object);
//                    }
//                });
//                Bundle parameters = new Bundle();
//                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
//                request.setParameters(parameters);
//                request.executeAsync();
//            }
//
//            @Override
//            public void onCancel() {
//                Toast.makeText(LoginActivity.this, "Request Cancelled", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_LONG).show();
//                Log.e("tag", error.getMessage());
//            }
//
//        });
    }

    public boolean getLoginInfo(String email, String password) {




        Log.d(TAG, "login: is called");

        Log.d(TAG, "onClick: email is" + email);
        Log.d(TAG, "onClick: password id" + password);


        logInDMCall = apiInterfaceLogin.logInCall(email, password);
        logInDMCall.enqueue(new Callback<LoginResponseDM>() {
            @Override
            public void onResponse(Call<LoginResponseDM> call, Response<LoginResponseDM> response) {
                Log.d(TAG, "onResponse: is called ");
                LoginResponseDM login = response.body();
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + login.toString());
                    String status = login.getStatus();
                    userId = login.getId();




                    if (status != null && status.equalsIgnoreCase("Login Successful")) {

                        Toast.makeText(LoginActivity.this, login.getStatus(), Toast.LENGTH_SHORT).show();

                        progressBar.invalidate();
                        progressBar.setVisibility(View.GONE);
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        editor = prefLogIn.edit();
                        editor.putString(Constants.PREF_USER_ID_KEY, userId);
                        Log.d(TAG, "user id is " + userId);
                        editor.commit();
                        editor.apply();

                        startActivity(intent);


                    } else {
                        progressBar.invalidate();
                        progressBar.setVisibility(View.GONE);
                        loginFailedDialog();
                    }


                } else {
                    Log.d(TAG, "onResponse: is failed ");
                    progressBar.invalidate();
                    progressBar.setVisibility(View.GONE);

                    loginFailedDialog();
                }

            }

            @Override
            public void onFailure(Call<LoginResponseDM> call, Throwable t) {
                progressBar.invalidate();
                progressBar.setVisibility(View.INVISIBLE);

                Toast.makeText(LoginActivity.this, "Network Problem", Toast.LENGTH_LONG).show();


            }
        });

        return isLogin;


    }

    public void loginFailedDialog() {


        final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle(getString(R.string.dialog_title));
        builder.setMessage(getString(R.string.dialog_message));

        String positiveText = getString(android.R.string.ok);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                        dialog.dismiss();
                    }
                });
/*
        String negativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                    }
                });*/

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (logInDMCall != null) {
            logInDMCall.cancel();
        }
    }



   /* public void loginWebservice(final String email, final String password) {
        progressBar.invalidate();
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setMinimumWidth(2);
        progressBar.setMax(1000);
        progressBar.setProgress(0);
        progressBar.setProgressWithAnim(1000);
        progressBar.setMaxWithAnim(200);
        progressBar.setAnimDuration(12000);

        LoginApi.RetrofitAdapter.CustomAPI().getUserProfile(email, password)
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
                        Log.d("username", "username" + logindataStructre.getProfile().getFirstName());
                        if (logindataStructre.getProfile().getMessage().equals("Successfull")) {
                            StaticData.userProfile.userProfile = logindataStructre.getProfile();
                            NaveMainActivity.startNavActivity(LoginActivity.this);
                        } else {

                            switch (logindataStructre.hashCode()) {
                                case 404:
                                    Toast.makeText(LoginActivity.this, "Requested resource not found", Toast.LENGTH_SHORT).show();
                                    break;
                                case 500:
                                    Toast.makeText(LoginActivity.this, "server broken", Toast.LENGTH_SHORT).show();
                                    break;

                                default:
                                    Toast.makeText(LoginActivity.this, "Unexpected Error occcured! check your internet connection", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressBar.invalidate();
                        progressBar.setVisibility(View.INVISIBLE);

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }*/

    // The data from the facebook
  /*  private Bundle getFacebookData(JSONObject object) {
        try {
            if (object != null) {
                Bundle bundle = new Bundle();
                String id = object.getString("id");
                StaticData.idUser = id;
                TinyDB.getInstance(this).putString(Constants.idUser, id);
                try {
                    URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                    bundle.putString("profile_pic", profile_pic.toString());
                    StaticData.userImage = profile_pic.toString();
                    TinyDB.getInstance(LoginActivity.this).putString(Constants.userImage, profile_pic.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return null;
                }

                bundle.putString("idFacebook", id);

                if (object.has("first_name"))
                    bundle.putString("first_name", object.getString("first_name"));
                bundle.putString("last_name", object.getString("last_name"));
                StaticData.nameOfUser = (object.getString("first_name") + "" + object.getString("last_name"));
                TinyDB.getInstance(LoginActivity.this).putString(Constants.nameOfUser, StaticData.nameOfUser);
                if (object.has("email"))
                    bundle.putString("email", object.getString("email"));
                StaticData.userEmail = object.getString("email");
                TinyDB.getInstance(LoginActivity.this).putString(Constants.userEmail, object.getString("email"));
                if (object.has("gender"))
                    bundle.putString("gender", object.getString("gender"));
                if (object.has("birthday"))
                    bundle.putString("birthday", object.getString("birthday"));
                if (object.has("location"))
                    bundle.putString("location", object.getJSONObject("location").getString("name"));

                callWebserviceforFbUser();

                return bundle;

            } else {
                Toast.makeText(this, "Connection error please restart app!", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            Log.d("Error", "Error parsing JSON");
        }
        return null;
    }*/

  /*  private void callWebserviceforFbUser() {
        progressBar.invalidate();
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setMinimumWidth(2);
        progressBar.setMax(1000);
        progressBar.setProgress(0);
        progressBar.setProgressWithAnim(1000);
        progressBar.setMaxWithAnim(200);
        progressBar.setAnimDuration(12000);
        FbRegisterUser.RetrofitAdapter.CustomAPI().getFbdata(StaticData.userEmail, StaticData.nameOfUser,
                "1995-12-01", StaticData.idUser, StaticData.userImage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LogindataStructre>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LogindataStructre logindataStructre) {
                        progressBar.invalidate();
                        progressBar.setVisibility(View.INVISIBLE);
                        if (logindataStructre.getProfile().getMessage().equals("Successfull")) {
                            StaticData.userProfile.userProfile = logindataStructre.getProfile();
                            NaveMainActivity.startNavActivity(LoginActivity.this);
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressBar.invalidate();
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public static void callLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);

    }

}


