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
    Button searchbtn,addbtn;

    DBHandler dbhdr=null;

    private ArrayList<Model> dataholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycleview);
        editText=findViewById(R.id.search);
        searchbtn=findViewById(R.id.button);
        addbtn=findViewById(R.id.addstd);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddStudent.class);
                startActivity(intent);

            }
        });


        StoreDataInArrays();

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    void StoreDataInArrays(){
        Cursor cursor=dbhdr.readAllData();
        if(cursor.getCount()==0){
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                Model obj= new Model(cursor.getString(1),cursor.getString(2),cursor.getString(3));
                dataholder.add(obj);

            }
        }
    }
}