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

public class UpdatePassword extends AppCompatActivity {

    EditText newPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);
        newPassword = (EditText)findViewById(R.id.editpassew);
    }

    public void changePassword(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.updatePassword(newPassword.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                        }
                    }
                });
    }
    public void passwordChanged(View view) {

        changePassword();
        Transition slide = new Slide(Gravity.RIGHT);

        getWindow().setExitTransition(slide.setDuration(1000));
        Intent myIntent = new Intent(UpdatePassword.this,SettingsActivity.class);
        UpdatePassword.this.startActivity(myIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}
