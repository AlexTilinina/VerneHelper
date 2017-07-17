package com.example.user.vernehelper.imageList;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.Method;

/**
 * Created by Tony on 17.07.2017.
 */

public class CameraHelper {
    File directory;
    private static final int CAM_REQUEST = 1;
    private String YouEditTextValue = "test";

    public String getYouEditTextValue() {
        return YouEditTextValue;
    }

    // TODO: 17.07.2017 Get uri from bitmap
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    // TODO: 17.07.2017 create AlertDiolog for description
    public String alert(Context context) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        final EditText edittext = new EditText(context);
        alert.setMessage("Введите описание");
        alert.setTitle("Введите описание к вашему фото");

        alert.setView(edittext);

        alert.setPositiveButton("Yes Option", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //OR
                YouEditTextValue = edittext.getText().toString();
            }
        });

        alert.setNegativeButton("No Option", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
                YouEditTextValue = "";
            }
        });

        alert.show();
        return YouEditTextValue;
    }

}
