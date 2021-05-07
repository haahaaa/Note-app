package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addNote extends AppCompatActivity {
    private Button addBtn;
    private EditText editText;
    DatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        addBtn = findViewById(R.id.addBtn);
        editText = findViewById(R.id.editText);
        DB = new DatabaseHelper(this);


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                if(editText.length() != 0){
                    AddData(newEntry);
                    editText.setText("");
                    Intent i = new Intent(addNote.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(addNote.this,"You must put something in the text field !", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
    public void AddData(String newEntry){
        boolean insertData = DB.addData(newEntry);
        if(insertData == true) {
            Toast.makeText(addNote.this, "Successfully Added!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(addNote.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }
    }
}