package com.android.chickdrive.chicksdrive.uiActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.chickdrive.chicksdrive.R;


public class SelectionSignupActivity extends AppCompatActivity {

    Button email_sign_in_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_signup);
        email_sign_in_button  = findViewById(R.id.email_sign_in_button);

        email_sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectionSignupActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
