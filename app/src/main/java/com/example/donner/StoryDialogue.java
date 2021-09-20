package com.example.donner;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StoryDialogue extends AppCompatDialogFragment {

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

    }
}
