package com.garciaericn.news;

import android.app.ListFragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/25/14.
 */
public class CurrentEventsFragment extends ListFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private String[] events;

    public CurrentEventsFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static CurrentEventsFragment newInstance(int sectionNumber) {
        CurrentEventsFragment fragment = new CurrentEventsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Resources res = getResources();

        events = res.getStringArray(R.array.current_events);

        if (events != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, events);
            setListAdapter(adapter);
        }
    }
}
