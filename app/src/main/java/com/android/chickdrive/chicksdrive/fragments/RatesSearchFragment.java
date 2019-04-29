package com.android.chickdrive.chicksdrive.fragments;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.chickdrive.chicksdrive.R;
import com.android.chickdrive.chicksdrive.models.CityDM;
import com.android.chickdrive.chicksdrive.models.rates.TodayRatesDM;
import com.android.chickdrive.chicksdrive.retrofit.ApiClient;
import com.android.chickdrive.chicksdrive.retrofit.ApiInterface;
import com.android.chickdrive.chicksdrive.uiActivities.HomeActivity;
import com.android.chickdrive.chicksdrive.uiActivities.SignupActivity;
import com.android.chickdrive.chicksdrive.utils.Constants;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RatesSearchFragment extends Fragment {

    private TextView tv_date;
    private SearchableSpinner spinner_city;
    private Button btn_filter;

    private Call<List<TodayRatesDM>> allCitiesDMCall;

    private TodayRatesDM homeListingDM;

    private ProgressBar progressBar;

    Call<List<CityDM>> callCity;
    private ArrayList<String> stringArrayListCityName;


    ApiInterface apiInterface;

    DatePickerDialog picker;

    private Context context;
    private String selectedCityName;


    public RatesSearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        HomeActivity.tv_appBar_title.setText("Filter Rates");
        HomeActivity.img_filter.setVisibility(View.GONE);
        return inflater.inflate(R.layout.fragment_rates_search, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViews();

        sendingNetworkRequestCity();
        setDate(tv_date);

        spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCityName=spinner_city.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                tv_date.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTodayRtaes(tv_date.getText().toString(),selectedCityName);
            }
        });
    }

    private void initViews() {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        tv_date = getActivity().findViewById(R.id.tv_date);
        spinner_city = getActivity().findViewById(R.id.spinner_city);
        btn_filter = getActivity().findViewById(R.id.btn_filter);
        progressBar = getActivity().findViewById(R.id.progressBar);
    }

    public void sendingNetworkRequestCity() {
        callCity = apiInterface.getMeCity();
        callCity.enqueue(new Callback<List<CityDM>>() {
            @Override
            public void onResponse(Call<List<CityDM>> call, Response<List<CityDM>> response) {
                if (response.isSuccessful()) {
                    List<CityDM> cityDMs = response.body();

                    if (cityDMs == null || cityDMs.size() == 0) {
//                        progressDialog.dismiss();
//                        linearLayout.setVisibility(View.GONE);
                        Toast.makeText(context, "City Not Found ", Toast.LENGTH_SHORT).show();

                    } else {
//                        progressDialog.dismiss();


                        stringArrayListCityName = new ArrayList<>();
                        for (int i = 0; i < cityDMs.size(); i++) {

                            CityDM classTypeDataModel = cityDMs.get(i);
                            String  cityName = classTypeDataModel.getCityName();
                            stringArrayListCityName.add(cityName);

                        }
                        setDataCitySpinner(stringArrayListCityName);


                    }
                } else {
                    Toast.makeText(context, "Something went wrong ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<CityDM>> call, Throwable t) {

            }
        });

    }

    private void setDataCitySpinner(ArrayList<String> stringArrayListCityName) {
        ArrayAdapter<String> classArrayadapter = new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, stringArrayListCityName);
        spinner_city.setAdapter(classArrayadapter);
    }

    public void setDate (TextView view){

        Date today = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//formating according to my need
        String date = formatter.format(today);
        view.setText(date);
    }

    public void getTodayRtaes(String date,String city) {

        progressBar.setVisibility(View.VISIBLE);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        allCitiesDMCall = apiInterface.getFilterRates(date,city);
        allCitiesDMCall.enqueue(new Callback<List<TodayRatesDM>>() {
            @Override
            public void onResponse(Call<List<TodayRatesDM>> call, Response<List<TodayRatesDM>> response) {
                if (response.isSuccessful()) {
                    List<TodayRatesDM> todayRatesDMS   = response.body();

                    if (todayRatesDMS!=null && todayRatesDMS.size()==0)
                    {
                        Toast.makeText(context, "Not Available", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        RatesFragment fragment = new RatesFragment();

                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList(Constants.List, (ArrayList<TodayRatesDM>) todayRatesDMS);
                        fragment.setArguments(bundle);
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.simpleFrameLayout, fragment).addToBackStack(null);
                        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        transaction.commit();

                    }







                    progressBar.setVisibility(View.GONE);

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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
