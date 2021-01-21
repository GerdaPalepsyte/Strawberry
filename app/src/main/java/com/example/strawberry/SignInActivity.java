/*
package com.example.strawberry;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    //private Button signIn = (Button) findViewById(R.id.buttonInIn);
    Activity activity;
    boolean loggedUser;

    private SignValidate validation;
    private FeedReaderContract.FeedEntry.SymptomsDbHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        activity = SignInActivity.this;
        mydb = new FeedReaderContract.FeedEntry.SymptomsDbHelper(activity);
        validation = new SignValidate(activity);
        username = (EditText) findViewById(R.id.editUsername);
        password = (EditText) findViewById(R.id.editPass);
        loggedUser = false;
    }

    public void openChoose (View view) {
        Intent mainIntent = new Intent(SignInActivity.this,HomeActivity.class);
        SignInActivity.this.startActivity(mainIntent);

    }

    public void onLogin(View view) {

        verifyFromSQLite();
        if (loggedUser == true) {

            Transition slide = new Slide(Gravity.LEFT);

            getWindow().setExitTransition(slide.setDuration(1000));
            Intent mainIntent = new Intent(SignInActivity.this, HomeActivity.class);
            SignInActivity.this.startActivity(mainIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }
    }

    private void verifyFromSQLite() {
        if (!validation.isInputEditTextFilled(username)) {
            return;
        }
        if (!validation.isInputEditTextFilled(password)) {
            return;
        }
        if (mydb.checkUsers(username.getText().toString().trim(),password.getText().toString().trim())) {
            emptyInputEditText();
            loggedUser = true;
        } else {
            Toast.makeText(getApplicationContext(), "There is no such user", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private void emptyInputEditText() {
        username.setText(null);
        password.setText(null);
    }
}

 */
