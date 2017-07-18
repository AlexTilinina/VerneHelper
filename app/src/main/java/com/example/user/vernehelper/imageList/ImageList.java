package com.example.user.vernehelper.imageList;

import android.Manifest;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.user.vernehelper.DataBase.DBHelper;
import com.example.user.vernehelper.DataBase.ImageTable;
import com.example.user.vernehelper.Disease;
import com.example.user.vernehelper.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImageList extends AppCompatActivity {
    private static final int CAM_REQUEST = 12124;
    // TODO: 17.07.2017 Initialize
    RecyclerView imgRecycler;
    LinearLayoutManager verticalLinearLayoutManager;
    LinearLayoutManager horizontalLinearLayoutManager;
    private List<ModelItem> photoInf;
    ImageRvAdapter imageRvAdapter;
    ImageTable imageTable;
    DBHelper helper;
    SQLiteDatabase db;
    ImageButton takePhotoBtn;
    CameraHelper cameraHelper;
    Uri mOutputFileUri;
    File directory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);


        // TODO: 17.07.2017 take info from database
        imageTable = new ImageTable();
        helper = new DBHelper(this);
        db = helper.getWritableDatabase();

        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA

                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {/* ... */}

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
        }).check();
        createDirectory();


//        if (photoInf == null) {
//            photoInf = new ArrayList<>();
//        } else {
            photoInf = imageTable.getModelItems(db);
//        }

        // TODO: 17.07.2017 Initialize recycler
        imgRecycler = (RecyclerView) findViewById(R.id.img_recycler);
        verticalLinearLayoutManager = new LinearLayoutManager(this);
        horizontalLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        imageRvAdapter = new ImageRvAdapter(photoInf);
        imgRecycler.setAdapter(imageRvAdapter);
        imgRecycler.setLayoutManager(verticalLinearLayoutManager);

        // TODO: 17.07.2017 Initialize button
        cameraHelper = new CameraHelper();
        takePhotoBtn = (ImageButton) findViewById(R.id.takePhoto);
        takePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFullImage();

            }
        });


    }

    // TODO: 17.07.2017 Переопределил метод OnActivityResult


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAM_REQUEST) {
            // Проверяем, содержит ли результат маленькую картинку
            if (data != null) {
                if (data.hasExtra("data")) {
                    Bitmap thumbnailBitmap = (Bitmap) data.getExtras().get("data");
                    data.getExtras().get("data");
                    // TODO Какие-то действия с миниатюрой

                    imageTable.insert(db, new ModelItem(cameraHelper.getYouEditTextValue(), cameraHelper.getImageUri(this, thumbnailBitmap)));
                    photoInf = imageTable.getModelItems(db);
                    helper.onUpgrade(db,1,2);
                } else {
                    data.getExtras().get("data");
                    imageTable.insert(db, new ModelItem(cameraHelper.getYouEditTextValue(), mOutputFileUri));
                    photoInf = imageTable.getModelItems(db);
                }
                imageRvAdapter = new ImageRvAdapter(photoInf);
                imgRecycler.setAdapter(imageRvAdapter);
            }
        }
    }

    // TODO: 17.07.2017 Сохраняем изображение в галерею
    public void saveFullImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(directory.getPath() + "/" + "photo_"
                + System.currentTimeMillis() + ".jpg");
        mOutputFileUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mOutputFileUri.getSchemeSpecificPart());
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        startActivityForResult(intent, CAM_REQUEST);
        //cameraHelper.alert(this);


    }

    // TODO: 17.07.2017 Создаем директорию для хранения фото
    public void createDirectory() {
        directory = new File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "NautilusApp");
        if (!directory.exists())
            directory.mkdirs();
    }

   // public void deleteFromDB(int position){
       // db.delete("ImageTable","id = " + position,null);

    //}
}
