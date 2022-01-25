package com.muhsantech.notesonlinesaveinfirebase.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.muhsantech.notesonlinesaveinfirebase.MainActivity;
import com.muhsantech.notesonlinesaveinfirebase.R;
import com.muhsantech.notesonlinesaveinfirebase.databinding.ActivitySignUpBinding;

public class SignUp extends AppCompatActivity {

    ActivitySignUpBinding binding;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Ed - signUpEmail, signUpPassword
        // ReLButton SignUpBtn, sign_Up
        // TextView gotoLogin
        //getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();

        binding.gotoLogin.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        binding.signUp.setOnClickListener(view -> {
            //
            String mail = binding.signUpEmail.getText().toString().trim();
            String password = binding.signUpPassword.getText().toString().trim();
            
            if (mail.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "All Fields are Required", Toast.LENGTH_SHORT).show();
            }
            else if (password.length() < 7){
                Toast.makeText(this, "Password Should Greater than 7 Digits", Toast.LENGTH_SHORT).show();
            }
            else {
                // Registered the user to firebase

                firebaseAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()){
                                    Toast.makeText(SignUp.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    sendEmailVerification();
                                }
                                else {
                                    Toast.makeText(SignUp.this, "Failed To Register", Toast.LENGTH_SHORT).show();

                                }

                            }
                        });

            }
        });

    }

    // Send email verification
    private void sendEmailVerification(){

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser !=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    Toast.makeText(SignUp.this, "Verification Email is Sent Verify and Log In Again", Toast.LENGTH_SHORT).show();
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(SignUp.this, MainActivity.class));
                }
            });
        }else {
            Toast.makeText(SignUp.this, "Failed To Send Verification Email ", Toast.LENGTH_SHORT).show();

        }
    }
}