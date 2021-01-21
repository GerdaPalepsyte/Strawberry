package com.example.strawberry;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

    public class DayChoosing extends AppCompatActivity {

        Integer[] dayArray = {1,2,3,4,5,6,7,8};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        //TextView textView = (TextView)findViewById(R.id.spinnerDay);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerCho);


        DaySpinnerAdapter adapter = new DaySpinnerAdapter(this, R.layout.spinnerdays, dayArray);
        spinner.setAdapter(adapter);

    }

        public void openhome (View view) {
            String day;
            TextView days;
            days = (TextView)findViewById(R.id.spinnerDay);
            day = days.getText().toString();
            Transition slide = new Slide(Gravity.LEFT);

            getWindow().setExitTransition(slide.setDuration(1000));
            Intent mainIntent = new Intent(DayChoosing.this,HomeActivity.class);

            mainIntent.putExtra("dayMain", day);
            DayChoosing.this.startActivity(mainIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

        }
}
