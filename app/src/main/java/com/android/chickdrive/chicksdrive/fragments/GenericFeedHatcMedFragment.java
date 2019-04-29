package com.android.chickdrive.chicksdrive.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.chickdrive.chicksdrive.R;
import com.android.chickdrive.chicksdrive.adapters.GenericFeedHatchMedAdap;
import com.android.chickdrive.chicksdrive.adapters.HomeParentRecyclerAdapter;
import com.android.chickdrive.chicksdrive.models.more.GenericMedFeedHatchDM;
import com.android.chickdrive.chicksdrive.models.rates.TodayRatesDM;
import com.android.chickdrive.chicksdrive.retrofit.ApiClient;
import com.android.chickdrive.chicksdrive.retrofit.ApiInterface;
import com.android.chickdrive.chicksdrive.uiActivities.HomeActivity;
import com.android.chickdrive.chicksdrive.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class GenericFeedHatcMedFragment extends Fragment {

    private Context context;
    private ApiInterface apiInterface;
    private Call<List<GenericMedFeedHatchDM>> callFeed;

    private RecyclerView recycler_view_more;
    private GenericFeedHatchMedAdap genericFeedHatchMedAdap;
    private RecyclerView.LayoutManager layoutManager;

    private ProgressBar progressBar;
    private String generic;






    public GenericFeedHatcMedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_generic_feed_hatc_med, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        HomeActivity.img_filter.setVisibility(View.VISIBLE);


        initViews();

        generic = getArguments().getString(Constants.MOREOBJECT);
        if (generic.equalsIgnoreCase("MEDICINE"))
        {
            getMedicineCompanies();
            HomeActivity.tv_appBar_title.setText("Medicine Comapnies ");
        }if (generic.equalsIgnoreCase("Feed")){
            getFeedMills();
            HomeActivity.tv_appBar_title.setText("Feed Mills");
        }
        if(generic.equalsIgnoreCase("HATCHERIES")) {
            getHatcheries();
            HomeActivity.tv_appBar_title.setText("Hatcheries");
        }
        if(generic.equalsIgnoreCase("BROKERS")) {
            getHatcheries();
            HomeActivity.tv_appBar_title.setText("BROKERS");
        }
        HomeActivity.img_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeActivity.img_filter.setVisibility(View.VISIBLE);
                MoreCitySearchFragment fragment = new MoreCitySearchFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constants.MOREOBJECT, generic);
                fragment.setArguments(bundle);
                FragmentTransaction transaction =  ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().addToBackStack(null);;
                transaction.replace(R.id.simpleFrameLayout, fragment).addToBackStack(null);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.commit();
            }
        });


    }

    private void initViews() {

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        progressBar = getActivity().findViewById(R.id.progressBar);
        recycler_view_more = getActivity().findViewById(R.id.recycler_view_more);

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public void getFeedMills() {

        progressBar.setVisibility(View.VISIBLE);


        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        callFeed = apiInterface.getfeedmills();
        callFeed.enqueue(new Callback<List<GenericMedFeedHatchDM>>() {
            @Override
            public void onResponse(Call<List<GenericMedFeedHatchDM>> call, Response<List<GenericMedFeedHatchDM>> response) {
                if (response.isSuccessful()) {
                    List<GenericMedFeedHatchDM> todayRatesDMS   = response.body();

                    setDataToViews(todayRatesDMS);



                    progressBar.setVisibility(View.GONE);

                } else {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show();

                    progressBar.setVisibility(View.GONE);

                }

            }

            @Override
            public void onFailure(Call<List<GenericMedFeedHatchDM>> call, Throwable t) {
                Toast.makeText(context, "Network Problem", Toast.LENGTH_LONG).show();

                progressBar.setVisibility(View.GONE);

            }
        });
    }

    public void getHatcheries() {

        progressBar.setVisibility(View.VISIBLE);


        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        callFeed = apiInterface.getHatcheries();
        callFeed.enqueue(new Callback<List<GenericMedFeedHatchDM>>() {
            @Override
            public void onResponse(Call<List<GenericMedFeedHatchDM>> call, Response<List<GenericMedFeedHatchDM>> response) {
                if (response.isSuccessful()) {
                    List<GenericMedFeedHatchDM> todayRatesDMS   = response.body();

                    setDataToViews(todayRatesDMS);



                    progressBar.setVisibility(View.GONE);

                } else {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show();

                    progressBar.setVisibility(View.GONE);

                }

            }

            @Override
            public void onFailure(Call<List<GenericMedFeedHatchDM>> call, Throwable t) {
                Toast.makeText(context, "Network Problem", Toast.LENGTH_LONG).show();

                progressBar.setVisibility(View.GONE);

            }
        });
    }
    public void getMedicineCompanies() {

        progressBar.setVisibility(View.VISIBLE);


        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        callFeed = apiInterface.getMedicineCompanies();
        callFeed.enqueue(new Callback<List<GenericMedFeedHatchDM>>() {
            @Override
            public void onResponse(Call<List<GenericMedFeedHatchDM>> call, Response<List<GenericMedFeedHatchDM>> response) {
                if (response.isSuccessful()) {
                    List<GenericMedFeedHatchDM> todayRatesDMS   = response.body();

                    setDataToViews(todayRatesDMS);



                    progressBar.setVisibility(View.GONE);

                } else {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show();

                    progressBar.setVisibility(View.GONE);

                }

            }

            @Override
            public void onFailure(Call<List<GenericMedFeedHatchDM>> call, Throwable t) {
                Toast.makeText(context, "Network Problem", Toast.LENGTH_LONG).show();

                progressBar.setVisibility(View.GONE);

            }
        });
    }

    private void setDataToViews(List<GenericMedFeedHatchDM> todayRatesDMS) {

        genericFeedHatchMedAdap = new GenericFeedHatchMedAdap(getActivity(), todayRatesDMS);
        recycler_view_more.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycler_view_more.setAdapter(genericFeedHatchMedAdap);


    }
}
