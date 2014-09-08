package com.garciaericn.forecaster.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.garciaericn.forecaster.R;
import com.garciaericn.forecaster.data.Weather;
import com.garciaericn.forecaster.data.WeatherAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/6/14.
 */
public class DaysListFragment extends ListFragment {
    public static final String TAG = "DaysListFragment.TAG";
    private static List<Weather> forecastList;

    public void DaysListFragment(){};

    public static DaysListFragment newInstance(List<Weather> forecastArray) {
        Log.i(TAG, "newInstance entered");
        DaysListFragment frag = new DaysListFragment();
        forecastList = forecastArray;

        return frag;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, "onActivityCreated entered");
        super.onActivityCreated(savedInstanceState);

        WeatherAdapter adapter = new WeatherAdapter(getActivity(), R.layout.weather_list_item, forecastList);
        setListAdapter(adapter);
    }


}
