package com.garciaericn.memoryvault;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.garciaericn.memoryvault.fragments.SettingsFragment;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/17/14.
 */
public class SettingsActivity extends Activity {

    private static final String TAG = "SettingsActivity.TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate entered");
        setContentView(R.layout.activity_settings);

        // Create instance of preferences fragment
        SettingsFragment frag = new SettingsFragment();

        // Replace fragment container w/ preferences fragment
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_fragment_container, frag, SettingsActivity.TAG)
                .commit();
    }
}
