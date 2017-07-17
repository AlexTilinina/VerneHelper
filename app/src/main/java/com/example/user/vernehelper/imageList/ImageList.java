package com.example.user.vernehelper.imageList;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
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

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImageList extends AppCompatActivity {
    private static final int CAM_REQUEST = 1;
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

        // TODO: 17.07.2017 Initialize recycler
        imgRecycler = (RecyclerView) findViewById(R.id.img_recycler);
        verticalLinearLayoutManager = new LinearLayoutManager(this);
        horizontalLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        imgRecycler.setLayoutManager(verticalLinearLayoutManager);
        imageRvAdapter = new ImageRvAdapter(photoInf);
        imgRecycler.setAdapter(imageRvAdapter);

        // TODO: 17.07.2017 take info from database
        imageTable = new ImageTable();
        helper = new DBHelper(this);
        db = helper.getWritableDatabase();


        if (photoInf.get(0) == null) {
            photoInf = new ArrayList<>();
        } else photoInf = imageTable.getModelItems(db);

        // TODO: 17.07.2017 Initialize button
        cameraHelper = new CameraHelper();
        takePhotoBtn = (ImageButton) findViewById(R.id.takePhoto);
        takePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDirectory();
                saveFullImage();



            }
        });



    }

    // TODO: 17.07.2017 Переопределил метод OnActivityResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAM_REQUEST) {
            // Проверяем, содержит ли результат маленькую картинку
            if (data != null) {
                if (data.hasExtra("data")) {
                    Bitmap thumbnailBitmap = (Bitmap) data.getExtras().get("data");
                    // TODO Какие-то действия с миниатюрой
                    imageTable.insert(db,new ModelItem(cameraHelper.getYouEditTextValue(),cameraHelper.getImageUri(this,thumbnailBitmap)));



                }
            }
        }
    }

    // TODO: 17.07.2017 Сохраняем изображение в галерею
    public void saveFullImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(directory.getPath() + "/" + "photo_"
                + System.currentTimeMillis() + ".jpg");
        mOutputFileUri =Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mOutputFileUri);
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            cameraHelper.alert(this);
            startActivityForResult(intent, CAM_REQUEST);


        }


    }
    // TODO: 17.07.2017 Создаем директорию для хранения фото
    public void createDirectory (){

        directory = new File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "NautilusApp");
        if (!directory.exists())
            directory.mkdirs();
    }
}
