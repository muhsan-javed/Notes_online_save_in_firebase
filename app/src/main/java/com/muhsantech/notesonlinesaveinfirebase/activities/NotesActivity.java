package com.muhsantech.notesonlinesaveinfirebase.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.muhsantech.notesonlinesaveinfirebase.MainActivity;
import com.muhsantech.notesonlinesaveinfirebase.R;
import com.muhsantech.notesonlinesaveinfirebase.databinding.ActivityNotesBinding;

public class NotesActivity extends AppCompatActivity {

    ActivityNotesBinding binding;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        // FloatButton createNoteFloatButton
        // RecyclerView =  recyclerView
        //getSupportActionBar().setTitle("All Notes");
        binding.createNoteFloatButton.setOnClickListener(view -> {
            startActivity(new Intent(NotesActivity.this, CreateNote.class));
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.logout:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(NotesActivity.this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}