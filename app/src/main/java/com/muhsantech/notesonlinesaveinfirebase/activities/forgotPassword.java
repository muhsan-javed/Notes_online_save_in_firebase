package com.muhsantech.notesonlinesaveinfirebase.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.muhsantech.notesonlinesaveinfirebase.MainActivity;
import com.muhsantech.notesonlinesaveinfirebase.R;
import com.muhsantech.notesonlinesaveinfirebase.databinding.ActivityForgotPasswordBinding;
import com.muhsantech.notesonlinesaveinfirebase.databinding.ActivityMainBinding;

public class forgotPassword extends AppCompatActivity {

    ActivityForgotPasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        // Ed - forgotPassword
        // Button passwordRecoverButton
        // TextView goBackToLogin

        binding.goBackToLogin.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        binding.passwordRecoverButton.setOnClickListener(view -> {
            String mail = binding.forgotPassword.getText().toString().trim();
            if (mail.isEmpty()){
                Toast.makeText(this, "Enter your mail first", Toast.LENGTH_SHORT).show();
            }else {
                // we here to send
            }
        });

    }
}