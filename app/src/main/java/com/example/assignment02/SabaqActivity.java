package com.example.assignment02;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SabaqActivity extends AppCompatActivity {
    Context context;

    private DBHandler dbHandler;
    private TextView rollnum;
    private TextView sabaq;
    private TextView sabaqi;
    private TextView manzil;
    private TextView stdclass;
    private TextView name;

    private Button viewRecordsbtn,updateBtn;


    private String stdRollnumber,stdSabaqValue,stdSabaqiValue,stdManzilValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sabaq);

        // Initialize views
        rollnum = findViewById(R.id.rollnumber);
        sabaq = findViewById(R.id.sabaq);
        sabaqi = findViewById(R.id.sabaqi);
        manzil = findViewById(R.id.manzil);
        stdclass = findViewById(R.id.cls);
        name = findViewById(R.id.name);


        viewRecordsbtn = findViewById(R.id.viewRecordsbtn);
        updateBtn = findViewById(R.id.updatebtn);

        // Create a DBHandler instance
        dbHandler = new DBHandler(this);

        // Get the roll number from the intent
        Intent intent = getIntent();
        String rollNum = intent.getStringExtra("rollnum");
        String namestd = intent.getStringExtra("name");
        String classStd = intent.getStringExtra("stdclass");

        name.setText("Name : " + namestd);
        stdclass.setText("Class : " + classStd);

        // Query the database to retrieve data for the specified roll number
        Cursor cursor = dbHandler.getDataByRollNum(rollNum);

        if (cursor != null && cursor.moveToFirst()) {
            // Retrieve the data from the cursor
            @SuppressLint("Range") String stdrollNum = cursor.getString(cursor.getColumnIndex("rollno_fk"));
            @SuppressLint("Range") String stdSabaq = cursor.getString(cursor.getColumnIndex("sabaq"));
            @SuppressLint("Range") String stdSabaqi = cursor.getString(cursor.getColumnIndex("sabaqi"));
            @SuppressLint("Range") String stdManzil = cursor.getString(cursor.getColumnIndex("manzil"));

            // Set the retrieved data in the TextViews
            rollnum.setText("Roll Number : " + stdrollNum);
            sabaq.setText("Sabaq : " + stdSabaq);
            sabaqi.setText("Sabaqi : " + stdSabaqi);
            manzil.setText("Manzil : " + stdManzil);


            stdRollnumber=stdrollNum;
            stdSabaqValue = stdSabaq;
            stdSabaqiValue = stdSabaqi;
            stdManzilValue = stdManzil;

            cursor.close();
        } else {
            // No data found for the specified roll number
            Toast.makeText(this, "No data found for Roll Number: " + rollNum, Toast.LENGTH_SHORT).show();
        }



        viewRecordsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(SabaqActivity.this,updateActivity.class);
                intent.putExtra("rollnum",stdRollnumber);
                startActivity(intent);

            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(SabaqActivity.this,updateActivity.class);
                intent.putExtra("rollnum",stdRollnumber);
                startActivity(intent);
            }
        });
    }
}
