package com.garciaericn.memoryvault.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.Log;

import com.garciaericn.memoryvault.R;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/17/14.
 */
public class SettingsFragment extends PreferenceFragment {

    public static final String TAG = "SettingsFragment.TAG";

    public static final String FIRST_LAUNCH = "FIRST_LAUNCH";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate entered");

        // Load the xml
        addPreferencesFromResource(R.xml.settings);
    }
    // TODO: Set up any preferences onClicks
}
