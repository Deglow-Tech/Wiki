package com.example.ejemplonavegacion.DataBase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ejemplonavegacion.Entities.Contact;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ContactDAO {

    private static final String CONTACT_TABLE = "CONTACT";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_PHONE = "Phone";
    private static final String COLUMN_PRIORITY = "Priority";
    private static final String COLUMN_ID = "ID";

    public static final String CREATE_CONTACT_TABLE = "CREATE TABLE " + CONTACT_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_PHONE + " TEXT, " + COLUMN_PRIORITY + " TEXT)";

    public static boolean addContact(DataBaseHelper db, Contact contact){
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, contact.getName());
        cv.put(COLUMN_PHONE, contact.getPhone());
        cv.put(COLUMN_PRIORITY, contact.getPriority());

        long insert = database.insert(CONTACT_TABLE, null, cv);
        if (insert == -1){
            Toast.makeText(db.getContext(), "Error al registrar el contacto", Toast.LENGTH_SHORT).show();
            database.close();
            return false;
        }
        Toast.makeText(db.getContext(), "Contacto registrado correctamente", Toast.LENGTH_SHORT).show();
        database.close();
        return true;
    }

    public static List<Contact> getContacts(DataBaseHelper db){
        List<Contact> contactList = new ArrayList<>();
        String query = "SELECT * FROM " + CONTACT_TABLE;
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do{
                int contactID = cursor.getInt(0);
                String contactName = cursor.getString(1);
                String contactPhone = cursor.getString(2);
                String contactPriority = cursor.getString(3);
                Contact contact = new Contact(contactName, contactPhone, contactPriority);
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return  contactList;
    }
}
