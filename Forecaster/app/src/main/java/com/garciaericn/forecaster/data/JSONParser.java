package com.garciaericn.forecaster.data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/7/14.
 */
public class JSONParser {

    public static final String TAG = "JSONParser.TAG";


    public static List<Weather> parseForecast(String jsonString) {
        Log.i(TAG, "parseForecast entered");

        List<Weather> weatherJSON = new ArrayList<Weather>();

        try {
            // Create JSON
            JSONObject data = new JSONObject(jsonString);

            // Create Array of weather objects
            JSONObject forecast = new JSONObject(data.getJSONObject("forecast").toString());
            JSONObject txtForecast = new JSONObject(forecast.getJSONObject("txt_forecast").toString());
            JSONArray forecastDay = txtForecast.getJSONArray("forecastday");


            // TODO: Add more details to weather object with following info.
//            JSONObject simpleForecast = forecast.getJSONObject(forecast.getJSONObject("simpleforecast").toString());
//            JSONArray simpleForecastDay = simpleForecast.getJSONArray("forecastday");

            // Loop through recipesJSON for desired info
            for (int i = 0; i < forecastDay.length(); i++) {
                Log.i(TAG, "In for loop at index: " + i + " Object: " + forecastDay.getJSONObject(i));

                // Obtain Fields
                String dayOfWeek = forecastDay.getJSONObject(i).getString("title");
                String condition = forecastDay.getJSONObject(i).getString("icon");
                String forecastText = forecastDay.getJSONObject(i).getString("fcttext");
                String iconURL = forecastDay.getJSONObject(i).getString("icon_url");

                // Create instance of Weather
                Weather tempWeather = new Weather(dayOfWeek, condition, forecastText, iconURL);

                weatherJSON.add(tempWeather);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "JSON array: " + weatherJSON);

        return weatherJSON;
    }
}
