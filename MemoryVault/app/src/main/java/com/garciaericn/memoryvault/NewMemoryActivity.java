package com.garciaericn.memoryvault;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.garciaericn.memoryvault.data.Memory;
import com.garciaericn.memoryvault.data.MemoryManager;
import com.garciaericn.memoryvault.fragments.NewMemoryFragment;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/16/14.
 */
public class NewMemoryActivity extends Activity
    implements NewMemoryFragment.NewMemoryFragmentCallbacks{

    private static final String TAG = "NewMemoryActivity.TAG";
    private MemoryManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_memory);

        manager = new MemoryManager();
        manager.newInstance(this);

        if (savedInstanceState == null) { // First launch of activity (no saved state)
            Log.i(TAG, "onCreate for first launch");

            // Create fragment
            NewMemoryFragment frag = new NewMemoryFragment();
            // Obtain bundle from intent
            Bundle b = getIntent().getBundleExtra(MemoryListActivity.MEMORYBUNDLE);
            frag.setArguments(b);

            // Load new fragment
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.add_fragment_container, frag, NewMemoryFragment.TAG)
                    .commit();
        } else { // Application recreated from saved state
            Log.i(TAG, "onCreate from saved instance");
            // TODO: Load fragments from savedInstanceState Bundle
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.memory_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings : {
                Log.i(TAG, "Settings button pressed");
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            }
            /**
             * Save is handled in fragment class
             * case R.id.action_save_memory : {
             *  Log.i(TAG, "Save button pressed");
             *  return false;
             * }
             */
            //
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addMemory(Memory newMemory) {
        manager.addMemory(newMemory);

        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
