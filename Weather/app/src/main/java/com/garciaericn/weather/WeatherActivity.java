package com.garciaericn.weather;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.garciaericn.weather.fragments.CurrentWeatherFragment;
import com.garciaericn.weather.objects.CurrentWeather;


public class WeatherActivity extends Activity
        implements ActionBar.TabListener, CurrentWeatherFragment.ForecastFragmentCallback {

    private static final String TAG = "WeatherActivity.TAG";
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    private String forecastURL;
    private String returnedCurrentWeather;
    private String returnedFoewcastWeather;
    private String returnedHourlyWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            switch (position) {
                case 0 : {
                    return CurrentWeatherFragment.newInstance(position + 1);
                }case 1 : {
                    return CurrentWeatherFragment.newInstance(position + 1);
                }case 2 : {
                    return CurrentWeatherFragment.newInstance(position + 1);
                }
                default: return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

//    /**
//     * A placeholder fragment containing a simple view.
//     */
//    public static class PlaceholderFragment extends Fragment {
//        /**
//         * The fragment argument representing the section number for this
//         * fragment.
//         */
//        private static final String ARG_SECTION_NUMBER = "section_number";
//
//        /**
//         * Returns a new instance of this fragment for the given section
//         * number.
//         */
//        public static PlaceholderFragment newInstance(int sectionNumber) {
//            PlaceholderFragment fragment = new PlaceholderFragment();
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
//            return fragment;
//        }
//
//        public PlaceholderFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_current_weather, container, false);
//            return rootView;
//        }
//    }

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

    /**
     *
     * Callbacks interfaces
     * */

    @Override
    public List<CurrentWeather> getCurrentWeather(String requestType) {
        searchWeatherUnderground(getForecastURL("conditions"));


        return null;
    } }
