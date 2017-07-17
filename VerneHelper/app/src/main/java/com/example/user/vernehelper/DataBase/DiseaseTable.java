package com.example.user.vernehelper.DataBase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.user.vernehelper.Disease;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 17.07.2017.
 */

public class DiseaseTable {

    public static final String DISEASE_TABLE_NAME = "diseaseTable";
    public static final String ID_COLUMN = "disease_id";
    public static final String NAME_COLUMN = "disease_name";
    public static final String DESC_COLUMN = "disease_description";

    public static String createTable(){
        return "CREATE TABLE " + DISEASE_TABLE_NAME + " (" +
                ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME_COLUMN + " TEXT, " +
                DESC_COLUMN + " TEXT)";
    }

    public void insert(SQLiteDatabase db, Disease disease){
        ContentValues cv = new ContentValues();
        cv.put(NAME_COLUMN, disease.getName());
        cv.put(DESC_COLUMN, disease.getDescription());
        int diseaseID = (int) db.insert(DISEASE_TABLE_NAME, null, cv);

        SymptomsTable symptomsTable = new SymptomsTable();
        for (String symptom: disease.getSymptoms()){
            symptomsTable.insert(db, symptom, diseaseID);
        }
    }

    public List<Disease> getAllDiseasesFromDB(SQLiteDatabase db){
        String selectQuerry = "SELECT "
                + ID_COLUMN + ", "
                + NAME_COLUMN + ", "
                + DESC_COLUMN + " "
                + "FROM " + DISEASE_TABLE_NAME;

        Cursor cursor = db.rawQuery(selectQuerry, null);

        List<Disease> diseases = new ArrayList<>();
        SymptomsTable symptomsTable = new SymptomsTable();
        if (cursor != null){
            if (cursor.moveToFirst()){
                String str = "";
                do {
                    for (String cn : cursor.getColumnNames()){
                        str = str.concat(cn + " = " + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                    }
                    diseases.add(new Disease(cursor.getString(cursor.getColumnIndex(NAME_COLUMN)),
                            symptomsTable.getSymptomsById(db, cursor.getColumnIndex(ID_COLUMN)),
                            cursor.getString(cursor.getColumnIndex(DESC_COLUMN))));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return diseases;
    }
}
