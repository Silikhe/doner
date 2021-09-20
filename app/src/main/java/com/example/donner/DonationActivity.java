package com.example.donner;

import static android.content.Intent.createChooser;

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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DonationActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    private ImageView addDonations;
    private Button chooseImage;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        addDonations = (ImageView) findViewById(R.id.iv_add_donation);

        addDonations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogue();
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


//        recyclerView = findViewById(R.id.blogs_recycler);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);
//
//        blogsArrayList = new ArrayList<>();
//
//        recyclerViewAdapter = new RecyclerViewAdapter(this, blogsArrayList);
//        recyclerView.setAdapter(recyclerViewAdapter);


//        btn_load = findViewById(R.id.btn_load);

//        blogsArrayList = new ArrayList<>();
//        blogsArrayList.clear();
//
//        progressBar = new ProgressDialog(this);
//        progressBar.setTitle("Loading data blogs...");


//        loadPost();
//        mListView = (ListView) findViewById(R.id.listview);
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, donations);
//        mListView.setAdapter(adapter);

//        Intent intent = getIntent();
//        String name = intent.getStringExtra("username");

    }

    private void openDialogue() {
        StoryDialogue storyDialogue = new StoryDialogue();
        storyDialogue.show(getSupportFragmentManager(), "Story Dialog");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            imageUri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    Toast.makeText(DonationActivity.this, "Your image src: " + bitmap, Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    //    private void loadPost() {
//        progressBar.show();
//        if (nextToken.equals("")){
//            Log.d(TAG, "loadPost: Next Page token is empty, no more pages");
//            url = "https://www.googleapis.com/blogger/v3/blogs/"+Constants.BLOG_ID+"/posts?key="+Constants.API_KEY+"";
//        }
//    }


}