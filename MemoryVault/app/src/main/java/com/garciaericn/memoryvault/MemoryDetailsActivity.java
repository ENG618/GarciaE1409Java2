package com.garciaericn.memoryvault;

import android.app.Activity;
import android.os.Bundle;

import com.garciaericn.memoryvault.fragments.MemoryDetailsFragment;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/19/14.
 */
public class MemoryDetailsActivity extends Activity {

    public static final String TAG = "MemoryDetailsActivity.TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_details);

        if (savedInstanceState == null) {
            // Create fragment
            MemoryDetailsFragment fragment = new MemoryDetailsFragment();
            // Obtain bundle from intent
            Bundle b = getIntent().getBundleExtra(MemoryListActivity.MEMORYBUNDLE);
            fragment.setArguments(b);

            //Load new fragment
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detail_fragment_container, fragment, MemoryDetailsFragment.TAG);
        } else {
            // TODO: Load from savedInstanceState
        }

    }
}
