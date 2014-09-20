package com.garciaericn.memoryvault.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.garciaericn.memoryvault.R;
import com.garciaericn.memoryvault.data.Memory;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/19/14.
 */
public class MemoryDetailsFragment extends Fragment {
    public static final String TAG = "MemoryDetailsFragment.TAG";

    private Memory memory;
    private MemoryDetailsFragmentCallbacks activity;

    public interface MemoryDetailsFragmentCallbacks {
        public void discardMemory(Memory discardedMemory);
    }

    public MemoryDetailsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate entered");

        setHasOptionsMenu(true);

        Bundle b = getArguments();
        if (b != null && b.containsKey(Memory.EVENT_KEY)) {
            memory = new Memory(b);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_discard_memory : {
                Log.i(TAG, "Save from fragments");
                discard();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void discard() {
        activity.discardMemory(memory);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (MemoryDetailsFragmentCallbacks) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView entered");
        //Load layout
        View view = inflater.inflate(R.layout.fragment_memory_details, container, false);

        if (memory != null) {
            // Obtain and set all fields
            TextView eventTV = (TextView) view.findViewById(R.id.eventNameTV);
            eventTV.setText(memory.getEventName());

            TextView guestsTV = (TextView) view.findViewById(R.id.guestsTV);
            guestsTV.setText(String.valueOf(memory.getNumGuests()));

            TextView locationTV = (TextView) view.findViewById(R.id.locationTV);
            locationTV.setText(memory.getEventLocation());

            TextView notesTV = (TextView) view.findViewById(R.id.notesTV);
            notesTV.setText(memory.getEventNotes());
        }
        return view;
    }
}
