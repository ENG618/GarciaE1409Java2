package com.garciaericn.memoryvault;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/16/14.
 */
public class NewMemoryActivity extends Activity {

    private static final String TAG = "NewMemoryActivity.TAG";

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
            case R.id.action_save_memory : {
                Log.i(TAG, "Save button pressed");
                // TODO: Save Memory
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
