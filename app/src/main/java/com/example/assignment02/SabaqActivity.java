package com.example.assignment02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SabaqActivity extends AppCompatActivity {

    TextView name,cls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sabaq);

        name=findViewById(R.id.name);
        cls=findViewById(R.id.cls);

        Intent intent=getIntent();
        String std_Name=intent.getStringExtra("name");
        String std_class=intent.getStringExtra("class");

        name.setText("Name : "+std_Name);
        cls.setText("Class : "+std_class);


    }
}