package com.garciaericn.weather.fragments;

import android.app.ListFragment;
import android.os.Bundle;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/25/14.
 */
public class HourlyWeatherFragment extends ListFragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static HourlyWeatherFragment newInstance(int sectionNumber) {
        HourlyWeatherFragment fragment = new HourlyWeatherFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
}
