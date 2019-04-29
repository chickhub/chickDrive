package com.android.chickdrive.chicksdrive.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import com.android.chickdrive.chicksdrive.R;
import com.android.chickdrive.chicksdrive.fragments.GenericFeedHatcMedFragment;
import com.android.chickdrive.chicksdrive.fragments.SearchCityGenericFeedHatcMedFragment;
import com.android.chickdrive.chicksdrive.utils.Constants;
import com.android.chickdrive.chicksdrive.utils.NewFilter;
import com.android.chickdrive.chicksdrive.viewHolders.MoreCitySearchVH;

import java.util.List;

public class MoreCitySearchAdapter extends RecyclerView.Adapter<MoreCitySearchVH> implements Filterable {

    Context context;
    List<String> mData;
    NewFilter mfilter;
    String generic;

    public MoreCitySearchAdapter(Context context, List<String> data,String generic) {
        this.context = context;
        this.mData = data;
        this.generic = generic;
        mfilter = new NewFilter(MoreCitySearchAdapter.this);
    }
    @NonNull
    @Override
    public MoreCitySearchVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.temp_city, viewGroup, false);
        return new MoreCitySearchVH(view);    }

    @Override
    public void onBindViewHolder(@NonNull MoreCitySearchVH moreCitySearchVH, int i) {
        moreCitySearchVH.tv_city_name.setText(mData.get(i));

       final String ciy = mData.get(i);
        moreCitySearchVH.tv_city_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchCityGenericFeedHatcMedFragment fragment = new SearchCityGenericFeedHatcMedFragment();
                Bundle bundle = new Bundle();

                bundle.putString(Constants.CITYOBJECT,ciy );
                bundle.putString(Constants.MOREOBJECT,generic );
                fragment.setArguments(bundle);
                FragmentTransaction transaction =  ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.simpleFrameLayout, fragment,"SearchCityGenericFeedHatcMedFragment").addToBackStack("null");
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != mData ? mData.size() : 0);
    }



    public void loadNewData( List<String> mData)
    {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return mfilter;    }
}
