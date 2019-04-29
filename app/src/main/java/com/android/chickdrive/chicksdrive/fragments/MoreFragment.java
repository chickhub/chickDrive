package com.android.chickdrive.chicksdrive.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.chickdrive.chicksdrive.R;
import com.android.chickdrive.chicksdrive.uiActivities.HomeActivity;
import com.android.chickdrive.chicksdrive.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment {

    private CardView cardV_medicine;
    private CardView cardV_feddMills;
    private CardView cardV_hatcheries;
    private CardView cardV_brokers;
    private String generic;
    private Context context;


    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        HomeActivity.tv_appBar_title.setText("More");
        HomeActivity.img_filter.setVisibility(View.GONE);
        return inflater.inflate(R.layout.fragment_more, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();

        cardV_medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeActivity.img_filter.setVisibility(View.VISIBLE);
                generic = "MEDICINE";
                GenericFeedHatcMedFragment fragment = new GenericFeedHatcMedFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constants.MOREOBJECT, generic);
                fragment.setArguments(bundle);
                FragmentTransaction transaction =  ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.simpleFrameLayout, fragment).addToBackStack(null);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.commit();
            }
        });
        cardV_feddMills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generic = "FEED";
                GenericFeedHatcMedFragment fragment = new GenericFeedHatcMedFragment();
                HomeActivity.img_filter.setVisibility(View.VISIBLE);

                Bundle bundle = new Bundle();
                bundle.putString(Constants.MOREOBJECT, generic);
                fragment.setArguments(bundle);
                FragmentTransaction transaction =  ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.simpleFrameLayout, fragment).addToBackStack(null);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.commit();

            }
        });
        cardV_hatcheries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity.img_filter.setVisibility(View.VISIBLE);

                generic = "HATCHERIES";
                GenericFeedHatcMedFragment fragment = new GenericFeedHatcMedFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constants.MOREOBJECT, generic);
                fragment.setArguments(bundle);
                FragmentTransaction transaction =  ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.simpleFrameLayout, fragment).addToBackStack(null);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.commit();

            }
        });
        cardV_brokers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity.img_filter.setVisibility(View.VISIBLE);
                generic = "BROKERS";
                GenericFeedHatcMedFragment fragment = new GenericFeedHatcMedFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constants.MOREOBJECT, generic);
                fragment.setArguments(bundle);
                FragmentTransaction transaction =  ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.simpleFrameLayout, fragment).addToBackStack(null);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.commit();

            }
        });
    }

    private void initViews() {

        cardV_medicine = getActivity().findViewById(R.id.cardV_medicine);
        cardV_feddMills = getActivity().findViewById(R.id.cardV_feddMills);
        cardV_hatcheries = getActivity().findViewById(R.id.cardV_hatcheries);
        cardV_brokers = getActivity().findViewById(R.id.cardV_brokers);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
