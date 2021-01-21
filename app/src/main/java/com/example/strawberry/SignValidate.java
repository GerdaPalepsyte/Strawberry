package com.example.strawberry;

import android.content.Context;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

public class SignValidate {
    private Context context;

    /**
     * constructor
     *
     * @param context
     */
    public SignValidate(Context context) {
        this.context = context;
    }

    public boolean isInputEditTextFilled(EditText username) {
        String value = username.getText().toString().trim();
        if (value.isEmpty()) {
            Toast.makeText(context, "Please fill all", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }

    }
    public boolean isInputEditTextEmail(EditText email) {
        String value = email.getText().toString().trim();
        //if (value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            if(value.isEmpty()) {
                Toast.makeText(context, "Please enter email", Toast.LENGTH_SHORT).show();
                return false;
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
                Log.i("email", value);
                Toast.makeText(context, "Please enter valid email", Toast.LENGTH_SHORT).show();
                return false;
            }
            else {
                return true;
            }

    }

}
