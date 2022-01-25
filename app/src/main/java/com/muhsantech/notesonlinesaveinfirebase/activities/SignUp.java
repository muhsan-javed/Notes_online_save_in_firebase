package com.muhsantech.notesonlinesaveinfirebase.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.muhsantech.notesonlinesaveinfirebase.MainActivity;
import com.muhsantech.notesonlinesaveinfirebase.R;
import com.muhsantech.notesonlinesaveinfirebase.databinding.ActivitySignUpBinding;

public class SignUp extends AppCompatActivity {

    ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Ed - signUpEmail, signUpPassword
        // ReLButton SignUpBtn, sign_Up
        // TextView gotoLogin

        getSupportActionBar().hide();

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
            }
        });

    }
}