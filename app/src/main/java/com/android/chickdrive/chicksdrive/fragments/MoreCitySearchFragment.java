package com.android.chickdrive.chicksdrive.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.android.chickdrive.chicksdrive.R;
import com.android.chickdrive.chicksdrive.adapters.MoreCitySearchAdapter;
import com.android.chickdrive.chicksdrive.models.CityDM;
import com.android.chickdrive.chicksdrive.retrofit.ApiClient;
import com.android.chickdrive.chicksdrive.retrofit.ApiInterface;
import com.android.chickdrive.chicksdrive.uiActivities.HomeActivity;
import com.android.chickdrive.chicksdrive.uiActivities.SignupActivity;
import com.android.chickdrive.chicksdrive.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreCitySearchFragment extends Fragment {
    private Context context;
    ApiInterface apiInterface;
    Call<List<CityDM>> callCity;
    public  static ArrayList<String> country = new ArrayList<>();
   public  static ArrayList<String> listcountry = new ArrayList<>();

    private RecyclerView list;
    private EditText search;
    RecyclerView.LayoutManager layoutManager;

    MoreCitySearchAdapter moreCitySearchAdapter;

    String generic;


    public MoreCitySearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        HomeActivity.tv_appBar_title.setText("Select City");
        HomeActivity.img_filter.setVisibility(View.GONE);

        return inflater.inflate(R.layout.fragment_more_city_search, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();

        generic = getArguments().getString(Constants.MOREOBJECT);




        sendingNetworkRequestCity();

        layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        list.setLayoutManager(layoutManager);
        moreCitySearchAdapter = new MoreCitySearchAdapter(context, country,generic);
        list.setAdapter(moreCitySearchAdapter);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                moreCitySearchAdapter.getFilter().filter(charSequence.toString());
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void initViews() {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        list = getActivity().findViewById(R.id.list);
        search = getActivity().findViewById(R.id.search);
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


                        country = new ArrayList<>();
                        for (int i = 0; i < cityDMs.size(); i++) {

                            CityDM classTypeDataModel = cityDMs.get(i);
                            String  cityName = classTypeDataModel.getCityName();
                            country.add(cityName);

                        }
                        listcountry .addAll(country);
                        moreCitySearchAdapter.loadNewData(country);





                    }
                } else {
                    Toast.makeText(context, "Something went wrong ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<CityDM>> call, Throwable t) {
                Toast.makeText(context, "Something went wrong ", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onStop() {
        super.onStop();
        search.setText("");
    }
}
