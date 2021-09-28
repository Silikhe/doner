package com.example.donner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

public class DonPanellActivity extends AppCompatDialogFragment {
    CardView btn, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
        public ArrayList<String> donations;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_don_panell, null);


        builder.setView(view)
                .setTitle("Choose Item to donate")
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

//        Intent intent = new Intent(DonPanelActivity.this, FullBoxActivity.class);
//        intent.putExtra("FILES_TO_SEND", donations);

        btn = view.findViewById(R.id.btn_shirt);
        btn1 = view.findViewById(R.id.btn_dress);
        btn2 = view.findViewById(R.id.btn_shoes);
        btn3 = view.findViewById(R.id.btn_jeans);
        btn4 = view.findViewById(R.id.btn_sweater);
        btn5 = view.findViewById(R.id.btn_tshirt);
        btn6 = view.findViewById(R.id.btn_pants);
        btn7 = view.findViewById(R.id.btn_access);
//        btn8 = view.findViewById(R.id.btn_suit);
        btn9 = view.findViewById(R.id.btn9);
//
        donations = new ArrayList<String>();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donations.add("Shirt");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donations.add("Dress");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donations.add("Shoes");
            }
        });


        btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                donations.add("Pants");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                donations.add("Sweater");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donations.add("T-shirt");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donations.add("Jeans");
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donations.add("Accessories");
            }
        });

//        btn8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                donations.add("Suit");
//            }
//        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        return builder.create();
    }

}

