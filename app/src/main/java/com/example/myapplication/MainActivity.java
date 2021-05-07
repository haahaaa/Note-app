package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button viewBtn,createBtn;
    private EditText editText;
    DatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createBtn = findViewById(R.id.createNote);
        viewBtn = findViewById(R.id.viewBtn);
        DB = new DatabaseHelper(this);


        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = DB.getList();
                if (data.getCount() <= 0){
                    Toast.makeText(MainActivity.this,"No note in list, please create one!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i = new Intent(MainActivity.this,ListDataActivity.class);
                    startActivity(i);
                }
            }
        });
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,addNote.class);
                startActivity(i);
            }
        });
    }
}