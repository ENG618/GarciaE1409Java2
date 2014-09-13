package com.garciaericn.forecaster;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/12/14.
 */
public class SettingsActivity extends PreferenceActivity {

    private static final String TAG = "SettingsActivity.TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate entered");
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
