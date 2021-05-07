package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class editnoteActivity extends AppCompatActivity {
    private Button updateBtn,deleteBtn;
    private EditText changeNote;
    DatabaseHelper DB;
    private String selectednote;
    private int selectedID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editnote);

        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        changeNote = findViewById(R.id.changeNote);
        DB = new DatabaseHelper(this);

        Intent i = getIntent();
        selectedID = i.getIntExtra("id", -1);
        selectednote = i.getStringExtra("note");

        changeNote.setText(selectednote);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String note = changeNote.getText().toString();
                if(!note.equals("")){
                    DB.updateNote(note,selectedID,selectednote);
                    Toast.makeText(editnoteActivity.this,"Successfully updated note!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(editnoteActivity.this, ListDataActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(editnoteActivity.this,"You didn't make any change!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = DB.getList();
                DB.deleteNote(selectedID,selectednote);
                changeNote.setText("");
                Toast.makeText(editnoteActivity.this,"Removed note from database", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(editnoteActivity.this, ListDataActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
}