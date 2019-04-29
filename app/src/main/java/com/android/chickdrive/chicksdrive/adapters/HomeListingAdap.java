package com.android.chickdrive.chicksdrive.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.chickdrive.chicksdrive.R;
import com.android.chickdrive.chicksdrive.models.HomeGenericLisDM;
import com.android.chickdrive.chicksdrive.viewHolders.HomeListingVH;
import com.bumptech.glide.Glide;

import java.util.List;


public class HomeListingAdap extends RecyclerView.Adapter<HomeListingVH> {
    private static final String TAG = "CategoriesListAdapter";
    private List<HomeGenericLisDM> allMatchesListDMArrayList;
    private Context context;

    public HomeListingAdap(Context context, List<HomeGenericLisDM> allMatchesListDMArrayList) {
        this.allMatchesListDMArrayList = allMatchesListDMArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeListingVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Called by the layoutManger to attach the view to adapter
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.temp_home_list, parent, false);
        return new HomeListingVH(view);

    }

    @Override
    public void onBindViewHolder(final HomeListingVH holder, int position) {

        final HomeGenericLisDM homeGenericLisDM = allMatchesListDMArrayList.get(position);
        holder.tv_City.setText(homeGenericLisDM.getCity());
        holder.tv_Days_Age.setText(homeGenericLisDM.getDaysAge()+" "+"days");
        holder.tv_Quantity.setText(homeGenericLisDM.getChickenType()+" "+"Chicken"+" "+homeGenericLisDM.getQuantity()+"");
        holder.tv_WeightMinWeightMax.setText(homeGenericLisDM.getWeightMax()+" "+"-"+" "+homeGenericLisDM.getWeightMax()+" kg");

        Glide.with(context).load(homeGenericLisDM.getImage1()).into(holder.img_Image1);


    }

    @Override
    public int getItemCount() {
        if (allMatchesListDMArrayList != null && allMatchesListDMArrayList.size() > 0) {
            return allMatchesListDMArrayList.size();
        }
        return 0;
    }

    public void loadNewData(List<HomeGenericLisDM> categoriesListDMList) {
        this.allMatchesListDMArrayList = categoriesListDMList;
        notifyDataSetChanged();
    }

    public HomeGenericLisDM getDataObject(int position) {
        return allMatchesListDMArrayList.get(position);
    }
}


