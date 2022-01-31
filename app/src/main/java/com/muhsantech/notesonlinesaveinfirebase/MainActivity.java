package com.muhsantech.notesonlinesaveinfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.muhsantech.notesonlinesaveinfirebase.activities.NotesActivity;
import com.muhsantech.notesonlinesaveinfirebase.activities.SignUp;
import com.muhsantech.notesonlinesaveinfirebase.activities.forgotPassword;
import com.muhsantech.notesonlinesaveinfirebase.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //getSupportActionBar().hide();
        // EditTExt - loginPassword, loginEmail
        // RelativeLayout gotoSignUp, login
        // TextView  forgotPassword
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            finish();
            startActivity(new Intent(MainActivity.this, NotesActivity.class));
        }

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
                // Login the user to firebase
                binding.progressbarOfMainActivity.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(mailLogin, passwordLogin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            checkMailVerification();
                        }else {
                            Toast.makeText(MainActivity.this, "Account Doesn't Exist", Toast.LENGTH_SHORT).show();
                            binding.progressbarOfMainActivity.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });
        
    }
    
    private void checkMailVerification(){
        
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        
        if (firebaseUser.isEmailVerified() == true){
            Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(MainActivity.this, NotesActivity.class));
        }
        else {
            binding.progressbarOfMainActivity.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Verify your mail first", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
}