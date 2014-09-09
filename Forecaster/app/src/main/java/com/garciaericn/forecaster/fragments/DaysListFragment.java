package com.garciaericn.forecaster.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.garciaericn.forecaster.R;
import com.garciaericn.forecaster.data.Weather;
import com.garciaericn.forecaster.data.WeatherAdapter;

import java.util.List;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/6/14.
 */
public class DaysListFragment extends ListFragment {
    public static final String TAG = "DaysListFragment.TAG";
    private static List<Weather> forecastList;

    // TODO: Set up Callbacks to communicate between activities
//    public interface Callbacks {
//        public void onItemSelected(Weather weather, int position);
//    }

    public DaysListFragment(){

    }

    public static DaysListFragment newInstance(List<Weather> forecastArray) {
        Log.i(TAG, "newInstance entered");
        DaysListFragment frag = new DaysListFragment();
        forecastList = forecastArray;

        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (forecastList != null) {
//            WeatherAdapter adapter = new WeatherAdapter(getActivity(), R.layout.weather_list_item, forecastList);
//            setListAdapter(adapter);
//        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, "onActivityCreated entered");
        super.onActivityCreated(savedInstanceState);

        if (forecastList != null) {
            WeatherAdapter adapter = new WeatherAdapter(getActivity(), R.layout.weather_list_item, forecastList);
            setListAdapter(adapter);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.i(TAG, "onListItemClick entered");
        Weather weather = forecastList.get(position);

        WeatherDetailsFragment frag = (WeatherDetailsFragment) getFragmentManager().findFragmentByTag(WeatherDetailsFragment.TAG);
        if (frag == null) {
            frag = WeatherDetailsFragment.newInstance(weather);
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.weather_detail_container, frag, WeatherDetailsFragment.TAG)
                    .commit();
        }
    }
}
