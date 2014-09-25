package com.garciaericn.littleleagueroster;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/24/14.
 */
public class TeamFragment extends ListFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public TeamFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TeamFragment newInstance(int sectionNumber) {
        TeamFragment fragment = new TeamFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_roster, container, false);
        return rootView;
    }
}
