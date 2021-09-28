package com.example.donner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddDonation extends AppCompatActivity {
Button btn_add_donations;
ImageView btn_full_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);

        btn_add_donations = (Button) findViewById(R.id.addDonBtn);
        btn_add_donations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(AddDonation.this, DonPanellActivity.class);
//                startActivity(intent);
                openDialogue();
            }
        });

        btn_full_box = (ImageView) findViewById(R.id.btn_full_box);
        btn_full_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDonation.this, FullBoxActivity.class);
                startActivity(intent);
            }
        });


    }

    private void openDialogue() {
        DonPanellActivity donPanellActivity = new DonPanellActivity();
        donPanellActivity.show(getSupportFragmentManager(), "Story Dialog");
//        StoryDialogue storyDialogue = new StoryDialogue();
//        storyDialogue.show(getSupportFragmentManager(), "Story Dialog");
    }
//    public class AddDonation extends AppCompatDialogFragment {
//
//        Button btn_add_donations;
//        ImageView btn_full_box;
//
//
//        @NonNull
//        @Override
//        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//            LayoutInflater inflater = getActivity().getLayoutInflater();
//            View view = inflater.inflate(R.layout.activity_don_panell, null);
//
//
//            builder.setView(view)
//                    .setTitle("Create Donation Story")
//                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//
//                        }
//                    });
//            btn_add_donations = view.findViewById(R.id.addDonBtn);
//            btn_add_donations.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                    Intent intent = new Intent(AddDonation.this, DonPanellActivity.class);
////                    startActivity(intent);
//                }
//            });
//
//            return builder.create();
//        }
//
//
//
//    }

}

