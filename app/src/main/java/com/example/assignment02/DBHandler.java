package com.example.assignment02;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DBHandler extends SQLiteOpenHelper {

    private final Context context;
    private static final String DBName="Mydb";
    private static final int DBVersion=1;
    private static final String Table_Name="My_Table";

    private static final String RollNum="std_roll";
    private static final String Name="std_name";
    private static final String Age="std_age";
    private static final String StdClass="std_class";




    private static final String Table_Name_Tasks="My_Table_task";
    private static final String RollNumFK = "rollno_fk";
    private static final String Sabaq = "sabaq";
    private static final String Sabaqi = "sabaqi";
    private static final String Manzil = "manzil";

    ///////////////////For storing data
    private static final String History_Table="Std_History";
    private static final String KEY_ID = "id";
    private static final String KEY_ROLL_NUM = "roll_number";
    private static final String KEY_SABAQ = "sabaq";
    private static final String KEY_SABAQI = "sabaqi";
    private static final String KEY_MANZIL = "manzil";
    private static final String KEY_TIMESTAMP = "timestamp";




    public DBHandler(@Nullable Context context) {
        super(context, DBName, null, DBVersion);
        this.context = context;
    }




    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        String query="CREATE TABLE "+ Table_Name +
                "(" + RollNum + " TEXT PRIMARY KEY, "+
                Name + " TEXT, " +
                Age + " TEXT, " +
                StdClass + " TEXT);";
        db.execSQL(query);

        String tasksTableQuery="CREATE TABLE "+ Table_Name_Tasks +
                "(" + RollNumFK + " TEXT PRIMARY KEY, "+
                Sabaq + " TEXT, " +
                Sabaqi + " TEXT, " +
                Manzil + " TEXT);";
        db.execSQL(tasksTableQuery);

        String createHistoryTableQuery = "CREATE TABLE " + History_Table + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_ROLL_NUM + " TEXT,"
                + KEY_SABAQ + " TEXT,"
                + KEY_SABAQI + " TEXT,"
                + KEY_MANZIL + " TEXT,"
                + KEY_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
        db.execSQL(createHistoryTableQuery);


    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int i, int i1) {
        String query1="DROP TABLE IF EXISTS "+ Table_Name;
        String query2="DROP TABLE IF EXISTS "+ Table_Name_Tasks;
        String query3="DROP TABLE IF EXISTS "+ History_Table;
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        onCreate(db);

    }

    public void AddStudents(String rollnum, String name, String age, String cls) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(RollNum, rollnum);
        cv.put(Name, name);
        cv.put(Age, age);
        cv.put(StdClass, cls);

        long result = db.insert(Table_Name, null, cv);

        if (result == -1) {
            if (context != null) {
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (context != null) {
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
            }
        }
        db.close();
    }


    public void AddTask(String rollNum,String sabaq,String sabaqi,String manzil){
        SQLiteDatabase db= this.getWritableDatabase();//so we can add the values in the data
        ContentValues cv=new ContentValues();
        cv.put(RollNumFK,rollNum);
        cv.put(Sabaq,sabaq);
        cv.put(Sabaqi,sabaqi);
        cv.put(Manzil,manzil);

        long task_result=db.insert(Table_Name_Tasks,null,cv);

        if(task_result==-1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show();
        }
        db.close();

    }




    public Cursor readAllData(){
        String query="SELECT * FROM " + Table_Name;
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor = null;
        if(db!=null) cursor = db.rawQuery(query, null);
        return cursor;
    }


    public Cursor getDataByRollNum(String rollNum) {
        String query = "SELECT * FROM " + Table_Name_Tasks + " WHERE " + RollNumFK + " = '" + rollNum + "'";

        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor = null;
        if(db!=null) cursor = db.rawQuery(query, null);
        return cursor;

    }

    public void updateDataAndSaveHistorical(String rollNum, String newsabaq,String newSabaqi, String newManzil) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Update the data in the main table
        ContentValues values = new ContentValues();
        values.put(Sabaq,newsabaq);
        values.put(Sabaqi, newSabaqi);
        values.put(Manzil, newManzil);
        db.update(Table_Name_Tasks, values, RollNumFK + " = ?", new String[]{rollNum});

        // Save the previous data as a historical record
        ContentValues historicalValues = new ContentValues();
        historicalValues.put(KEY_ROLL_NUM, rollNum);
        historicalValues.put(KEY_SABAQ, newsabaq);
        historicalValues.put(KEY_SABAQI, newSabaqi);
        historicalValues.put(KEY_MANZIL, newManzil);
        historicalValues.put(KEY_TIMESTAMP, getCurrentTimestamp());
        db.insert(History_Table, null, historicalValues);

        db.close();
    }

    // Method to get the current timestamp
    private String getCurrentTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }


}






