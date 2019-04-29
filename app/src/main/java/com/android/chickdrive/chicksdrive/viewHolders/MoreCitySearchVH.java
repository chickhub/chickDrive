package com.android.chickdrive.chicksdrive.viewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.chickdrive.chicksdrive.R;

public class MoreCitySearchVH extends RecyclerView.ViewHolder {
    public TextView tv_city_name;

    public MoreCitySearchVH(@NonNull View itemView) {
        super(itemView);
        tv_city_name = itemView.findViewById(R.id.tv_city_name);
    }
}
