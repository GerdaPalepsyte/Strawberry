package com.example.strawberry;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.core.app.ActivityCompat;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeActivity extends AppCompatActivity  {

    Activity activity;
    private ListView listView;
    private ListAdapter adapter;
    String day1 = "";
    int day2;

    public int mainDay;



    FeedReaderContract.FeedEntry.SymptomsDbHelper mydb;

    ArrayList<ListItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        activity = HomeActivity.this;
        mydb = new FeedReaderContract.FeedEntry.SymptomsDbHelper(activity);
        listView = (ListView) findViewById(R.id.listViewHome);
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        ImageView circle = (ImageView) findViewById(R.id.circlemage);
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(5000);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setInterpolator(new LinearInterpolator());
        circle.startAnimation(rotate);

        Intent intent = getIntent();
        if (intent.hasExtra("dayMain") != false) {
            day1 = intent.getStringExtra("dayMain");

            mainDay = Integer.parseInt(day1);
        } else {
            mainDay = 3;
        }
        TextView textDay = (TextView) findViewById(R.id.homeDayText);
        day2 = calendarView.getSelectedDate().get(Calendar.DAY_OF_MONTH);
        textDay.setText(String.valueOf(day2));



    }


    @Override
    protected void onStart() {
        super.onStart();
        //loadData(items);

        adapter = new ListAdapter(activity, items);
        listView.setAdapter(adapter);

    }

    public void periodStart(View view) {
        int periodDays;
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        List<Calendar> selectedDays = new ArrayList<>();
        //List<Calendar> periodDate = new ArrayList<>();
        Calendar date = calendarView.getFirstSelectedDate();
        //Calendar period = date;
        selectedDays.add(0, date);
        periodDays = date.get(Calendar.DAY_OF_MONTH);

        for (int i = 1; i < mainDay; i++) {
            periodDays = periodDays + 1;
            date.set(Calendar.SECOND, 0);
            date.set(Calendar.MINUTE, 13);
            date.set(Calendar.HOUR, 7);
            date.set(Calendar.AM_PM, Calendar.AM);
            date.set(Calendar.MONTH, date.get(Calendar.MONTH));
            date.set(Calendar.DAY_OF_MONTH, periodDays);
            date.set(Calendar.YEAR, date.get(Calendar.YEAR));
            //periodDate.add(period);
            selectedDays.add(i, date);


        }

        calendarView.setHighlightedDays(selectedDays);


    }





    public void toSymptoms(View view) {

        Transition slide = new Slide(Gravity.LEFT);

        getWindow().setExitTransition(slide.setDuration(1000));
        Intent myIntent = new Intent(HomeActivity.this, SymptomsActivity.class);
        //myIntent.putExtra("user", String.valueOf(user));
        HomeActivity.this.startActivity(myIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void toSettings(View view) {

        Transition slide = new Slide(Gravity.LEFT);

        getWindow().setExitTransition(slide.setDuration(1000));
        Intent myIntent = new Intent(HomeActivity.this, SettingsActivity.class);
        HomeActivity.this.startActivity(myIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }


}



