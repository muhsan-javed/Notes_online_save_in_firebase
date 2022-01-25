package com.muhsantech.notesonlinesaveinfirebase.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.muhsantech.notesonlinesaveinfirebase.databinding.ActivityNotesBinding;

public class NotesActivity extends AppCompatActivity {

    ActivityNotesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}