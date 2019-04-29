package com.android.chickdrive.chicksdrive.viewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.chickdrive.chicksdrive.R;

public class GenericFeedHatchMedVH extends RecyclerView.ViewHolder {

    public ImageView img_Logo;
    public TextView tv_City;
    public TextView tv_Address;
    public TextView tv_ContactNo;
    public TextView tv_Email;
    public TextView tv_Name;


    public GenericFeedHatchMedVH(@NonNull View itemView) {
        super(itemView);
        img_Logo = itemView.findViewById(R.id.img_Logo);
        tv_City = itemView.findViewById(R.id.tv_City);
        tv_Address = itemView.findViewById(R.id.tv_Address);
        tv_ContactNo = itemView.findViewById(R.id.tv_ContactNo);
        tv_Email = itemView.findViewById(R.id.tv_Email);
        tv_Name = itemView.findViewById(R.id.tv_Name);
    }
}
