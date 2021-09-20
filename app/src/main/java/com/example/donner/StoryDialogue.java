package com.example.donner;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StoryDialogue extends AppCompatDialogFragment {

    private static final String TAG = "Success";
    private EditText donTitle, donDesc, donImage;
    String storyTitle, storyDesc, storyImage;
    private ProgressBar loadingPg;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    String storyId;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialogue, null);


        builder.setView(view)
                .setTitle("Create Donation Story")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Add Story", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        postStory();
                    }
                });

        donTitle = view.findViewById(R.id.story_title);
        donDesc = view.findViewById(R.id.story_description);
        donImage = view.findViewById(R.id.img_url);


        return builder.create();
    }

    private void postStory() {
        databaseReference = firebaseDatabase.getReference("Story");

        storyTitle = donTitle.getText().toString();
        storyDesc = donDesc.getText().toString();
        storyImage = donImage.getText().toString();

        storyId = storyTitle;

        StoryModel storyModel = new StoryModel(storyId, storyTitle, storyDesc, storyImage);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.child(storyId).setValue(storyModel);
//                Toast.makeText(, "", Toast.LENGTH_SHORT).show();
//                Toast.makeText(StoryDialogue.this, "Logged in Successfully!", Toast.LENGTH_LONG).show();
                Log.d(TAG, "Message added successfully: ");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, "There was an error: "+ error);
            }
        });

    }
}
