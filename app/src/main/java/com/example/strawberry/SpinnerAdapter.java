package com.example.strawberry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.strawberry.R;

public class SpinnerAdapter extends ArrayAdapter<Integer> {
    private Context ctx;
    //private String[] contentArray;
    private Integer[] imageArray;


    public SpinnerAdapter(Context context, int resource,
                          Integer[] imageArray) {
        super(context,  R.layout.spinnertemdesign,R.id.spinnerImage, imageArray);
        this.ctx = context;
        //this.contentArray = objects;
        this.imageArray = imageArray;
    }

    @Override
    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.spinnertemdesign, parent, false);

        //TextView textView = (TextView) row.findViewById(R.id.spinnertextView);
        //textView.setText(contentArray[position]);

        ImageView imageView = (ImageView)row.findViewById(R.id.spinnerImage);
        imageView.setImageResource(imageArray[position]);

        return row;
    }
}
