package com.garciaericn.forecaster.fragments;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;

import com.garciaericn.forecaster.MainActivity;
import com.garciaericn.forecaster.R;

import java.io.File;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/11/14.
 */
public class SettingsFragment extends PreferenceFragment {

    private static final String CLEAR_CACHE = "CLEAR_CACHE";
    private static final String TAG = "SettingsFragment.TAG";

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
                clearData();

                return false;
            }
        });
    }

    private void clearData(){
        Log.i(TAG, "clearData entered");

        File external = getActivity().getExternalFilesDir(null);
        File file = new File(external, MainActivity.FILENAME);

        if (file.delete()) {
            Log.i(TAG, "Cache was deleted");
        }
    }
}
