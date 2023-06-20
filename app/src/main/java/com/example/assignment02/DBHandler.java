package com.example.assignment02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;




public class DBHandler extends SQLiteOpenHelper {

    private final Context context;
    private static final String DBName="Mydb";
    private static final int DBVersion=1;
    private static final String Table_Name="My_Table";

    //private static final String ID="id";
    private static final String Name="std_name";
    private static final String Age="std_age";
    private static final String StdClass="std_class";


    public DBHandler(@Nullable Context context) {
        super(context, DBName, null, DBVersion);
        this.context = context;
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        String query="CREATE TABLE "+ Table_Name +
                "(" + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Name + " TEXT, " +
                Age + " TEXT, " +
                StdClass + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int i, int i1) {
        String query="DROP TABLE IF EXISTS "+ Table_Name;
        db.execSQL(query);
        onCreate(db);

    }

    public void AddStudents(String name,String age,String cls){
        SQLiteDatabase db= this.getWritableDatabase();//so we can add the values in the data
        ContentValues cv=new ContentValues();
        cv.put(Name,name);
        cv.put(Age,age);
        cv.put(StdClass,cls);

        long result=db.insert(Table_Name,null,cv);

        if(result==-1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show();
        }
        db.close();

    }
    Cursor readAllData(){
        String query="SELECT * FROM " + Table_Name;
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=null;
        if(db!=null){
            cursor=db.rawQuery(query,null);
        }
        return cursor;
    }
}
