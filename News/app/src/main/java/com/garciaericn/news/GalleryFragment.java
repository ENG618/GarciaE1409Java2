package com.garciaericn.news;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/25/14.
 */
public class GalleryFragment extends Fragment{
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public GalleryFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static GalleryFragment newInstance(int sectionNumber) {
        GalleryFragment fragment = new GalleryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
}
