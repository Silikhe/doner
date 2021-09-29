package com.example.donner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageView back_btn;
    private ExtendedFloatingActionButton add_story;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);

        progressBar = (ProgressBar) findViewById(R.id.pbLoading);


        recyclerView = findViewById(R.id.rec_stations);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StationActivity.this, DonationActivity.class);
                startActivity(intent);
            }
        });


        add_story = findViewById(R.id.add_fab);
        add_story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                StoryDialogue storyDialogue = new StoryDialogue();
//                storyDialogue.postStory();
            }
        });



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://redcross.or.ke/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonMakers jsonMakers = retrofit.create(JsonMakers.class);

        Call<StationWrapper> call = jsonMakers.getStations();
//        progressBar.show

        call.enqueue(new Callback<StationWrapper>() {
            @Override
            public void onResponse(Call<StationWrapper> call, Response<StationWrapper> response) {
                progressBar.setVisibility(View.GONE);
    
                if (!response.isSuccessful()) {
                    Toast.makeText(StationActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Stations> stationsList = response.body().getMarkers();
                StationAdapter stationAdapter = new StationAdapter(StationActivity.this, stationsList);
                recyclerView.setAdapter(stationAdapter);
            }

            @Override
            public void onFailure(Call<StationWrapper> call, Throwable t) {
                Toast.makeText(StationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}