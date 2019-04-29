package com.android.chickdrive.chicksdrive.fragments;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.chickdrive.chicksdrive.R;
import com.android.chickdrive.chicksdrive.adapters.HomeParentRecyclerAdapter;
import com.android.chickdrive.chicksdrive.models.HomeGenericLisDM;
import com.android.chickdrive.chicksdrive.models.HomeListingDM;
import com.android.chickdrive.chicksdrive.models.MoreItemsDM;
import com.android.chickdrive.chicksdrive.retrofit.ApiClient;
import com.android.chickdrive.chicksdrive.retrofit.ApiInterface;
import com.android.chickdrive.chicksdrive.uiActivities.HomeActivity;
import com.android.chickdrive.chicksdrive.utils.CustomAdDialogue;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView my_recycler_view;
    List<String> listTitleRecycler;
    private ProgressBar progressBar;
    private HomeParentRecyclerAdapter homeParentRecyclerAdapter;


    private ApiInterface apiInterface;
    private Call<HomeListingDM> allCitiesDMCall;

    private HomeListingDM homeListingDM;

    private Context context;




    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        HomeActivity.tv_appBar_title.setText("Home");
        HomeActivity.img_filter.setVisibility(View.GONE);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        CustomAdDialogue customDialogClass = new CustomAdDialogue(getActivity());
        customDialogClass.show();
        customDialogClass.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        customDialogClass.setCancelable(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(customDialogClass.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.x = 10;
        lp.y = 10;
        customDialogClass.getWindow().setAttributes(lp);

        my_recycler_view = getActivity().findViewById(R.id.my_recycler_view);
        progressBar = getActivity().findViewById(R.id.google_progress);

        progressBar.setVisibility(View.VISIBLE);
        // adding recycler tietle
        listTitleRecycler = new ArrayList<String>();
        listTitleRecycler.add(0, "Listings You Would Like");
        listTitleRecycler.add(1, "Recommended");


        homeParentRecyclerAdapter = new HomeParentRecyclerAdapter(getActivity(), listTitleRecycler);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        my_recycler_view.setAdapter(homeParentRecyclerAdapter);

        loadHomeListing();
         List<MoreItemsDM> moreItemsDMList = new ArrayList<>();

        for (int i=0;i<4;i++)

        {
            MoreItemsDM moreItemsDM = new MoreItemsDM();
            moreItemsDMList.add(moreItemsDM);
        }

        homeParentRecyclerAdapter.loadMoreItemsData(moreItemsDMList);
        homeParentRecyclerAdapter.notifyDataSetChanged();
    }

    public void loadHomeListing() {

        progressBar.setVisibility(View.VISIBLE);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        allCitiesDMCall = apiInterface.gethomeList();
        allCitiesDMCall.enqueue(new Callback<HomeListingDM>() {
            @Override
            public void onResponse(Call<HomeListingDM> call, Response<HomeListingDM> response) {
                if (response.isSuccessful()) {
                    HomeListingDM homeListingDM   = response.body();

                    prepareRelatedListData(homeListingDM);

                    progressBar.setVisibility(View.GONE);
                    my_recycler_view.setVisibility(View.VISIBLE);

                } else {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show();

                    progressBar.setVisibility(View.GONE);

                }

            }

            @Override
            public void onFailure(Call<HomeListingDM> call, Throwable t) {
                Toast.makeText(context, "Network Problem", Toast.LENGTH_LONG).show();

                progressBar.setVisibility(View.GONE);

            }
        });
    }

    private void prepareRelatedListData(HomeListingDM homeListingDM) {

         List<HomeGenericLisDM> allList= new ArrayList<>();

      List<HomeGenericLisDM> layerList = new ArrayList<>();
      List<HomeGenericLisDM> broylerList = new ArrayList<>();

        broylerList.addAll(homeListingDM.getBroyler());
        layerList.addAll(homeListingDM.getLayer());

        for (int i =0 ; i< broylerList.size() ; i++)
        {
           allList.add(broylerList.get(i));
        }

        for (int j =0 ; j< layerList.size() ; j++)
        {
            allList.add(layerList.get(j));

        }

        homeParentRecyclerAdapter.loadHomeListingData(allList);
        homeParentRecyclerAdapter.notifyDataSetChanged();




    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

}
