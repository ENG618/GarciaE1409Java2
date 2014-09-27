package com.garciaericn.weather.objects;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.garciaericn.weather.WeatherParser;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/26/14.
 */
public class CurrentWeather {

    private static final String TAG = "CurrentWeather.TAG";
    private String forecastURL;
    private Context context;

    public CurrentWeather(){

    }

    public void setContext(Context context) {
        this.context = context;
    }



    /**
     * Data getter for Weather Underground api
     * */

    public boolean checkNetworkStatus(){
        Log.i(TAG, "checkNetworkStatus entered");
        boolean isConnected = false;

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
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("To get updated information please connect your device to the internet")
                    .setTitle("No internet Connection")
                    .create()
                    .show();
        }
        return isConnected;
    }

    public String getForecastURL(String requestType) {
        // Log message
        Log.i(TAG, "getForecastURL entered");

        // Format: http://api.wunderground.com/api/88e112815576ce6e/REQUEST_TYPE/q/FL/Orlando.json
        // Construct forecast string
        forecastURL = "http://api.wunderground.com/api/88e112815576ce6e/" + requestType + "/q/FL/Orlando.json";

        return forecastURL;
    }

    // Search method
    public void searchWeatherUnderground(String stringURL){
        Log.i(TAG, "searchWeatherUnderground Entered");

        // Check if connected to internet
        if (checkNetworkStatus()){

            // Preform search
            getData data = new getData();
            data.execute(stringURL);
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

            // TODO: Parse and send to fragments
            WeatherParser parser = new WeatherParser();
            parser = parser.newInstance(s);

            parser.parseDataForType(WeatherParser.FORECAST);

//            // Send JSON string to parsing method
//            List<CurrentWeather> forecastList = JSONParser.parseForecast(s);
//            Log.i(TAG, "The fully parsed json toString(): " + forecastList.toString());
//
//            // Populate weather list fragment into container
//            DaysListFragment listFragment = DaysListFragment.newInstance(forecastList);
//
//            // Create FragmentManager and Transaction
//            getFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.days_list_fragment, listFragment, DaysListFragment.TAG)
//                    .commit();

            super.onPostExecute(s);
        }
    }
}
