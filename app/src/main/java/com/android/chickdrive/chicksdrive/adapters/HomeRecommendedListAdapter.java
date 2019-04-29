package com.android.chickdrive.chicksdrive.adapters;

/**
 * Created by pratap.kesaboyina on 24-12-2014.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.chickdrive.chicksdrive.R;
import com.android.chickdrive.chicksdrive.fragments.GenericFeedHatcMedFragment;
import com.android.chickdrive.chicksdrive.models.MoreItemsDM;
import com.android.chickdrive.chicksdrive.utils.Constants;

import java.util.List;



public class HomeRecommendedListAdapter extends RecyclerView.Adapter<HomeRecommendedListAdapter.SingleItemRowHolder> {

    private Context context;
    private List<MoreItemsDM>listMatch;
    private String generic;

    public HomeRecommendedListAdapter(Context context, List<MoreItemsDM> serriesDMArrayList) {
        this.context = context;
        this.listMatch = serriesDMArrayList;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_single_card, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder,final int i) {
        if (i==0)
        {
            holder.img.setImageResource(R.drawable.ic_medicine);
            holder.tv_name.setText("MEDICINE\n" +
                    "COMPANIES");
            generic = "MEDICINE";
        }
        if (i==1)
        {
            holder.img.setImageResource(R.drawable.ic_feed_mills);
            holder.tv_name.setText("FEED MILLS");
            generic = "FEED";

        }
        if (i==2)
        {
            holder.img.setImageResource(R.drawable.ic_hatcheries);
            holder.tv_name.setText("HATCHERIES");
            generic = "HATCHERIES";


        }
        if (i==3)
        {
            holder.img.setImageResource(R.drawable.ic_brokers);
            holder.tv_name.setText("BROKERS");
            generic = "BROKERS";


        }
        holder.cardV_job_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GenericFeedHatcMedFragment fragment = new GenericFeedHatcMedFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constants.MOREOBJECT, generic);
                fragment.setArguments(bundle);
                FragmentTransaction transaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.simpleFrameLayout, fragment).addToBackStack(null);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.commit();
            }
        });







    }

    @Override
    public int getItemCount() {
        return (null != listMatch ? listMatch.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        public ImageView img;
        public TextView tv_name;
        public CardView cardV_job_desc;







        public SingleItemRowHolder(View view) {
            super(view);

            img = itemView.findViewById(R.id.img);
            tv_name = itemView.findViewById(R.id.tv_name);
            cardV_job_desc = itemView.findViewById(R.id.cardV_job_desc);








        }

    }

}