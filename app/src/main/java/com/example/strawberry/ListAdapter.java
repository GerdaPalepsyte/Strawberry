package com.example.strawberry;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListAdapter extends ArrayAdapter<ListItem> {
    public ListAdapter(Context context, List<ListItem> objects) {
        super(context,R.layout.listitemdesign, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            LayoutInflater inflater =
                    (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listitemdesign, null);
        }

        TextView title = (TextView) view.findViewById(R.id.itemName);
        TextView description = (TextView) view.findViewById(R.id.itemDes);
        ImageView image = (ImageView) view.findViewById(R.id.imageList);

        ListItem item = getItem(position);
        //int length = 0;
        if(item.getImage()!=null) {
            //length = item.getImage().length;


            title.setText(item.getName());
            description.setText(item.getDescription());

            image.setImageBitmap(BitmapFactory.decodeByteArray(item.getImage(), 0, item.getImage().length));
        }

        return view;
    }
}
