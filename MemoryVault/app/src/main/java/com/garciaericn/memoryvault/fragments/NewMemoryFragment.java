package com.garciaericn.memoryvault.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.garciaericn.memoryvault.R;
import com.garciaericn.memoryvault.data.Memory;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/16/14.
 */
public class NewMemoryFragment extends Fragment {

    public static final String TAG = "NewMemoryFragment.TAG";

    Memory memory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate entered");

        Bundle b = getArguments();
        if (b != null && b.containsKey(Memory.EVENT_KEY)) {
            memory = new Memory(b);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG,"onCreateView entered");

        // Load layout
        return inflater.inflate(R.layout.fragment_new_memory, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
