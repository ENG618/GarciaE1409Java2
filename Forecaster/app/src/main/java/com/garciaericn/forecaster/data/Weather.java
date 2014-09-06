package com.garciaericn.forecaster.data;

import java.net.URL;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/6/14.
 */
public class Weather {

    // Constants for field references

    // Privet fields
    private String dayOfWeek;
    private String condition;
    private String forecastText;
    private URL iconURL;

    // Constructor method
    public Weather(String day, String condition, String forecast, URL iconURL) {
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

    public URL getIconURL() {
        return iconURL;
    }

    public void setIconURL(URL iconURL) {
        this.iconURL = iconURL;
    }
}
