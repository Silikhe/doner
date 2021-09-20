package com.example.donner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private static final int PASSWORD_LENGTH = 6;
    EditText mUsername, mPassword;
    Button mLogBtn;
    private ProgressDialog progressBar;

    private String pass, email;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        viewInitializations();
        mLogBtn = (Button) findViewById(R.id.btn_login);
        mLogBtn.setOnClickListener((view) -> {
            userLogin();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            Intent intent = new Intent(LoginActivity.this, DonationActivity.class);
            startActivity(intent);
            this.finish();
        }
    }

    private void userLogin() {
        mUsername = findViewById(R.id.et_email);
        mPassword = findViewById(R.id.et_password);

        email = mUsername.getText().toString();
        pass = mPassword.getText().toString();

        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mAuth.signInWithEmailAndPassword(email, pass)
//            mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
//                            checkIfEmailVerified();
                            Toast.makeText(LoginActivity.this, "Logged in Successfully!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this, DonationActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LoginActivity.this, "Logged in Failed!", Toast.LENGTH_LONG).show();
                }
            });


        } else {
            mUsername.setError("Please enter Correct Email");
        }
    }

    boolean validateInput() {

//        email = mUsername.getText().toString();
//        pass = mPassword.getText().toString();

        if ((email.isEmpty()) || (pass.isEmpty())) {
//            mUsername.setError("Empty inputs not allowed!");
            Toast.makeText(LoginActivity.this, "Empty inputs not allowed!", Toast.LENGTH_LONG).show();
            return false;
        } else if (pass.length() < PASSWORD_LENGTH) {
            mPassword.setError("Enter Valid password");
            return false;
        }
        return true;
    }


    public void signUp(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    public void viewTopics(View view) {
//        if (validateInput()) {
//            Intent topics = new Intent(LoginActivity.this, DonationActivity.class);
//            topics.putExtra("username", email);
//            startActivity(topics);
//        }
    }

    public void checkIfEmailVerified() {
        progressBar = new ProgressDialog(this);
        progressBar.setTitle("Verifying...");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user.isEmailVerified()) {
            /**
             * If the user is verified, finish this activity and send them to the
             * MainActivity and show a success toast message.
             */
            Toast.makeText(LoginActivity.this, "Succefully Varified!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
            finish();
        } else {
            /**
             * If the email is not verified, prompt a message to the user using
             * the `failEmailVerified` dialog and make sure the user is still
             * signed out.
             */
            progressBar.dismiss();
//            DialogChooser.failEmailVerified(context);
            FirebaseAuth.getInstance().signOut();
        }
    }

}