package com.example.strawberry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void openIn (View view) {
        Transition slide = new Slide(Gravity.LEFT).excludeTarget(findViewById(R.id.buttonIn),true);

        getWindow().setExitTransition(slide.setDuration(1000));
        Intent mainIntent = new Intent(MainActivity.this,LoginActivity.class);
        MainActivity.this.startActivity(mainIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());


    }

    public void openUp (View view) {

        Transition slide = new Slide(Gravity.LEFT).excludeTarget(findViewById(R.id.buttonUp),true);

        getWindow().setExitTransition(slide.setDuration(1000));
        Intent mainIntent = new Intent(MainActivity.this,RegisterActivty.class);
        MainActivity.this.startActivity(mainIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());


    }
}