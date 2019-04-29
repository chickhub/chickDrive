package com.android.chickdrive.chicksdrive.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.chickdrive.chicksdrive.R;
import com.android.chickdrive.chicksdrive.models.more.GenericMedFeedHatchDM;
import com.android.chickdrive.chicksdrive.viewHolders.GenericFeedHatchMedVH;
import com.bumptech.glide.Glide;

import java.util.List;


public class GenericFeedHatchMedAdap extends RecyclerView.Adapter<GenericFeedHatchMedVH> {
    private static final String TAG = "CategoriesListAdapter";
    private List<GenericMedFeedHatchDM> allMatchesListDMArrayList;
    private Context context;

    public GenericFeedHatchMedAdap(Context context, List<GenericMedFeedHatchDM> allMatchesListDMArrayList) {
        this.allMatchesListDMArrayList = allMatchesListDMArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public GenericFeedHatchMedVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Called by the layoutManger to attach the view to adapter
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.temp_generic_feed_hatc_med, parent, false);
        return new GenericFeedHatchMedVH(view);

    }

    @Override
    public void onBindViewHolder(final GenericFeedHatchMedVH holder, int position) {

        final GenericMedFeedHatchDM homeGenericLisDM = allMatchesListDMArrayList.get(position);
        holder.tv_City.setText(homeGenericLisDM.getCity());
        holder.tv_Address.setText(homeGenericLisDM.getAddress() + " ");
        holder.tv_Name.setText(homeGenericLisDM.getName() + " ");
        holder.tv_ContactNo.setText(homeGenericLisDM.getContactNo() + " ");
        holder.tv_Email.setText(homeGenericLisDM.getEmail() + " ");


        Glide.with(context).load(homeGenericLisDM.getLogo()).into(holder.img_Logo);

    }

    @Override
    public int getItemCount() {
        if (allMatchesListDMArrayList != null && allMatchesListDMArrayList.size() > 0) {
            return allMatchesListDMArrayList.size();
        }
        return 0;
    }

    public void loadNewData(List<GenericMedFeedHatchDM> categoriesListDMList) {
        this.allMatchesListDMArrayList = categoriesListDMList;
        notifyDataSetChanged();
    }

    public GenericMedFeedHatchDM getDataObject(int position) {
        return allMatchesListDMArrayList.get(position);
    }
}


