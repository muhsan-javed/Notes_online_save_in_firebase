package com.muhsantech.notesonlinesaveinfirebase.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.muhsantech.notesonlinesaveinfirebase.MainActivity;
import com.muhsantech.notesonlinesaveinfirebase.R;
import com.muhsantech.notesonlinesaveinfirebase.databinding.ActivityForgotPasswordBinding;
import com.muhsantech.notesonlinesaveinfirebase.databinding.ActivityMainBinding;

public class forgotPassword extends AppCompatActivity {

    ActivityForgotPasswordBinding binding;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //getSupportActionBar().hide();
        // Ed - forgotPassword
        // Button passwordRecoverButton
        // TextView goBackToLogin
        firebaseAuth = FirebaseAuth.getInstance();

        binding.goBackToLogin.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        binding.passwordRecoverButton.setOnClickListener(view -> {
            String mail = binding.forgotPassword.getText().toString().trim();
            if (mail.isEmpty()){
                Toast.makeText(this, "Enter your mail first", Toast.LENGTH_SHORT).show();
            }else {
                // we here to send password recover email
                
                firebaseAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        
                        if (task.isSuccessful()){
                            Toast.makeText(forgotPassword.this, "Mail Sent, You can recover your password using mail'", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(forgotPassword.this, MainActivity.class));
                        }else {
                            Toast.makeText(forgotPassword.this, "Email is Wrong or Account Not Exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                
            }
        });

    }
}