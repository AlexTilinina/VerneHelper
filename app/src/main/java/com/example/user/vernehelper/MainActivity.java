package com.example.user.vernehelper;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.vernehelper.DataBase.DBHelper;
import com.example.user.vernehelper.DataBase.ImageTable;
import com.example.user.vernehelper.imageList.ImageList;
import com.example.user.vernehelper.imageList.ModelItem;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView takePhoto;
    ImageView diseases;
    ImageView button;
    @RequiresApi(api = Build.VERSION_CODES.N)
    SQLiteDatabase db;
    DBHelper helper;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        diseases = (ImageView) findViewById(R.id.image_view_dietAndPills_main);

        diseases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DiseaseInfo.class);
                startActivity(intent);
            }
        });
        button = (ImageView) findViewById(R.id.image_view_exeForEyes_main);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EyeSettings.class));
            }
        });

        takePhoto = (ImageView) findViewById(R.id.image_view_createPhoto_main);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ImageList.class));
            }
        });

        create_Notification();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void create_Notification(){
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        Calendar calendar3 = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY,8);
        calendar2.set(Calendar.HOUR_OF_DAY,13);
        calendar3.set(Calendar.HOUR_OF_DAY,19);
        calendar.set(Calendar.MINUTE,0);
        calendar2.set(Calendar.MINUTE,0);
        calendar3.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar2.set(Calendar.SECOND,0);
        calendar3.set(Calendar.SECOND,0);

        Calendar now = Calendar.getInstance();{
            if(now.after(calendar))
                calendar.add(Calendar.HOUR_OF_DAY, 24);
            if(now.after(calendar2))
                calendar2.add(Calendar.HOUR_OF_DAY, 24);
            if(now.after(calendar3))
                calendar3.add(Calendar.HOUR_OF_DAY, 24);
        }
        Intent intent = new Intent(getApplicationContext(),Notification_reciever.class);
        PendingIntent pendingIntent8 = PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntent13 = PendingIntent.getBroadcast(getApplicationContext(),101,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntent19 = PendingIntent.getBroadcast(getApplicationContext(),102,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent8);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar2.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent13);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar3.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent19);
    }
}

