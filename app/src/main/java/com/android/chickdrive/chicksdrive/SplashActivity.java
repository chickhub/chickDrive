package com.android.chickdrive.chicksdrive;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.android.chickdrive.chicksdrive.uiActivities.HomeActivity;
import com.android.chickdrive.chicksdrive.uiActivities.LoginActivity;
import com.android.chickdrive.chicksdrive.utils.Constants;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_splash);
        splashThread();
    }

    public void hideStatusBar() {
        View decorView = getWindow().getDecorView();


// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.

        // If the Android version is lower than Jellybean, use this call to hide
        // the status bar.

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

    }

    public void splashThread() {
        Thread timerThread = new Thread() {
            public void run() {
                try {
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {



                    SharedPreferences sharedPreferences;
                    sharedPreferences=getSharedPreferences(Constants.MY_PREF_LOGIN, Context.MODE_PRIVATE);
                    String prefUserId =sharedPreferences.getString(Constants.PREF_USER_ID_KEY,Constants.NULL);

                    if (prefUserId!=null && !prefUserId.isEmpty()) {
                        Intent intent = new Intent(SplashActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {

                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }

            }
        };
        timerThread.start();
    }




    @Override
    protected void onResume() {
        super.onResume();
        hideStatusBar();
    }
}
