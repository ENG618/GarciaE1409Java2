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

    // Privet fields
    private String city;
    private String weather;
    private String temp;

    public CurrentWeather(String city, String weather, String temp) {
        this.city = city;
        this.weather = weather;
        this.temp = temp;
    }
}