package com.example.assignment02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class updateActivity extends AppCompatActivity {

    EditText from,to;
    DBHandler db;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        from=findViewById(R.id.from);
        to=findViewById(R.id.to);

        db= new DBHandler(this);

        btn=findViewById(R.id.update);

        Intent intent=getIntent();
        String rollnumer=intent.getStringExtra("rollnum");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String fromayat= from.getText().toString();
                String toayat= to.getText().toString();
                String sabaqi="new test sabaqi";
                String manzil="new test manzil";
                String sabaq= " "+fromayat + " to "+toayat +" ";
                db.updateDataAndSaveHistorical(rollnumer,sabaq,sabaqi,manzil);
                Toast.makeText(updateActivity.this,"Data is updated",Toast.LENGTH_SHORT).show();
            }
        });
    }
}