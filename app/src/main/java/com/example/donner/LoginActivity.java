package com.example.donner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private static final int PASSWORD_LENGTH = 6;
    EditText mUsername;
    EditText mPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewInitializations();
    }

    void viewInitializations(){
        mUsername= findViewById(R.id.et_email);

        //set text from login intent
            Intent login = getIntent();
        String username = login.getStringExtra("username");
        mUsername.setText(username);

        mPassword= findViewById(R.id.et_password);
    }

    boolean validateInput() {

        if (mUsername.getText().toString().isEmpty()){
            mUsername.setError("Enter Email");
            return false;
        }
        if (mUsername.getText().toString().equals("")){
            mUsername.setError("Enter Email");
            return false;
        }
        if(mPassword.getText().toString().isEmpty() || mPassword.getText().toString().length()< PASSWORD_LENGTH){
            mPassword.setError("Enter valid password");
            return false;
        }
        return true;
    }

    public void viewTopics (View view) {
        if (validateInput()) {

            // Input is valid, here send data to your server

            String username = mUsername.getText().toString();
            String password = mPassword.getText().toString();

            Intent topics = new Intent(LoginActivity.this, DonationActivity.class);
            topics.putExtra("username", username);

            startActivity(topics);

            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();

        }
    }

}