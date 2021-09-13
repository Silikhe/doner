package com.example.donner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DonationActivity extends AppCompatActivity {
//    private ListView mListView;
//    private String[] donations = new String[]{
//            "Mukuru Donations", "Kijabe Donations", "Nairobi donations", "Silas Donations",
//            "Tevin Donations", "Mike Donations",
//            "Yvonne Donations"
//    };

    RecyclerView recyclerView;
    ArrayList<Blogs> blogsArrayList;
    RecyclerViewAdapter recyclerViewAdapter;
    String title;
    String displayName;
    String img_url;
    String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        recyclerView = findViewById(R.id.blogs_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        blogsArrayList = new ArrayList<>();

        recyclerViewAdapter = new RecyclerViewAdapter(this, blogsArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);



//        mListView = (ListView) findViewById(R.id.listview);
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, donations);
//        mListView.setAdapter(adapter);

    }
}