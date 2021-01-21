package com.example.strawberry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DaySpinnerAdapter extends ArrayAdapter<Integer> {

    private Context ctx;
    //private String[] contentArray;
    private Integer[] dayArray;


    public DaySpinnerAdapter(Context context, int resource,
                          Integer[] dayArray) {
        super(context,  R.layout.spinnerdays,R.id.spinnerDay, dayArray);
        this.ctx = context;
        //this.contentArray = objects;
        this.dayArray = dayArray;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.spinnerdays, parent, false);

        TextView textView = (TextView) row.findViewById(R.id.spinnerDay);
        textView.setText((dayArray[position]).toString());

        return row;
    }
}
