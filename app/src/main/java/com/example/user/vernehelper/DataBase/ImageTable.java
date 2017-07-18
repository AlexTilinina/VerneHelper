package com.example.user.vernehelper.DataBase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.user.vernehelper.Disease;
import com.example.user.vernehelper.imageList.ModelItem;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tony on 17.07.2017.
 */

public class ImageTable {
    public static final String IMAGE_TABLE_NAME = "imageTable";
    public static final String IMAGE_COLUMN_DESCR = "image_descr";
    public static final String URI_COLUMN = "image_uri";
    public static final String ID_COLUMN = "id";
    public Uri URI;



    public static String createTable(){
        return "CREATE TABLE " + IMAGE_TABLE_NAME + " (" +
                ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                IMAGE_COLUMN_DESCR + " TEXT, " +
                URI_COLUMN + " TEXT)";
    }

    public void insert(SQLiteDatabase db, ModelItem modelItem){
        ContentValues cv = new ContentValues();
        cv.put(IMAGE_COLUMN_DESCR, modelItem.getDescription());
        cv.put(URI_COLUMN, modelItem.getPhotoUri().toString());

        db.insert(IMAGE_TABLE_NAME, null, cv);
        // TODO: 17.07.2017 Нужны столбы с описанием, с id, с uri.

    }



    List<ModelItem> photoInf = new ArrayList<>();

    public List<ModelItem> getModelItems(SQLiteDatabase db){

        String selectQuerry = "SELECT "
                + ID_COLUMN + ", "
                + IMAGE_COLUMN_DESCR  + ", "
                + URI_COLUMN + " "
                + "FROM " + IMAGE_TABLE_NAME;



        Cursor c = db.rawQuery(selectQuerry, null);


        if (c != null){
            if (c.moveToFirst()){
                do {

                    photoInf.add(new ModelItem(
                            c.getString(c.getColumnIndex(IMAGE_COLUMN_DESCR)),
                            Uri.parse(c.getString(c.getColumnIndex(URI_COLUMN))))
                    );
                } while (c.moveToNext());
            }
            c.close();
        }
        return photoInf;
    }

}
