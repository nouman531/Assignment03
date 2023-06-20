package com.example.assignment02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTasks extends AppCompatActivity {
    EditText rollNum,sabaq,sabaqi,manzil;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasks);

        rollNum=findViewById(R.id.std_roll);
        sabaq=findViewById(R.id.std_sabaq);
        sabaqi=findViewById(R.id.std_sabaqi);
        manzil=findViewById(R.id.std_manzil);

        button=findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                    DBHandler db=new DBHandler(AddTasks.this);
                    db.AddTask(rollNum.getText().toString(),
                            sabaq.getText().toString(),
                            sabaqi.getText().toString(),
                            manzil.getText().toString());
                    rollNum.setText("");
                    sabaq.setText("");
                    sabaqi.setText("");
                    manzil.setText("");


                }catch(Exception ex){
                    ex.toString();
                }
            }

        });
    }
}