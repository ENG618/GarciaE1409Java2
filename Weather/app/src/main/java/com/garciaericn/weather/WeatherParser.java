package com.garciaericn.weather;

import android.util.Log;

import com.garciaericn.weather.objects.CurrentWeather;
import com.garciaericn.weather.objects.ForecastWeather;
import com.garciaericn.weather.objects.HourlyWeather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/25/14.
 */
public class WeatherParser {
    private static final String TAG = "WeatherParser.TAG";

    // Constants for each type of data to parse
    public static final String FORECAST = "forecast";
    public static final String CURRENT = "current_observation";
    public static final String HOURLY = "hourly_forecast";

    // Constants for forecast
    public static final String TITLE = "title";
    public static final String FCTTEXT = "fcttext";

    // Constants for current
    private static final String OBSERVATION = "current_observation";
    private static final String FORECASTTXT = "txt_forecast";
    private static final String CITY = "city";
    private static final String WEATHER = "weather";
    private static final String TEMP = "temp";

    // Constants for hourly
    private static final String HOURLYFORECAST = "hourly_forecast";
    private static final String FCTTIME = "FCTTIME";


    private WeatherParser weatherParser;
    private String JSONString;

    public WeatherParser(){
    }

    public WeatherParser newInstance(String data) {
        JSONString = data;
        if (weatherParser == null) {
            weatherParser = new WeatherParser();
        }
        return weatherParser;
    }

    public HashMap<String, String> parseDataForType(String requestType) {


        if (requestType.equals(FORECAST)) {
            return parseForecast();
        } else if (requestType.equals(CURRENT)) {
            return parseCurrent();
        } else if (requestType.equals(HOURLY)) {
            return parseHourly();
        }
        return null;
    }

    private HashMap<String, ForecastWeather> parseForecast() {
        Log.i(TAG, "parseForecast entered");

        HashMap<String, String> forecastMap = new HashMap<String, String>();
        try {
            JSONObject data = new JSONObject(JSONString);

            JSONObject forecast = new JSONObject(data.getJSONObject(FORECAST).toString());
            JSONObject txtForecast = new JSONObject(forecast.getJSONObject(FORECASTTXT).toString());
            JSONArray forecastDay = txtForecast.getJSONArray("forecastday");

            // Loop through data
            for (int i = 0; i < forecastDay.length(); i++) {
                Log.i(TAG, "In for loop at index: " + i + " Object: " +forecastDay.getJSONObject(i));

                // Obtain fields
                String dayOfWeek = forecastDay.getJSONObject(i).getString(TITLE);
                String condition = forecastDay.getJSONObject(i).getString(FCTTEXT);

                forecast.put(TITLE, dayOfWeek);
                forecast.put(FCTTEXT, condition);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return forecastMap;
    }

    private HashMap<String,CurrentWeather> parseCurrent() {
        Log.i(TAG, "parseCurrent entered");

        HashMap<String, String> currentMap = new HashMap<String, String>();
        try {
            JSONObject data = new JSONObject(JSONString);

            JSONObject observation = new JSONObject(data.getJSONObject(OBSERVATION).toString());
            JSONObject location = new JSONObject(observation.getJSONObject("observation_location").toString());

            currentMap.put(CITY, location.getString("city"));
            currentMap.put(WEATHER, observation.getString("weather"));
            currentMap.put(TEMP, String.valueOf(observation.getInt("temp_f")));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return currentMap;
    }

    private HashMap<String, HourlyWeather> parseHourly() {
        Log.i(TAG, "parseHourly entered");

        HashMap<String, String> hourlyMap = new HashMap<String, String>();
        try {
            JSONObject data = new JSONObject(JSONString);

            JSONArray hourly = data.getJSONArray(HOURLYFORECAST);

            // Loop through hourly array
            for (int i = 0; i < hourly.length(); i++) {
                Log.i(TAG, "In for loop at index: " + i + "for object: " + hourly.getJSONObject(i));

                JSONObject time = new JSONObject(FCTTIME);

                // TODO: Finish parsing data
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hourlyMap;
    }
}
