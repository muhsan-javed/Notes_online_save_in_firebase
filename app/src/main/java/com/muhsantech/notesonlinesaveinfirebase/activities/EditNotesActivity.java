package com.muhsantech.notesonlinesaveinfirebase.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.muhsantech.notesonlinesaveinfirebase.R;
import com.muhsantech.notesonlinesaveinfirebase.databinding.ActivityEditNotesBinding;

import java.util.HashMap;
import java.util.Map;

public class EditNotesActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    FirebaseUser firebaseUser;
    Intent data;
    ActivityEditNotesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolBarOfNote);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        data = getIntent();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String noteTitle = data.getStringExtra("title");
        String noteContent = data.getStringExtra("content");
        binding.UpdateTitleOfNote.setText(noteTitle);
        binding.editNotesContent.setText(noteContent);

        binding.updateNoteFloatButton.setOnClickListener(view -> {
            Toast.makeText(this, "Note is Update", Toast.LENGTH_SHORT).show();

            String newTitle = binding.UpdateTitleOfNote.getText().toString();
            String newContent = binding.editNotesContent.getText().toString();

            if (newTitle.isEmpty() || newContent.isEmpty()){
                Toast.makeText(this, "SomeThing is empty", Toast.LENGTH_SHORT).show();
            }
            else {
                DocumentReference documentReference = firebaseFirestore.collection("notes")
                        .document(firebaseUser.getUid())
                        .collection("myNotes")
                        .document(data.getStringExtra("noteId"));

                Map<String,Object> note = new HashMap<>();
                note.put("title", newTitle);
                note.put("content", newContent);
                documentReference.set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EditNotesActivity.this, "Note is updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditNotesActivity.this, NotesActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditNotesActivity.this, "Failed To Update", Toast.LENGTH_SHORT).show();
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
    }}