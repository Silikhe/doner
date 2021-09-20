package com.example.donner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mMainBtn = (Button) findViewById(R.id.continueBtn);
        mMainBtn.setOnClickListener((view)->{
//            Toast.makeText(MainActivity.this, "Silas Added Success fully!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }
}