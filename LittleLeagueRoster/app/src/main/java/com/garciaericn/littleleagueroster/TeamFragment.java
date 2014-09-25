package com.garciaericn.littleleagueroster;

import android.app.ListFragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;

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
    private String[] players;

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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Resources res = getResources();

        if (savedInstanceState != null  && savedInstanceState.containsKey(ARG_SECTION_NUMBER)) {
            Bundle b = getArguments();
            int sectionNumber = b.getInt(ARG_SECTION_NUMBER);
            switch (sectionNumber) {
                case 1 : {
                    players = res.getStringArray(R.array.team_one);
                }
                case 2 : {
                    players = res.getStringArray(R.array.team_two);
                }
                case 3 : {
                    players = res.getStringArray(R.array.team_three);
                }
                case 4 : {
                    players = res.getStringArray(R.array.team_four);
                }
                case 5 : {
                    players = res.getStringArray(R.array.team_five);
                }
                case 6 : {
                    players = res.getStringArray(R.array.team_six);
                }
            }

            if (players != null) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, players);
                setListAdapter(adapter);
            }

        }
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_roster, container, false);
//        return rootView;
//    }
}
