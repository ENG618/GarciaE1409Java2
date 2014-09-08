package com.garciaericn.forecaster.data;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.garciaericn.forecaster.R;

import java.util.List;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/8/14.
 */
public class WeatherAdapter extends ArrayAdapter<Weather> {

    private Context context;
    private List<Weather> objects;

    public WeatherAdapter(Context context, int resource, List<Weather> objects) {
        super(context,resource, objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//            convertView = LayoutInflater.from(context).inflate(R.layout.weather_list_item, parent, false);
//        }

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.weather_list_item, null);

        Weather weather = objects.get(position);

        TextView dayTV = (TextView) convertView.findViewById(R.id.dayOfWeekTV);
        dayTV.setText(weather.getDayOfWeek());

        TextView overview = (TextView) convertView.findViewById(R.id.overviewTV);
        overview.setText(weather.getCondition());

//        return convertView;
        return view;
    }
}
