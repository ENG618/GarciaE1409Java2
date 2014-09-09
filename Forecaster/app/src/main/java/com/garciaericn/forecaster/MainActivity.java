package com.garciaericn.forecaster;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.garciaericn.forecaster.data.JSONParser;
import com.garciaericn.forecaster.data.Weather;
import com.garciaericn.forecaster.fragments.DaysListFragment;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;


public class MainActivity extends Activity {

    public static final String TAG = "MainActivity.TAG";
    private static final String FILENAME = "SavedWeatherData";
    private Context context;
    private static String forecastURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate entered");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        // Check if saved data exists...if so load saved data
        if (checkFile(FILENAME)) {

            // Send JSON string to parsing method
            List<Weather> forecastList = JSONParser.parseForecast(readFromFile(FILENAME));

            // Populate weather list fragment into container
            DaysListFragment listFragment = DaysListFragment.newInstance(forecastList);

            // Create FragmentManager and Transaction
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.days_list_fragment, listFragment, DaysListFragment.TAG)
                    .commit();
        } else { // Otherwise get new data from api
            // Call api to get current forecast
            searchWeatherUnderground();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings: {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Setting fragment would go here.")
                        .setTitle("Settings")
                        .create()
                        .show();

                return true;
            }
            case R.id.action_refresh: {
                // Call api to get current forecast
                searchWeatherUnderground();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void writeToFile(Context context, String fileName, String data) {
        Log.i(TAG, "writeToFile entered");
        // Store data in "protected" directory
        File external = context.getExternalFilesDir(null);
        File file = new File(external, fileName);

        try {
            //Create new output stream
            FileOutputStream fos = new FileOutputStream(file);
            // Convert string to byte and write to stream
            fos.write(data.getBytes());
            // Close the stream to sve the file
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Boolean checkFile (String fileName) {
        Log.i(TAG, "checkFile entered");
        // Store data in "protected" directory
        File external = context.getExternalFilesDir(null);
        File file = new File(external, fileName);
        return file.exists();
    }

    private String readFromFile(String fileName) {
        Log.i(TAG, "readFromFile entered");

        String savedWeatherString = null;

        File external = getExternalFilesDir(null);
        File file = new File(external, fileName);

        try {
            FileInputStream fis = new FileInputStream(file);
            // Create stream readers
            InputStreamReader inReader = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(inReader);

            // Read data and pass to StringBuffer
            StringBuilder buffer = new StringBuilder();
            String text;
            // Make sure a line of text is available to be read
            while ((text = reader.readLine()) != null) {
                buffer.append(text);
            }
            Log.i(TAG, "String from file: " + buffer.toString());

            savedWeatherString = buffer.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return savedWeatherString;
    }

    public boolean checkNetworkStatus(){
        Log.i(TAG, "checkNetworkStatus entered");
        boolean isConnected;

        //Create connectivity manager
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        // Obtain status
        NetworkInfo ni = cm.getActiveNetworkInfo();
        // Check validity & if connected
        if (ni != null && ni.isConnected()) {
            Log.i(TAG, "Connection type: " + ni.getTypeName());
            Toast.makeText(this, "Connected to: " + ni.getTypeName(), Toast.LENGTH_SHORT).show();
            isConnected = true;
        }
        else {
            Toast.makeText(this, "Please connect to internet to search", Toast.LENGTH_SHORT).show();
            isConnected = false;
        }
        return isConnected;
    }

    public String getForecastURL() {
        // Log message
        Log.i(TAG, "getForecastURL entered");

        // Construct forecast string
        // TODO: If time permits, add search field to check custom location
        // Format: http://api.wunderground.com/api/88e112815576ce6e/forecast10day/q/FL/Orlando.json
        forecastURL = "http://api.wunderground.com/api/88e112815576ce6e/forecast10day/q/FL/Orlando.json";

        return forecastURL;
    }

    // Search method
    public void searchWeatherUnderground(){
        Log.i(TAG, "searchWeatherUnderground Entered");

        // Check if connected to internet
        if (checkNetworkStatus()){

            // Preform search
            getData data = new getData();
            data.execute(getForecastURL());
        }
    }

    // Fetch URL
    private static String getResponse(URL url) {
        // Log message
        Log.i(TAG, "getResponse entered");

        String response;
        try {
            //noinspection UnusedAssignment
            response = null;
            URLConnection conn = url.openConnection();
            BufferedInputStream bin = new BufferedInputStream(conn.getInputStream());
            byte[] contextByte = new byte[1024];
            //noinspection UnusedAssignment
            int byteRead = 0;
            //StringBuffer was producing an error so I switched it to String Builder
            StringBuilder responseBuilder = new StringBuilder();


            while ((byteRead = bin.read(contextByte)) != -1) {
                response = new String(contextByte, 0, byteRead);
                responseBuilder.append(response);
            }
            response = responseBuilder.toString();
            Log.i(TAG, "URL Response: " + response);
        } catch (IOException e) {
            response = "Something isn't right.  We didn't receive a response";
            Log.e(TAG, "Something went wrong", e);
        }

        return response;
    }

    // Obtain data from api
    private class getData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            // Declare local variable response string
            String responseString;

            try { // Try URL
                URL url = new URL(forecastURL);
                responseString = getResponse(url);
            } catch (Exception e) { // If error show response string and error
                responseString = "Something isn't right";
                Log.e(TAG, "ERROR: ", e);
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.i(TAG, "onPostExecute entered");
            Log.i(TAG, "Post Execute String: " + s);

            writeToFile(context, FILENAME, s);

            // Send JSON string to parsing method
            List<Weather> forecastList = JSONParser.parseForecast(s);
            Log.i(TAG, "The fully parsed json toString(): " + forecastList.toString());

            // Populate weather list fragment into container
            DaysListFragment listFragment = DaysListFragment.newInstance(forecastList);

            // Create FragmentManager and Transaction
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.days_list_fragment, listFragment, DaysListFragment.TAG)
                    .commit();

            super.onPostExecute(s);
        }
    }
}
