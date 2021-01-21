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
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText email;
    //private Button signIn = (Button) findViewById(R.id.buttonInIn);
    Activity activity;

    private SignValidate validation;
    private FeedReaderContract.FeedEntry.SymptomsDbHelper mydb;
    private User user;
    boolean addedUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        activity = SignUpActivity.this;
        mydb = new FeedReaderContract.FeedEntry.SymptomsDbHelper(activity);
        validation = new SignValidate(activity);
        username = (EditText) findViewById(R.id.editUsernamep);
        password = (EditText) findViewById(R.id.editPassUp);
        email = (EditText) findViewById(R.id.editEmail);
        user = new User();
        addedUser = false;

    }

    public void openChoose (View view) {
        Intent mainIntent = new Intent(SignUpActivity.this,HomeActivity.class);
        SignUpActivity.this.startActivity(mainIntent);

    }

    public void onRegister(View view){
        postDataToSQLite();
        if(addedUser == true) {
            Transition slide = new Slide(Gravity.LEFT).excludeTarget(findViewById(R.id.buttonUpUp),true);

            getWindow().setExitTransition(slide.setDuration(1000));
            Intent mainIntent = new Intent(SignUpActivity.this, DayChoosing.class);
            SignUpActivity.this.startActivity(mainIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }
    }


        private void postDataToSQLite() {
        if (!validation.isInputEditTextFilled(username)) {
            return;
        }
        if (!validation.isInputEditTextFilled(email)) {
            return;
        }
        if (!validation.isInputEditTextEmail(email)) {
            return;
        }
        if (!validation.isInputEditTextFilled(password)) {
            return;
        }
        if(mydb.isTableExists("user") == false){
            Log.i("table", "doesnt exist");
        }
        if (!mydb.checkUser(email.getText().toString().trim())) {
            user.setName(username.getText().toString().trim());
            user.setEmail(email.getText().toString().trim());
            user.setPassword(password.getText().toString().trim());
            mydb.addUser(user);
            emptyInputEditText();
            addedUser = true;
            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
            emptyInputEditText();
        } else {
            Toast.makeText(getApplicationContext(), "This user already exists", Toast.LENGTH_SHORT).show();
            return;
        }
    }
    /**
     * This method is to empty all input edit text
     */
/*
    private void emptyInputEditText() {
        username.setText(null);
        email.setText(null);
        password.setText(null);
    }
}
*/
