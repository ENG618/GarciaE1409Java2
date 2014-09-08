package com.garciaericn.forecaster.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.garciaericn.forecaster.data.Weather;

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
        DaysListFragment frag = new DaysListFragment();
        forecastList = forecastArray;

        return frag;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Create string array of Days
        ArrayList<String> dayOfWeek = new ArrayList<String>();

        for (Weather weather : forecastList) {
            dayOfWeek.add(weather.getDayOfWeek());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, dayOfWeek);
        setListAdapter(adapter);
    }


}
