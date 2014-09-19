package com.garciaericn.memoryvault;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.garciaericn.memoryvault.data.Memory;
import com.garciaericn.memoryvault.data.MemoryManager;
import com.garciaericn.memoryvault.fragments.MemoryListFragment;
import com.garciaericn.memoryvault.fragments.SettingsFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemoryListActivity extends Activity
    implements MemoryListFragment.MemoryListFragmentCallback{

    public static final String TAG = "MemoryListActivity.TAG";
    private static final int NEW_MEM_CODE = 1234;
    public static final String MEMORYBUNDLE = "com.garciaericn.memoryvault.MEMORYBUNDLE";

    private Context context;
    private SharedPreferences settings;
    private MemoryManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate entered");
        setContentView(R.layout.activity_memory_list);

        // Cache context, preferences, & manager
        context = this;
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        manager = new MemoryManager();
        manager.newInstance(this);

        // Check if first launch
        checkFirstLaunch();

        loadList();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.memory_list, menu);
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
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.action_add_memory : {
                Intent intent = new Intent(this, NewMemoryActivity.class);
                startActivity(intent);
//                startActivityForResult(intent, NEW_MEM_CODE);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadList(){
        // Obtain HasMap of memories from manager
        HashMap map = manager.getMemories();

        // Create
        List<Memory> memoryList = new ArrayList<Memory>(map.values());

        // Create instance of Memory list fragment
        MemoryListFragment frag = MemoryListFragment.newInstance(memoryList);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.memory_list_fragment_container, frag, MemoryListFragment.TAG)
                .commit();
    }

    private void checkFirstLaunch() {
        Log.i(TAG, "checkFirstLaunch entered");

        // Check if first launch
        if (settings.getBoolean(SettingsFragment.FIRST_LAUNCH, true)) {
            Log.i(TAG, "This is first time app has been launched");

            loadDummyMemories();

            // Change preference to reflect app launch
            settings.edit()
                    .putBoolean(SettingsFragment.FIRST_LAUNCH, false)
                    .apply();
        } else {
            Log.i(TAG, "App has been launched previously");
        }
    }

    private void loadDummyMemories() {
        Log.i(TAG, "loadDummyMemories entered");

        manager.addMemory(new Memory("Project 3", 1, "Home", "Coming along pretty good so far"));
        manager.addMemory(new Memory("Family Vacation", 4, "Ruskin, FL", "Had some well deserved quality time with the family"));
        manager.addMemory(new Memory("Birthday", 25, "Party house", "HAPPY BIRTHDAY!!"));
        manager.addMemory(new Memory("Anniversary", 2, "Romantic restaurant", "Always fun spending time with my wife"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_MEM_CODE && resultCode == RESULT_OK) {
            Bundle result = data.getExtras();
            Memory newMemory = new Memory(result);

            manager.addMemory(newMemory);
        }
    }

    @Override
    public void onItemSelected(Memory memory) {

        Bundle b = memory.toBundle();
//        Intent intent = new Intent(this, )
    }
}
