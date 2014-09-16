package com.garciaericn.memoryvault.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/15/14.
 */
public class MemoryListFragment extends ListFragment {
    public static final String TAG = "MemoryListFragment.TAG";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate entered");
    }
}
