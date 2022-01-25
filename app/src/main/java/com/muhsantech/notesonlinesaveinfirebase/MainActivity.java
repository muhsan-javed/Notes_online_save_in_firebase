package com.muhsantech.notesonlinesaveinfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.muhsantech.notesonlinesaveinfirebase.activities.SignUp;
import com.muhsantech.notesonlinesaveinfirebase.activities.forgotPassword;
import com.muhsantech.notesonlinesaveinfirebase.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        // EditTExt - loginPassword, loginEmail
        // RelativeLayout gotoSignUp, login
        // TextView  forgotPassword

        binding.forgotPassword.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, forgotPassword.class));
        });

        binding.signUp.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, SignUp.class));
        });

        binding.login.setOnClickListener(view -> {

            String mailLogin = binding.loginEmail.getText().toString().trim();
            String passwordLogin = binding.loginPassword.getText().toString().trim();

            if (mailLogin.isEmpty() || passwordLogin.isEmpty()){
                Toast.makeText(this, "All Fields are Required", Toast.LENGTH_SHORT).show();
            }
            else {
                // Registered the user to firebase
            }
        });
    }
}