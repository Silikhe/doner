package com.example.donner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddDonation extends AppCompatActivity {
Button btn_add_donations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);

        btn_add_donations = (Button) findViewById(R.id.addDonBtn);
        btn_add_donations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDonation.this, DonPanellActivity.class);
                startActivity(intent);
            }
        });



    }
}