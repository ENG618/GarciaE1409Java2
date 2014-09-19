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
 * Created by ENG618-Mac on 9/16/14.
 */
public class NewMemoryFragment extends Fragment {

    public static final String TAG = "NewMemoryFragment.TAG";

    private Memory memory;
    private NewMemoryFragmentCallbacks activity;

    public interface NewMemoryFragmentCallbacks {
        public void addMemory(Memory newMemory);
    }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView entered");

        // Load layout
        return inflater.inflate(R.layout.fragment_new_memory, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (NewMemoryFragmentCallbacks) activity;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save_memory : {
                Log.i(TAG, "Save from fragments");
                save();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void save() {
        Log.i(TAG, "save entered");

        View view = getView();
        Memory newMemory;

        if (view != null) {
            // Obtain all fields
            TextView eventTitleTV = (TextView) view.findViewById(R.id.newEventNameTV);
            String eventTitle = (String) eventTitleTV.getText();

            TextView numGuestsTV = (TextView) view.findViewById(R.id.newGuestsTV);
            int numGuests = Integer.valueOf((String) numGuestsTV.getText());

            TextView eventLocationTV = (TextView) view.findViewById(R.id.newLocationTV);
            String eventLocation = (String) eventLocationTV.getText();

            TextView eventNotesTV = (TextView) view.findViewById(R.id.newNotesTV);
            String eventNotes = (String) eventNotesTV.getText();

            newMemory = new Memory(eventTitle, numGuests, eventLocation, eventNotes);

            activity.addMemory(newMemory);
        } else {
            // TODO: Display error alert
        }
    }
}
