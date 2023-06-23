package com.example.assignment02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment02.AddStudent;
import com.example.assignment02.AddTasks;
import com.example.assignment02.DBHandler;
import com.example.assignment02.Model;
import com.example.assignment02.MyAdapterClass;
import com.example.assignment02.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText editText;
    Button searchBtn, addBtn, addTaskButton;
    List<Model> filteredStudentList;

    DBHandler dbHandler;
    private Context context;

    private ArrayList<Model> dataholder;


    private MyAdapterClass adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        recyclerView = findViewById(R.id.recycleview);
        editText = findViewById(R.id.search);
        searchBtn = findViewById(R.id.button);
        addBtn = findViewById(R.id.addstd);
        addTaskButton = findViewById(R.id.addTask);

        //String query=editText.getText().toString();

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = editText.getText().toString();
                filterStudentList(query, dataholder);
            }
        });


        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTasks.class);
                startActivity(intent);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddStudent.class);
                startActivity(intent);
            }
        });

        dbHandler = new DBHandler(MainActivity.this);

        dataholder = new ArrayList<Model>();
        adapter = new MyAdapterClass(context, dataholder);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));



        loadData();
    }

    private void loadData() {
        Cursor cursor = dbHandler.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data yet", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String rollNum = cursor.getString(cursor.getColumnIndex("std_roll"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("std_name"));
                @SuppressLint("Range") String age = cursor.getString(cursor.getColumnIndex("std_age"));
                @SuppressLint("Range") String stdClass = cursor.getString(cursor.getColumnIndex("std_class"));

                Model model = new Model(rollNum, name, age, stdClass);
                dataholder.add(model);
            }
            adapter.notifyDataSetChanged();
        }
        cursor.close();
    }

    private void filterStudentList(String query, List<Model> studentList) {
        filteredStudentList = new ArrayList<>();

        if (query.isEmpty()) {
            filteredStudentList = (List<Model>) dbHandler.readAllData();
        } else {
            query = query.toLowerCase();
            for (Model student : studentList) {
                if (student.getName().toLowerCase().contains(query)) {
                    filteredStudentList.add(student);
                }
            }
            LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            adapter = new MyAdapterClass((Context) MainActivity.this, (ArrayList<Model>) filteredStudentList);
            recyclerView.setAdapter(adapter);
        }
        adapter.notifyDataSetChanged();
    }


}