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



}
