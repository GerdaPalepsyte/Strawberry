package com.example.strawberry;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SymptomsActivity extends AppCompatActivity {

    Activity activity;
    private ListView listView;
    private ListAdapter adapter;
    String user;


    FeedReaderContract.FeedEntry.SymptomsDbHelper mydb;

    ArrayList<ListItem> items = new ArrayList<>();


    //private DatabaseReference mPostReference;
    private String mPostKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

        activity = SymptomsActivity.this;
        mydb = new FeedReaderContract.FeedEntry.SymptomsDbHelper(activity);
        listView = (ListView) findViewById(R.id.listViewSym);


        adapter = new ListAdapter(activity, items);
        listView.setAdapter(adapter);
        //mPostReference = FirebaseDatabase.getInstance().getReference("Symptoms");






        }
    @Override
    protected void onResume() {
        super.onResume();
        items.clear();
        adapter.notifyDataSetChanged();
        loadData(items);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Cursor cursor = mydb.getDataSpecific(String.valueOf(listView.getItemIdAtPosition(position)+1));

                cursor.moveToFirst();

                while (cursor.isAfterLast() == false) {
                    mydb.insertContactHome(cursor.getString(1), cursor.getString(0), cursor.getBlob(2));
                    Toast.makeText(getApplicationContext(), "Symptom added.", Toast.LENGTH_SHORT).show();

                    cursor.moveToNext();

                }




            }
        });


    }




        public void deleteSmptom(View view) {
            Cursor cursor = mydb.fetch();
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                ListItem item = new ListItem();
                mydb.delete(item.getName());
                cursor.moveToNext();
            }
        }








    public void loadData( ArrayList<ListItem> dataList){

        Cursor cursor = mydb.fetch();
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            ListItem item = new ListItem();
            item.setName(cursor.getString(0));
            item.setDescription(cursor.getString(1));
            item.setImage(cursor.getBlob(2));
            dataList.add(item);
            cursor.moveToNext();
        }


    }




    public void returnToMain(View view) {

        Transition slide = new Slide(Gravity.RIGHT);

        getWindow().setExitTransition(slide.setDuration(1000));
        Intent myIntent = new Intent(SymptomsActivity.this,HomeActivity.class);
        SymptomsActivity.this.startActivity(myIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void goToAdd(View view) {

        Transition slide = new Slide(Gravity.LEFT).excludeTarget(findViewById(R.id.buttonSymadd),true);

        getWindow().setExitTransition(slide.setDuration(1000));
        Intent myIntent = new Intent(SymptomsActivity.this,AddActivity.class);
        myIntent.putExtra("username", user);
        SymptomsActivity.this.startActivity(myIntent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}
