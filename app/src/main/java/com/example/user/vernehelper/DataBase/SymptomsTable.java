package com.example.user.vernehelper.DataBase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 17.07.2017.
 */

public class SymptomsTable {
    public static final String SYMPTOMS_TABLE_NAME = "symptoms_table";
    public static final String COLUMN_SYMPTOM_ID = "symptom_id";
    public static final String COLUMN_DISEASE_ID = "disease_id";
    public static final String COLUMN_DISEASE_NAME = "disease_name";

    public static String createTable(){
        return "CREATE TABLE " + SYMPTOMS_TABLE_NAME + "(" +
                COLUMN_SYMPTOM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DISEASE_ID + " INTEGER, " +
                COLUMN_DISEASE_NAME + " TEXT)";
    }

    public void insert(SQLiteDatabase db, String symptom, int diseaseID){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DISEASE_ID, diseaseID);
        cv.put(COLUMN_DISEASE_NAME, symptom);
        diseaseID = (int) db.insert(SYMPTOMS_TABLE_NAME, null, cv);
    }

    public List<String> getSymptomsById(SQLiteDatabase db, int diseaseID){
        String selectQuerry = "SELECT "
                + COLUMN_SYMPTOM_ID + ", "
                + COLUMN_DISEASE_ID + ", "
                + COLUMN_DISEASE_NAME + " "
                + "FROM " + SYMPTOMS_TABLE_NAME;

        Cursor cursor = db.rawQuery(selectQuerry, null);

        List<String> sympts = new ArrayList<>();
        if (cursor != null){
            if (cursor.moveToFirst()){
                String str = "";
                do {
                    for (String cn : cursor.getColumnNames()){
                        str = str.concat(cn + " = " + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                    }
                    sympts.add(cursor.getString(cursor.getColumnIndex(COLUMN_DISEASE_NAME)));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return sympts;
    }
}
