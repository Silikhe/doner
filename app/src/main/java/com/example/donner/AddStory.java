package com.example.donner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddStory extends AppCompatActivity {

    private static final String TAG = "StoryDialogue";
    private EditText donTitle, donDesc, donImage;
    String storyTitle, storyDesc, storyImage;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    String storyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story);

        donTitle = findViewById(R.id.story_title);
        donTitle = findViewById(R.id.story_title);
        donDesc = findViewById(R.id.story_description);
        donImage = findViewById(R.id.img_url);


        databaseReference = FirebaseDatabase.getInstance().getReference("Stories");

        storyTitle = donTitle.getText().toString();
        storyDesc = donDesc.getText().toString();
        storyImage = donImage.getText().toString();
        storyId = storyTitle;

        StoryModel story = new StoryModel(storyId, storyTitle, storyDesc, storyImage);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.child(storyId).setValue(story);
                Log.d(TAG, "Message added successfully:" + databaseReference);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, "There was an error: " + error);
            }
        });


    }
}