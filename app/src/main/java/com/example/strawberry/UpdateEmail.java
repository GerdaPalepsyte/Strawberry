package com.example.strawberry;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UpdateEmail extends AppCompatActivity {

    EditText email;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeemail);
        email = (EditText) findViewById(R.id.editEmailsave);
    }

    public void changeEmail(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.updateEmail(email.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                        }
                    }
                });
    }
    public void emailChanged(View view) {

        changeEmail();
        Transition slide = new Slide(Gravity.RIGHT);

        getWindow().setExitTransition(slide.setDuration(1000));
        Intent myIntent = new Intent(UpdateEmail.this,SettingsActivity.class);
        UpdateEmail.this.startActivity(myIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}
