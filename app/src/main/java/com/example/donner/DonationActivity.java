package com.example.donner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DonationActivity extends AppCompatActivity {
    private ListView mListView;
    private String[] donations = new String[]{
            "Mukuru Donations", "Kijabe Donations", "Nairobi donations", "Silas Donations",
            "Tevin Donations", "Mike Donations",
            "Yvonne Donations"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        mListView = (ListView) findViewById(R.id.listview);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, donations);
        mListView.setAdapter(adapter);

    }
}