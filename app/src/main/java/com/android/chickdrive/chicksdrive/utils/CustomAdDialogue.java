package com.android.chickdrive.chicksdrive.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ImageView;

import com.android.chickdrive.chicksdrive.R;

public class CustomAdDialogue extends Dialog {

    private ImageView img_cross;


    public CustomAdDialogue(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_city_search);

        img_cross = findViewById(R.id.img_cross);

        img_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomAdDialogue.this.dismiss();
                CustomAdDialogue.this.cancel();

            }
        });

    }
}
