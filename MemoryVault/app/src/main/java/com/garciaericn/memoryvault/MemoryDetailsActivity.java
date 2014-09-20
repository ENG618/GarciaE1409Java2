package com.garciaericn.memoryvault;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.garciaericn.memoryvault.data.Memory;
import com.garciaericn.memoryvault.data.MemoryManager;
import com.garciaericn.memoryvault.fragments.MemoryDetailsFragment;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/19/14.
 */
public class MemoryDetailsActivity extends Activity
    implements MemoryDetailsFragment.MemoryDetailsFragmentCallbacks{

    public static final String TAG = "MemoryDetailsActivity.TAG";
    public static final int DISCARDCODE = 4321;

    private MemoryManager manager;
    private Memory memory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_details);
        Log.i(TAG, "onCreate entered");

        manager = new MemoryManager();
        manager.newInstance(this);

        if (savedInstanceState == null) {
            // Create fragment
            MemoryDetailsFragment fragment = new MemoryDetailsFragment();
            // Obtain bundle from intent
            Bundle b = getIntent().getBundleExtra(MemoryListActivity.MEMORYBUNDLE);
            fragment.setArguments(b);

            if (b != null && b.containsKey(Memory.EVENT_KEY)) {
                memory = new Memory(b);
            }

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

                if (memory != null) {
                    StringBuilder builder = new StringBuilder();
                    builder.append("Check out my event: ");
                    builder.append(memory.getEventName());
                    builder.append(" with ");
                    builder.append(String.valueOf(memory.getNumGuests()));
                    builder.append(" guests. At ");
                    builder.append(memory.getEventLocation());
                    builder.append(".");
                    builder.append(System.getProperty("line.separator"));
                    builder.append("--Sent from Memory Vault");

                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, (java.io.Serializable) builder);
                    sendIntent.setType("text/plain");
                    startActivity(Intent.createChooser(sendIntent, "Share Memory with..."));
                }

                return true;
            }
            case R.id.action_discard_memory : {
                discardMemory(memory);
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

    @Override
    public void discardMemory(Memory discardedMemory) {
        manager.removeMemory(discardedMemory);

        Intent returnIntent = new Intent();
        setResult(DISCARDCODE, returnIntent);
        finish();
    }
}
