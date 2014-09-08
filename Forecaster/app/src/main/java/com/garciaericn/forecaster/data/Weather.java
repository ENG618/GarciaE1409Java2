package com.garciaericn.forecaster.data;

import android.os.Bundle;

import java.io.Serializable;
import java.net.URL;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/6/14.
 */
public class Weather implements Serializable{
    public static final long serialVersionUID = 5573468465465465498L;

    // Constants for field references
    public static final String DAY_OF_WEEK = "com.garciaericn.forecaster.dayOfWeek";
    public static final String CONDITION = "com.garciaericn.forecaster.condition";
    public static final String FORCAST_TEXT = "com.garciaericn.forecaster.forecastText";
    public static final String ICON_URL = "com.garciaericn.forecaster.iconURL";

    // Privet fields
    private String dayOfWeek;
    private String condition;
    private String forecastText;
    private String iconURL;

    // Constructor method
    public Weather(String day, String condition, String forecast, String iconURL) {
        this.dayOfWeek = day;
        this.condition = condition;
        this.forecastText = forecast;
        this.iconURL = iconURL;
    }

    // Getters and Setters


    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getForecastText() {
        return forecastText;
    }

    public void setForecastText(String forecastText) {
        this.forecastText = forecastText;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    // Package data for transfer between fragments
    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putString(DAY_OF_WEEK, this.dayOfWeek);
        b.putString(CONDITION, this.condition);
        b.putString(FORCAST_TEXT, this.forecastText);
        b.putString(ICON_URL, this.iconURL);

        return b;
    }

    // Create Weather object from bundle
    public Weather(Bundle b) {
        if (b != null) {
            this.dayOfWeek = b.getString(DAY_OF_WEEK);
            this.condition = b.getString(CONDITION);
            this.forecastText = b.getString(FORCAST_TEXT);
            this.iconURL = b.getString(ICON_URL);
        }
    }
}
