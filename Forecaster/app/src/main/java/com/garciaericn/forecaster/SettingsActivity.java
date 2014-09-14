package com.garciaericn.forecaster;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.garciaericn.forecaster.fragments.SettingsFragment;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/12/14.
 */
public class SettingsActivity extends Activity {

    private static final String TAG = "SettingsActivity.TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate entered");
        super.onCreate(savedInstanceState);
//        addPreferencesFromResource(R.xml.settings);
        setContentView(R.layout.activity_settings);

        SettingsFragment frag = new SettingsFragment();

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.setting_fragment_container, frag, SettingsActivity.TAG)
                .commit();
    }
}
