package com.garciaericn.forecaster.fragments;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.garciaericn.forecaster.R;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/11/14.
 */
public class SettingsFragment extends PreferenceFragment {

    private static final String CLEAR_CACHE = "CLEAR_CACHE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from XML
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Find preference by key
        Preference pref = findPreference(CLEAR_CACHE);
        pref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                // TODO:  Delete stored weather data

                return false;
            }
        });
    }
}
