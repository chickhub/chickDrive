package com.android.chickdrive.chicksdrive.utils;

import android.widget.Filter;

import com.android.chickdrive.chicksdrive.adapters.MoreCitySearchAdapter;
import com.android.chickdrive.chicksdrive.fragments.MoreCitySearchFragment;

import java.util.ArrayList;

public class NewFilter extends Filter {

    public MoreCitySearchAdapter mAdapter;
    public NewFilter(MoreCitySearchAdapter mAdapter){
        super();
        this.mAdapter = mAdapter;
    }
    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        MoreCitySearchFragment.country.clear();
        final FilterResults results = new FilterResults();
        if(charSequence.length() == 0){
            MoreCitySearchFragment.country.addAll( MoreCitySearchFragment.listcountry);
        }else{
            final String filterPattern =charSequence.toString().toLowerCase().trim();
            for(String listcountry :  MoreCitySearchFragment.listcountry){
                if(listcountry.toLowerCase().startsWith(filterPattern)){
                    MoreCitySearchFragment.country.add(listcountry);
                }
            }
        }
        results.values =  MoreCitySearchFragment.country;
        results.count =  MoreCitySearchFragment.country.size();
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

//        MoreCitySearchFragment.country = (ArrayList<String>) results.values;
        this.mAdapter.notifyDataSetChanged();
    }
}
