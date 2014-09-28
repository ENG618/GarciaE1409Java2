package com.garciaericn.memoryvault.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
    private int selectedMemory = -1;

    public interface MemoryListFragmentCallback {
        public void onItemSelected(Memory memory);
        public void refreshList();
    }

    public MemoryListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    public static MemoryListFragment newInstance(List<Memory> memoryArray) {
        Log.i(TAG, "getInstance entered");
        MemoryListFragment frag = new MemoryListFragment();
        memoryList = memoryArray;

        return frag;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated");

        // Create instance of MemoryManager
        MemoryManager mgr = MemoryManager.getInstance(getActivity());

        if (memoryList != null) {
            adapter = new MemoryAdapter(getActivity(), R.layout.memory_list_item, mgr.getMemories(getActivity()));
            setListAdapter(adapter);
        }

        AdapterView.OnItemLongClickListener longClickListener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (actionMode != null) {
                    return false;
                }

                selectedMemory = position;

                actionMode = getActivity().startActionMode(actionModeCallback);

                return true;
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

                activity.refreshList();
                adapter.notifyDataSetChanged();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    // Contextual Actionbar Callback
    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate menu from resources
            MenuInflater menuInflater = mode.getMenuInflater();
            menuInflater.inflate(R.menu.memory_cab, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.deleteMemoryCAB: {
                    Log.i(TAG, "deleteMemory from CAB entered");
                    MemoryManager mgr = MemoryManager.getInstance(getActivity());
                    mgr.removeMemory(adapter.getItem(selectedMemory));
                    Toast.makeText(getActivity(), memoryList.get(selectedMemory).getEventName() + "deleted", Toast.LENGTH_SHORT).show();
                    activity.refreshList();
                    mode.finish();
                    return true;
                }
                default: {
                    return false;
                }
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
        }
    };
}
