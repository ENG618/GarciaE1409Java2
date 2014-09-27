package com.garciaericn.memoryvault.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
    private MemoryListFragmentCallback activity;
    private MemoryAdapter adapter;
    private ActionMode actionMode;

    public interface MemoryListFragmentCallback {
        public void onItemSelected(Memory memory);
    }

    public MemoryListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
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

        MemoryManager mgr = new MemoryManager();
        mgr = mgr.newInstance(getActivity());
        memoryList = mgr.getMemories(getActivity());

        if (memoryList != null) {
            adapter = new MemoryAdapter(getActivity(), R.layout.memory_list_item, memoryList);
            setListAdapter(adapter);
        }

        AdapterView.OnItemLongClickListener longClickListener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (actionMode != null) {
                    return false;
                }

                actionMode = getActivity().startActionMode(actionModeCallback);

                return false;
            }
        };
        getListView().setOnItemLongClickListener(longClickListener);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (MemoryListFragmentCallback) activity;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.i(TAG, "onListItemClick entered");
        Memory memory = memoryList.get(position);
        activity.onItemSelected(memory);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actions_refresh : {
                Log.i(TAG, "Refresh from fragments");

                updateList();
                adapter.notifyDataSetChanged();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateList() {
        MemoryManager mgr = new MemoryManager();
        memoryList = mgr.getMemories(getActivity());
    }

    // Contextual Actionbar Callback
    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    };
}
