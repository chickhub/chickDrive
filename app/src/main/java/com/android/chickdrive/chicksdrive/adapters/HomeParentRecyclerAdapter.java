package com.android.chickdrive.chicksdrive.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.chickdrive.chicksdrive.R;
import com.android.chickdrive.chicksdrive.models.HomeGenericLisDM;
import com.android.chickdrive.chicksdrive.models.MoreItemsDM;
import com.android.chickdrive.chicksdrive.viewHolders.ItemRowHolder;

import java.util.ArrayList;
import java.util.List;



public class HomeParentRecyclerAdapter extends RecyclerView.Adapter<ItemRowHolder> {

    List<HomeGenericLisDM> listAllMatchData;
    private List<MoreItemsDM> seriesList;
    private List<String> strings = new ArrayList<>();
    private Context context;


    public HomeParentRecyclerAdapter(Context context, List<String> strings) {
        this.strings = strings;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.temp_parent_recy_list_items, null);
        ItemRowHolder mh = new ItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRowHolder itemRowHolder, int i) {
        if (i == 0) {
            itemRowHolder.tv_titleRecycler.setText("Listings You Would Like");
            HomeListingAdap homeListingAdap = new HomeListingAdap(context, listAllMatchData);
            itemRowHolder.recycler_view_list.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            itemRowHolder.recycler_view_list.setAdapter(homeListingAdap);
            itemRowHolder.recycler_view_list.setNestedScrollingEnabled(false);
            itemRowHolder.recycler_view_list.setHasFixedSize(true);
            homeListingAdap.notifyDataSetChanged();

           /* PagerSnapHelper snapHelper = new PagerSnapHelper();
            snapHelper.attachToRecyclerView(itemRowHolder.recycler_view_list);
            itemRowHolder.recycler_view_list.addItemDecoration(new LinePagerIndicatorDecoration());*/
        } else if (i == 1) {
            itemRowHolder.tv_titleRecycler.setText("Recommended");

            HomeRecommendedListAdapter itemListDataAdapter = new HomeRecommendedListAdapter(context, seriesList);
            itemRowHolder.recycler_view_list.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            itemRowHolder.recycler_view_list.setAdapter(itemListDataAdapter);
            itemRowHolder.recycler_view_list.setNestedScrollingEnabled(false);
            itemRowHolder.recycler_view_list.setHasFixedSize(true);
            itemListDataAdapter.notifyDataSetChanged();


        }


    }

    @Override
    public int getItemCount() {

        return (null != strings ? strings.size() : 0);

    }

    public void loadHomeListingData(List<HomeGenericLisDM> listAllMatchData) {
        this.listAllMatchData = listAllMatchData;
        notifyDataSetChanged();
    }

    public void loadMoreItemsData(List<MoreItemsDM> seriesList) {
        this.seriesList = seriesList;
        notifyDataSetChanged();
    }


}