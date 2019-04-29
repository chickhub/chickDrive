package com.android.chickdrive.chicksdrive.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.chickdrive.chicksdrive.R;
import com.android.chickdrive.chicksdrive.models.HomeListingDM;
import com.android.chickdrive.chicksdrive.models.rates.TodayRatesDM;
import com.android.chickdrive.chicksdrive.retrofit.ApiClient;
import com.android.chickdrive.chicksdrive.retrofit.ApiInterface;
import com.android.chickdrive.chicksdrive.uiActivities.HomeActivity;
import com.android.chickdrive.chicksdrive.utils.Constants;
import com.android.chickdrive.chicksdrive.utils.GenericFunctions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RatesFragment extends Fragment {

    private ApiInterface apiInterface;
    private Call<List<TodayRatesDM>> allCitiesDMCall;

    private TodayRatesDM homeListingDM;

    private Context context;
    private ProgressBar progressBar;

    private TextView tv_Date;
    private TextView tv_City;
    private TextView tv_BFarmRate;
    private TextView tv_BWholeSaleAlive;
    private TextView BRetailAlive;
    private TextView tv_BMeat;
    private TextView tv_LEggRate;
    private TextView tv_LCage;
    private TextView tv_LFloor;
    private TextView tv_LStarter;
    private TextView tv_LGramRate;
    private LinearLayout linear_main;
    List<TodayRatesDM> todayRatesDMS;



    public RatesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        HomeActivity.tv_appBar_title.setText("Filter Rates");
        HomeActivity.img_filter.setVisibility(View.VISIBLE);

        return inflater.inflate(R.layout.fragment_rates, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        if (getArguments()!=null) {
            todayRatesDMS = getArguments().getParcelableArrayList(Constants.List);
        }



        if (todayRatesDMS!=null && todayRatesDMS.size()>0)
        {
            linear_main.setVisibility(View.VISIBLE);

            setDataToViews(todayRatesDMS);
        }
else {
            getTodayRtaes();
        }

        HomeActivity.img_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RatesSearchFragment ratesSearchFragment = new RatesSearchFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null);
                transaction.replace(R.id.simpleFrameLayout, ratesSearchFragment);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.commit();

            }
        });

    }

    private void initViews() {

        progressBar = getActivity().findViewById(R.id.progressBar);
        tv_Date = getActivity().findViewById(R.id.tv_Date);
        tv_City = getActivity().findViewById(R.id.tv_City);
        tv_BFarmRate = getActivity().findViewById(R.id.tv_BFarmRate);
        tv_BWholeSaleAlive = getActivity().findViewById(R.id.tv_BWholeSaleAlive);
        BRetailAlive = getActivity().findViewById(R.id.BRetailAlive);
        tv_BMeat = getActivity().findViewById(R.id.tv_BMeat);
        tv_LEggRate = getActivity().findViewById(R.id.tv_LEggRate);
        tv_LCage = getActivity().findViewById(R.id.tv_LCage);
        tv_LFloor = getActivity().findViewById(R.id.tv_LFloor);
        tv_LStarter = getActivity().findViewById(R.id.tv_LStarter);
        tv_LGramRate = getActivity().findViewById(R.id.tv_LGramRate);
        linear_main = getActivity().findViewById(R.id.linear_main);

    }

    public void getTodayRtaes() {

        progressBar.setVisibility(View.VISIBLE);


        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        allCitiesDMCall = apiInterface.getTodayRates();
        allCitiesDMCall.enqueue(new Callback<List<TodayRatesDM>>() {
            @Override
            public void onResponse(Call<List<TodayRatesDM>> call, Response<List<TodayRatesDM>> response) {
                if (response.isSuccessful()) {
                    List<TodayRatesDM> todayRatesDMS   = response.body();

                    setDataToViews(todayRatesDMS);



                    progressBar.setVisibility(View.GONE);
                    linear_main.setVisibility(View.VISIBLE);

                } else {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show();

                    progressBar.setVisibility(View.GONE);

                }

            }

            @Override
            public void onFailure(Call<List<TodayRatesDM>> call, Throwable t) {
                Toast.makeText(context, "Network Problem", Toast.LENGTH_LONG).show();

                progressBar.setVisibility(View.GONE);

            }
        });
    }

    private void setDataToViews(List<TodayRatesDM> todayRatesDMS) {
        TodayRatesDM ratesDM = null;
        if (todayRatesDMS!=null && todayRatesDMS.size()>0) {
            ratesDM = todayRatesDMS.get(0);
        }
        tv_Date.setText(GenericFunctions.ConvertApiIntoMonthWordDayYear(ratesDM.getDate())+"");
        tv_City.setText(ratesDM.getCity().getCityName()+"");
        tv_BFarmRate.setText(ratesDM.getBFarmRate()+"");
        tv_BWholeSaleAlive.setText(ratesDM.getBWholeSaleAlive()+"");
        BRetailAlive.setText(ratesDM.getBRetailAlive()+"");
        tv_BMeat.setText(ratesDM.getBMeat()+"");
        tv_LCage.setText(ratesDM.getLCage()+"");
        tv_LFloor.setText(ratesDM.getLFloor()+"");
        tv_LStarter.setText(ratesDM.getLStarter()+"");
        tv_LEggRate.setText(ratesDM.getLEggRate()+"");
        tv_LGramRate.setText(ratesDM.getLGramRate()+"");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
