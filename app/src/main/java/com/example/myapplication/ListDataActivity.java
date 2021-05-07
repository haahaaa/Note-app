package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {
    private ListView listView;
    DatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        listView = findViewById(R.id.LV);
        DB = new DatabaseHelper(this);

        ArrayList<String> list = new ArrayList<>();
        Cursor data = DB.getList();
        while (data.moveToNext()) {
            list.add(data.getString(1));
        }
        ListAdapter listAdapter =  new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String note = parent.getItemAtPosition(position).toString();
                Cursor data = DB.getID(note);
                int itemID = -1;
                while (data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    Intent i = new Intent(ListDataActivity.this, editnoteActivity.class);
                    i.putExtra("id", itemID);
                    i.putExtra("note", note);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(ListDataActivity.this,"No ID in list",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}