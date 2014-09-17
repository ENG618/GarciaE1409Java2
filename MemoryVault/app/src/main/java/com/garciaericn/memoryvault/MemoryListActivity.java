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
import com.garciaericn.memoryvault.fragments.MemoryListFragment;
import com.garciaericn.memoryvault.fragments.SettingsFragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class MemoryListActivity extends Activity {

    public static final String TAG = "MemoryListActivity.TAG";
    public static final String FILENAME = "SavedMemories";

    private Context context;
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate entered");
        setContentView(R.layout.activity_memory_list);

        // Cache context & preferences
        context = this;
        settings = PreferenceManager.getDefaultSharedPreferences(this);

        // Check if first launch
        checkFirstLaunch();

        // Create instance of Memory list fragment
        MemoryListFragment frag = new MemoryListFragment();

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.memory_list_fragment_container, frag, MemoryListFragment.TAG);
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
                // TODO: launch add activity/fragment
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private Boolean checkFile (String fileName) {
        Log.i(TAG, "checkFile entered");
        // Store data in "protected" directory
        File external = context.getExternalFilesDir(null);
        File file = new File(external, fileName);
        return file.exists();
    }

    private void writeToFile(Context context, String fileName, String data) {
        Log.i(TAG, "writeToFile entered");
        // TODO: Save Data to protected storage
//        // Store data in "protected" directory
//        File external = context.getExternalFilesDir(null);
//        File file = new File(external, fileName);
//
//        try {
//            //Create new output stream
//            FileOutputStream fos = new FileOutputStream(file);
//            // Convert string to byte and write to stream
//            fos.write(data.getBytes());
//            // Close the stream to sve the file
//            fos.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private String readFromFile(String fileName) {
        Log.i(TAG, "readFromFile entered");
        // TODO: Read data from saved file

//        String savedWeatherString = null;
//
//        File external = getExternalFilesDir(null);
//        File file = new File(external, fileName);
//
//        try {
//            FileInputStream fis = new FileInputStream(file);
//            // Create stream readers
//            InputStreamReader inReader = new InputStreamReader(fis);
//            BufferedReader reader = new BufferedReader(inReader);
//
//            // Read data and pass to StringBuffer
//            StringBuilder buffer = new StringBuilder();
//            String text;
//            // Make sure a line of text is available to be read
//            while ((text = reader.readLine()) != null) {
//                buffer.append(text);
//            }
//            Log.i(TAG, "String from file: " + buffer.toString());
//
//            savedWeatherString = buffer.toString();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return savedWeatherString;
        return null;
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

        // Load dummy data
        Memory mem1 = new Memory("Project 3", 1, "Home", "Coming along pretty good so far");
        Memory mem2 = new Memory("Family Vacation", 4, "Ruskin, FL", "Had some well deserved quality time with the family");
        Memory mem3 = new Memory("Birthday", 25, "Party house", "HAPPY BIRTHDAY!!");
        Memory mem4 = new Memory("Anniversary", 2, "Romantic restaurant", "Always fun spending time with my wife");

        // TODO: Add to hashMap, and save to file
    }
}
