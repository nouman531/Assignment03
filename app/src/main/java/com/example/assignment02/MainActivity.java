package com.example.assignment02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText editText;
    Button searchBtn, addBtn;

    DBHandler dbHandler;

    private ArrayList<Model> dataholder;
    MyAdapterClass adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleview);
        editText = findViewById(R.id.search);
        searchBtn = findViewById(R.id.button);
        addBtn = findViewById(R.id.addstd);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddStudent.class);
                startActivity(intent);
            }
        });

        dbHandler = new DBHandler(MainActivity.this);

        dataholder = new ArrayList<Model>(); // Initialize the dataholder ArrayList

        StoreDataInArrays();
        adapter = new MyAdapterClass(MainActivity.this,dataholder);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    void StoreDataInArrays() {
        Cursor cursor = dbHandler.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                Model obj = new Model(cursor.getString(1), cursor.getString(2), cursor.getString(3));
                dataholder.add(obj);
            }
        }
    }
}

