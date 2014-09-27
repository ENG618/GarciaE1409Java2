package com.garciaericn.weather.fragments;

import android.app.Fragment;

import com.garciaericn.weather.objects.CurrentWeather;

import java.util.List;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/25/14.
 */
public class CurrentWeatherFragment extends Fragment {

    // Callback
    public interface ForecastFragmentCallback{
        public List<CurrentWeather> getCurrentWeather(String requestType);
    }


}
