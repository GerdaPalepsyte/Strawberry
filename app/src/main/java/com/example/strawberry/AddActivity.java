package com.example.strawberry;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Log;
import android.util.LruCache;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    FeedReaderContract.FeedEntry.SymptomsDbHelper mydb;

    Integer[] imageArray = {R.drawable.acne, R.drawable.bra, R.drawable.headache,
            R.drawable.muslim,  R.drawable.nausea,  R.drawable.stomach};

    private Button addButton;
    String nameFinal;
    String desFinal;
    Bitmap image;
    ArrayList<ListItem> things = new ArrayList<>();
    private LruCache<String, Bitmap> mMemoryCache;

    //private DatabaseReference mDatabase;
    //private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ImageView imageView = (ImageView)findViewById(R.id.spinnerImage);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        SpinnerAdapter adapter = new SpinnerAdapter(this, R.layout.spinnertemdesign, imageArray);
        spinner.setAdapter(adapter);
        mydb = new FeedReaderContract.FeedEntry.SymptomsDbHelper(getApplicationContext());


        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };


        //mDatabase = FirebaseDatabase.getInstance().getReference();
        //mAuth = FirebaseAuth.getInstance();
    }

    /*
    private void writeNewSymptom(String userId,String name, String description, String icon) {
        ListItem symptom = new ListItem(name,icon,description);

        mDatabase.child("Symptoms").child(userId).setValue(symptom);
    }

     */



    public void addSymptom(View view) {
        EditText name = (EditText) findViewById(R.id.editSymptom);
        EditText description = (EditText) findViewById(R.id.editDes);
        ImageView icon = (ImageView) findViewById(R.id.spinnerImage);
        BitmapDrawable drawable = (BitmapDrawable) icon.getDrawable();

        nameFinal = name.getText().toString();
        desFinal = description.getText().toString();
        image = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);

        byte [] imageList = stream.toByteArray();
        //String str = new String(imageList, StandardCharsets.UTF_8);
        Toast.makeText(getApplicationContext(), "Symptom added.", Toast.LENGTH_SHORT).show();
        //image.recycle();


        mydb.insertContact(nameFinal,desFinal,imageList);





        //writeNewSymptom(mAuth.getUid(),nameFinal,desFinal,str);
        name.setText(null);
        description.setText(null);



    }




    public void returnToSymptoms(View view) {

        Transition slide = new Slide(Gravity.RIGHT).excludeTarget(findViewById(R.id.buttonbackAdd),true);

        getWindow().setExitTransition(slide.setDuration(1000));
        Intent myIntent = new Intent(AddActivity.this,SymptomsActivity.class);
        myIntent.putExtra("works", "1");
        AddActivity.this.startActivity(myIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }


}
