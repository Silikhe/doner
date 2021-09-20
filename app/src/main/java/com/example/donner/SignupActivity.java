package com.example.donner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.core.Tag;

public class SignupActivity extends AppCompatActivity {
    private static final int PASSWORD_LENGTH = 6;
    private static final String TAG = "Name";
    private FirebaseAuth mAuth;

    EditText mEmail, mPassword, mConfirmPassword;
    Button btn;
    private String pass, conPass, email;
    protected Context context;
//    protected ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btn = (Button) findViewById(R.id.btn_sign);
        btn.setOnClickListener((view) -> {
//            if (validateInput())
                createUser();
        });
    }

    private void createUser() {
//        progressBar.setVisibility(View.VISIBLE);
        mEmail = findViewById(R.id.et_email);
        mPassword = findViewById(R.id.et_password);

        pass = mPassword.getText().toString();
        email = mEmail.getText().toString();


        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
//                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(SignupActivity.this, " Signed Up Successfully!", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                    finish();
//                                    Dialog.OnF
//                                    DialogChooser.failCreateUser(context);
//                                    sendVerificationEmail();
                                } else {
                                    Toast.makeText(SignupActivity.this, " Registration Failed.!", Toast.LENGTH_LONG).show();
                                }

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignupActivity.this, " Registration Failed.!", Toast.LENGTH_LONG).show();
                    }
                });
        } else {
//            mEmail.setError("Please enter Correct Email or Password");
            Toast.makeText(SignupActivity.this, " Please enter Correct Email or Password!", Toast.LENGTH_LONG).show();
        }
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    private boolean validateInput() {
        mConfirmPassword = findViewById(R.id.et_confirm_password);
        conPass = mConfirmPassword.getText().toString();

        if ((email.isEmpty()) || (pass.isEmpty()) || (conPass.isEmpty())) {
            Log.d(TAG, "validateInput: pass");
            Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show();
//            mEmail.setError("Empty fields are not allowed");
            return false;
        } else if (!pass.equals(conPass)) {
            mConfirmPassword.setError("Password do not match");
            Toast.makeText(this, "Password do not match", Toast.LENGTH_SHORT).show();
            return false;
        } else if (pass.length() < PASSWORD_LENGTH) {
//            mPassword.setError("Enter Valid password");
            Toast.makeText(this, "Enter a valid password!\"", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void sendVerificationEmail() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            /**
                             * FireBase failed to send a verification email so we will show a
                             * `Failed Send Verification Dialog` to the user, sign out the user
                             * and override the pent.
                             */
                            overridePendingTransition(0, 0);
                            FirebaseAuth.getInstance().signOut();
                            startActivity(getIntent());
                            Toast.makeText(SignupActivity.this, "Verification Failed!", Toast.LENGTH_SHORT).show();
//                            DialogChooser.failSendVerification(context);
                        } else {
                            /**
                             * FireBase successfully sent a verification email so now we show a
                             * `Thank-you Dialog` to the user that allows them to check their email
                             * for the verification.
                             */
//                            DialogChooser.agreementAccepted(context);
                            Toast.makeText(SignupActivity.this, "Verified Successfully!", Toast.LENGTH_SHORT).show();
                            FirebaseAuth.getInstance().signOut();
                        }
                    }
                });
    }


}