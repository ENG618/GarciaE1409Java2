package com.garciaericn.forecaster.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.garciaericn.forecaster.R;
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

    public static WeatherDetailsFragment newInstance(Weather weather) {
        Log.i(TAG, "newInstance entered");

        WeatherDetailsFragment frag = new WeatherDetailsFragment();

        // Bundle arguments
        Bundle args = weather.toBundle();
        frag.setArguments(args);

        return frag;
    }

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
        return inflater.inflate(R.layout.detail_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, "onActivityCreated entered");
        super.onActivityCreated(savedInstanceState);

        if (weather != null) {
            // Obtain and set all test views
            TextView dayTV = (TextView) getView().findViewById(R.id.dayTV);
            dayTV.setText(weather.getDayOfWeek());

            TextView conditionTV = (TextView) getView().findViewById(R.id.conditionTV);
            conditionTV.setText(weather.getCondition());

            TextView forecastTV = (TextView) getView().findViewById(R.id.forecastTV);
            forecastTV.setText(weather.getForecastText());

            TextView iconURLTV = (TextView) getView().findViewById(R.id.iconURLTV);
            iconURLTV.setText(weather.getIconURL());
        }

    }

    public void setDetailText(Weather weather) {
        // Obtain & set textViews
        TextView dayTV = (TextView) getView().findViewById(R.id.dayTV);
        dayTV.setText(weather.getDayOfWeek());

        TextView conditionTV = (TextView) getView().findViewById(R.id.conditionTV);
        conditionTV.setText(weather.getCondition());

        TextView forecastTV = (TextView) getView().findViewById(R.id.forecastTV);
        forecastTV.setText(weather.getForecastText());

        TextView iconURLTV = (TextView) getView().findViewById(R.id.iconURLTV);
        iconURLTV.setText(weather.getIconURL());

    }
}
