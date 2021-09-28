package com.example.donner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class FullBoxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_box);

     DonPanellActivity donPanellActivity = new DonPanellActivity();
     ArrayList<String> don = donPanellActivity.donations;
//     int donSize = don.size();
//        String donn = don.get(0);
//        Toast.makeText(FullBoxActivity.this, "Size" + don, Toast.LENGTH_SHORT).show();
    }
}