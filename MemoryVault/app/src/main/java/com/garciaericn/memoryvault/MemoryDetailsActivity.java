package com.garciaericn.memoryvault;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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
        Log.i(TAG, "onCreate entered");

        if (savedInstanceState == null) {
            // Create fragment
            MemoryDetailsFragment fragment = new MemoryDetailsFragment();
            // Obtain bundle from intent
            Bundle b = getIntent().getBundleExtra(MemoryListActivity.MEMORYBUNDLE);
            fragment.setArguments(b);

            //Load new fragment
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detail_fragment_container, fragment, MemoryDetailsFragment.TAG)
                    .commit();
        } else {
            // TODO: Load from savedInstanceState
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.memory_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share_memory : {
                // TODO: Share memory
                return true;
            }
            case R.id.action_discard_memory : {
                // TODO: Delete memory
                return true;
            }
            case R.id.action_edit_memory : {
                // TODO: Edit memory
                return true;
            }
            case R.id.action_settings : {
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
