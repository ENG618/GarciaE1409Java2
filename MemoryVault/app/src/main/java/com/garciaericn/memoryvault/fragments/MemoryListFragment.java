package com.garciaericn.memoryvault.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.garciaericn.memoryvault.R;
import com.garciaericn.memoryvault.data.Memory;
import com.garciaericn.memoryvault.data.MemoryAdapter;
import com.garciaericn.memoryvault.data.MemoryManager;

import java.util.List;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/15/14.
 */
public class MemoryListFragment extends ListFragment {
    public static final String TAG = "MemoryListFragment.TAG";
    private static List<Memory> memoryList;

    public MemoryListFragment() {

    }

    public static MemoryListFragment newInstance(List<Memory> memoryArray) {
        Log.i(TAG, "newInstance entered");
        MemoryListFragment frag = new MemoryListFragment();
        memoryList = memoryArray;

        return frag;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated");

        if (memoryList != null) {
            MemoryAdapter adapter = new MemoryAdapter(getActivity(), R.layout.memory_list_item, memoryList);
            setListAdapter(adapter);
        }

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }
}
