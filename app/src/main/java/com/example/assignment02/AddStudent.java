package com.example.assignment02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddStudent extends AppCompatActivity {

    EditText rollnum,name,age,cls;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        name=findViewById(R.id.std_name);
        age=findViewById(R.id.std_age);
        cls=findViewById(R.id.std_class);
        rollnum=findViewById(R.id.rollNum);

        btn=findViewById(R.id.button);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                    DBHandler db=new DBHandler(AddStudent.this);
                    db.AddStudents(rollnum.getText().toString(),
                            name.getText().toString(),
                            age.getText().toString(),
                            cls.getText().toString());
                    rollnum.setText("");
                    name.setText("");
                    age.setText("");
                    cls.setText("");


                }catch(Exception ex){
                    ex.toString();
                }
            }

        });


    }
}