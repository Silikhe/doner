package com.example.donner;

import static android.content.Intent.createChooser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;

public class DonationActivity extends AppCompatActivity implements StoryRVAdapter.StoryClickInterface {

    private static final int PICK_IMAGE = 1;
    private static final String TAG = "DonationActivity";
    private ImageView addDonations;
    private Button chooseImage;
    Uri imageUri;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private FloatingActionButton donateFab;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<StoryModel> storyModels;
    private RelativeLayout bottomSheet;
    private StoryRVAdapter storyRVAdapter;
    private FirebaseAuth mAuth;
    String storyId;
    Context context = this;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    Intent intent = new Intent(context, DonationActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
//                    Toast.makeText(DonationActivity.this, "Home Selected", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.story:
                    openDialogue();
                return true;
                case R.id.logout:
                    mAuth.signOut();
                    Intent i = new Intent(DonationActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                    Toast.makeText(DonationActivity.this, "User is now logged out!", Toast.LENGTH_LONG).show();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        donateFab = (FloatingActionButton) findViewById(R.id.donate);
        recyclerView = (RecyclerView) findViewById(R.id.donationsRecycler);
        progressBar = (ProgressBar) findViewById(R.id.pbLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Stories");
        storyModels = new ArrayList<>();
        bottomSheet = (RelativeLayout) findViewById(R.id.donRl);
        storyRVAdapter = new StoryRVAdapter(storyModels, this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(storyRVAdapter);
        addDonations = findViewById(R.id.iv_add_donation);
        mAuth = FirebaseAuth.getInstance();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        donateFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonationActivity.this, AddDonation.class);
                startActivity(intent);
                Toast.makeText(DonationActivity.this, "fab clicked", Toast.LENGTH_LONG).show();
            }
        });

        addDonations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DonationActivity.this, "No notification at the moment", Toast.LENGTH_LONG).show();
            }
        });

        getAllStories();

    }

    private void getAllStories() {
        storyModels.clear();


        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                progressBar.setVisibility(View.GONE);

//                storyModels.add(snapshot.getValue(StoryModel.class));
                storyRVAdapter.notifyDataSetChanged();

//                if (snapshot.child("storyDesc").exists() &&
//                        snapshot.child("storyTitle").exists() &&
//                        snapshot.child("storyImage").exists()){
//                    Log.d(TAG, "onChildChanged:" + snapshot.toString());
//                    StoryModel command = snapshot.getValue(StoryModel.class);
//                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                progressBar.setVisibility(View.GONE);
                storyRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                progressBar.setVisibility(View.GONE);
                storyRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void openDialogue() {
        StoryDialogue storyDialogue = new StoryDialogue();
        storyDialogue.show(getSupportFragmentManager(), "Story Dialog");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                Toast.makeText(DonationActivity.this, "Your image src: " + bitmap, Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onStoryClick(int position) {
        displayBottomSheet(storyModels.get(position));
    }

    private void displayBottomSheet(StoryModel storyModel) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_dialog, bottomSheet);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

        TextView storyContent = layout.findViewById(R.id.contents);
        storyContent.setText(storyModel.getStoryDesc());
    }


}