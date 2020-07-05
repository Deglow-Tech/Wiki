package com.example.ejemplonavegacion.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private Context context;

    public DataBaseHelper(@Nullable Context context) {
        super(context, "contactDB", null, 1);
        this.context = context;
    }

    //This is called the first time a database is accessed
    //There should be coin in here to create the data base
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContactDAO.CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Context getContext(){
        return context;
    }
}
