package com.garciaericn.memoryvault.data;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.garciaericn.memoryvault.R;

import java.util.List;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/18/14.
 */
public class MemoryAdapter extends ArrayAdapter<Memory> {

    private static final String TAG = "MemoryAdapter.TAG";
    private Context context;
    private List<Memory> objects;

    public MemoryAdapter(Context context, int resource, List<Memory> memoryList) {
        super(context,resource, memoryList);
        this.context = context;
        this.objects = memoryList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "getView entered");

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.memory_list_item, null);

        Memory memory = objects.get(position);

        TextView eventTV = (TextView) view.findViewById(R.id.list_item_title);
        eventTV.setText(memory.getEventName());

        TextView eventLocationTV = (TextView) view.findViewById(R.id.list_item_location);
        eventLocationTV.setText(memory.getEventLocation());

        TextView numGuestsTV = (TextView) view.findViewById(R.id.list_item_guest_count);
        numGuestsTV.setText(Integer.toString(memory.getNumGuests()));

        return view;
    }

    public void refresh(List<Memory> memoryList) {
        this.objects = memoryList;

    }
}
