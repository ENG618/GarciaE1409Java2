package com.garciaericn.forecaster.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.garciaericn.forecaster.data.Weather;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/6/14.
 */
public class WeatherDetailsFragment extends Fragment {

    public static final String TAG = "WeatherDetailsFragment.TAG";

    Weather weather;

    // TODO: Create interface

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate entered");
        super.onCreate(savedInstanceState);

        Bundle b = getArguments();
        if (b != null && b.containsKey(Weather.DAY_OF_WEEK)) {
            weather = new Weather(b);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView entered");

        // Load layout
        View view = inflater.inflate(R.layout.detail_fragment, container, false);

        if (weather != null) {
            // Obtain and set all test views

        }
    }
}
