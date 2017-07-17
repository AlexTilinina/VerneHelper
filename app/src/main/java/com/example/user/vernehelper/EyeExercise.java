package com.example.user.vernehelper;


import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;


public class EyeExercise extends AppCompatActivity {

    Chronometer chronometer;

    Button button;

    long timer;

    int count;

    int countOfExe;

    public static final String EXTRA_TIMER = "TIMER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        countOfExe = 4;
        count = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eye_exercise);

        final ViewPagerForExe viewPager = (ViewPagerForExe) findViewById(R.id.view_pager_test);
        viewPager.setAdapter(new ViewPagerForExAdapter(getSupportFragmentManager()));
        timer = Long.parseLong(getIntent().getExtras().getString(EXTRA_TIMER));
        timer = timer * 60 * 1000;
        viewPager.setSwipeable(false);

        chronometer = (Chronometer) findViewById(R.id.chronometer_time_exercise);
        button = (Button) findViewById(R.id.button_start_exercise);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                count++;
                button.setEnabled(false);
            }
        });

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long dif = SystemClock.elapsedRealtime() - chronometer.getBase();

                if (dif > timer){
                    if (count < countOfExe){
                        button.setEnabled(true);
                        chronometer.stop();
                        viewPager.setCurrentItem(count);
                    }else{
                        count = 0;
                        Intent intent = new Intent(EyeExercise.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

    }
}
