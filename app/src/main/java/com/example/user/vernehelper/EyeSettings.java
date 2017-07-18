package com.example.user.vernehelper;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

public class EyeSettings extends AppCompatActivity {

    public static final String PERIODICITY = "0";
    public static final String REGIME = "1";
    public static final String DURABILITY = "2";
    public static final String SWITCH = "3";

    int posDur;
    int posPer;
    int posReg;
    boolean checked;

    SharedPreferences sharedPreferences;

    Button start;

    Switch on;

    Spinner periodicity;
    Spinner regime;
    Spinner durability;

    String setPeriodicity;
    String setDurability;

    public static final String SHARE_PREFERENCES_NAME = "EeySettings";

    ArrayAdapter<CharSequence> periodicityAdapter;
    ArrayAdapter<CharSequence> regimeAdapter;
    ArrayAdapter<CharSequence> durabilityAdapter;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eye_settings);

        init();

        setListeners();

    }


    @Override
    protected void onStart() {

        posPer = sharedPreferences.getInt(PERIODICITY,0);
        posDur = sharedPreferences.getInt(DURABILITY,0);
        posReg = sharedPreferences.getInt(REGIME,0);

        periodicity.setSelection(posPer);
        durability.setSelection(posDur);
        regime.setSelection(posReg);

        //////

        checked = sharedPreferences.getBoolean(SWITCH,false);
        on.setChecked(checked);
        sendMessage();


        super.onStart();

    }



    private void init(){
//kostil
        sharedPreferences = getSharedPreferences(SHARE_PREFERENCES_NAME,MODE_PRIVATE);

        start = (Button) findViewById(R.id.button_start_eyeSetting);

        on = (Switch) findViewById(R.id.switch_eyeSetting);

        periodicity = (Spinner) findViewById(R.id.spinner_periodicity_eyeSetting);
        regime = (Spinner) findViewById(R.id.spinner_regime_eyeSetting);
        durability = (Spinner) findViewById(R.id.spinner_durability_eyeSetting);

        periodicityAdapter = ArrayAdapter.createFromResource(this,R.array.periodicity,android.R.layout.simple_spinner_item);
        periodicityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        periodicity.setAdapter(periodicityAdapter);

        regimeAdapter = ArrayAdapter.createFromResource(this,R.array.regime,android.R.layout.simple_spinner_item);
        regimeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regime.setAdapter(regimeAdapter);
///////////////////
        durabilityAdapter = ArrayAdapter.createFromResource(this,R.array.durability,android.R.layout.simple_spinner_item);
        durabilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        durability.setAdapter(durabilityAdapter);

        periodicity.setSelection(sharedPreferences.getInt(PERIODICITY,0));
        durability.setSelection(sharedPreferences.getInt(DURABILITY,0));
        regime.setSelection(sharedPreferences.getInt(REGIME,0));

        //////

        on.setChecked(sharedPreferences.getBoolean(SWITCH,false));
    }

    private void setListeners(){

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EyeSettings.this,EyeExercise.class);
                intent.putExtra(EyeExercise.EXTRA_TIMER,setDurability);
                startActivity(intent);
            }
        });

        on.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checked = isChecked;
                sharedPreferences.edit()
                        .putInt(PERIODICITY,posPer)
                        .putInt(DURABILITY,posDur)
                        .putInt(REGIME,posReg)
                        .putBoolean(SWITCH,checked)
                        .apply();
            }
        });

        periodicity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posPer = position;
                setPeriodicity = periodicity.getItemAtPosition(position).toString();
                sharedPreferences.edit()
                        .putInt(PERIODICITY,position)
                        .apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        durability.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posDur = position;
                setDurability = durability.getItemAtPosition(position).toString();
                sharedPreferences.edit()
                        .putInt(DURABILITY,position)
                        .apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
////////////////////////////////////////////////////////ПЛОХОЙ КОД
        regime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sharedPreferences.edit().putInt(REGIME,position).apply();
                String itemText = regime.getSelectedItem().toString();
                switch (itemText){
                    case "Чтение":
                        durability.setEnabled(false);
                        periodicity.setEnabled(false);
                        posDur = 1;
                        durability.setSelection(posDur);
                        posPer = 0;
                        periodicity.setSelection(posPer);
                        setDurability = durability.getSelectedItem().toString();
                        setPeriodicity = periodicity.getSelectedItem().toString();
                        break;
                    case "Письмо":
                        durability.setEnabled(false);
                        periodicity.setEnabled(false);
                        posDur = 0;
                        posPer = 3;
                        durability.setSelection(0);
                        periodicity.setSelection(3);
                        setDurability = durability.getSelectedItem().toString();
                        setPeriodicity = periodicity.getSelectedItem().toString();

                        break;
                    default:
                        durability.setEnabled(true);
                        periodicity.setEnabled(true);
                        setDurability = durability.getSelectedItem().toString();
                        setPeriodicity = periodicity.getSelectedItem().toString();
                        break;
                }

                sharedPreferences.edit()
                        .putInt(DURABILITY,posDur)
                        .putInt(PERIODICITY,posPer)
                        .apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    ///////////////////////////////////////////////////// hz
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(EyeSettings.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
//////////////////////////////////////////////////// new method
  //  @RequiresApi(api = Build.VERSION_CODES.N)
    public void sendMessage(){
        Intent intent = new Intent(getApplicationContext(),Notificatio_reciever_for_eyes.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),205,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int num = Integer.parseInt(periodicity.getSelectedItem().toString());

        if (on.isChecked()){
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),num * 1000 * 60, pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
        }
    }
}
