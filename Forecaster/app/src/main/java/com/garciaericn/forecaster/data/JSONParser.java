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
            JSONArray forecast = data.getJSONArray("forecast");
            JSONObject txtForecast = forecast.getJSONObject(Integer.parseInt("txt_forecast"));
            JSONObject simpeForecast = forecast.getJSONObject(Integer.parseInt("simpleforecast"));

            // Loop through recipesJSON for desired info
            for (int i = 0; i < txtForecast.length(); i++) {
                Log.i(TAG, "In for loop at index: " + i + " Object: " + forecast.getJSONObject(i));

                // Obtain Fields
//                String dayOfWeek = forecast.getJSONObject(i);
//                String condition;
//                String forecastText;
//                String iconURL;

//                // Obtain Fields
//                String recipeID = matches.getJSONObject(i).getString("id");
//                String recipeName = matches.getJSONObject(i).getString("recipeName");
//                String sourceName = matches.getJSONObject(i).getString("sourceDisplayName");
//                String imgURL = matches.getJSONObject(i).getString("smallImageUrls");
//                //int cookTimeInSecondsInt = matches.getJSONObject(i).getInt("totalTimeInSeconds");
//                //String cookTimeInSeconds = matches.getJSONObject(i).getString("totalTimeInSeconds");
//                String course = matches.getJSONObject(i).getJSONObject("attributes").getString("course");

                // Create instance of Recipe
//                Recipe tempRecipe = new Recipe(recipeName,recipeID, sourceName, imgURL, course);
//
//                recipesJSON.add(tempRecipe);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "JSON array: " + weatherJSON);

        return weatherJSON;
    }
}
