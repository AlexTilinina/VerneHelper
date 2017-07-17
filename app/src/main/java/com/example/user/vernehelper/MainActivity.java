package com.example.user.vernehelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.user.vernehelper.imageList.ImageList;

public class MainActivity extends AppCompatActivity {
    ImageView takePhoto;
    ImageView diseases;
    ImageView button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}

