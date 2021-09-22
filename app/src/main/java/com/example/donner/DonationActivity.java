package com.example.donner;

import static android.content.Intent.createChooser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        donateFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogue();
            }
        });

        addDonations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Toast.makeText(DonationActivity.this, "User is now logged out!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(DonationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        chooseImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(DonationActivity.this, "Opening Gallery!", Toast.LENGTH_LONG).show();
////
//                Intent gallery = new Intent();
//                gallery.setType("image/*");
//                gallery.setAction(Intent.ACTION_GET_CONTENT);
//
//                startActivityForResult(createChooser(gallery, "select picture"), PICK_IMAGE);
//            }
//        });

        getAllStories();

    }

    private void getAllStories() {
        storyModels.clear();


        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                progressBar.setVisibility(View.GONE);

                storyModels.add(snapshot.getValue(StoryModel.class));
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