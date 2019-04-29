package com.android.chickdrive.chicksdrive.viewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.chickdrive.chicksdrive.R;


public class HomeListingVH extends RecyclerView.ViewHolder {
    private static final String TAG = "HomeListingVH";
    public ImageView img_Image1;
    public TextView tv_Quantity;
    public TextView tv_Days_Age;
    public TextView tv_City;
    public TextView tv_WeightMinWeightMax;



    public HomeListingVH(@NonNull View itemView) {
        super(itemView);

        img_Image1 = itemView.findViewById(R.id.img_Image1);
        tv_Quantity = itemView.findViewById(R.id.tv_Quantity);
        tv_Days_Age = itemView.findViewById(R.id.tv_Days_Age);
        tv_City = itemView.findViewById(R.id.tv_City);
        tv_WeightMinWeightMax = itemView.findViewById(R.id.tv_WeightMinWeightMax);



    }
}
