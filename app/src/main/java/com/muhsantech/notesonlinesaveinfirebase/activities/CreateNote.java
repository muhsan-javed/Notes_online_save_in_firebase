package com.muhsantech.notesonlinesaveinfirebase.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.muhsantech.notesonlinesaveinfirebase.databinding.ActivityCreateNoteBinding;

import java.util.HashMap;
import java.util.Map;

public class CreateNote extends AppCompatActivity {

    ActivityCreateNoteBinding binding;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //saveNoteFloatButton
        // EditText = createTitleOfNote, createNotesContent
        // Toolbar toolBarOfCreateNote

        setSupportActionBar(binding.toolBarOfCreateNote);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseUser = firebaseAuth.getInstance().getCurrentUser();

        binding.saveNoteFloatButton.setOnClickListener(view -> {

            String title = binding.createTitleOfNote.getText().toString();
            String content = binding.createNotesContent.getText().toString();

            if (title.isEmpty() || content.isEmpty()){
                Toast.makeText(this, "Both field are Require", Toast.LENGTH_SHORT).show();
            }else {

                DocumentReference documentReference = firebaseFirestore.collection("notes")
                        .document(firebaseUser.getUid())
                        .collection("myNotes")
                        .document();

                Map<String, Object> note = new HashMap<>();
                note.put("title", title);
                note.put("content",content);

                documentReference.set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(CreateNote.this, "Note Create Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(CreateNote.this, NotesActivity.class));
                        
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CreateNote.this, "Failed To Create Note", Toast.LENGTH_SHORT).show();
                    }
                });


            }

        });




    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}