package com.sabo.dominik.quizalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;


public class MainActivity extends AppCompatActivity implements AlarmClickInterface {

    final private int NEW_REQUEST_CODE = 1;
    final private int EDIT_REQUEST_CODE = 2;
    final private int RESULT_DELETE = 5;

    private RecyclerView rvRecyclerView;
    private RecyclerAdapter adapter;
    private Button btnAdd;
    private ArrayList<Alarm> alarms = new ArrayList<>();
    AlarmManager alarmManager;

    FileManager fileManager;
    private String alarmFileName = "AlarmList";
    private File alarmFile, questionFile;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileManager = new FileManager(this);

        try {
            questionFile = new File(MainActivity.this.getFilesDir(), "Anime");
            if (questionFile.createNewFile()){
                questionFile = new File(MainActivity.this.getFilesDir(), "Animals");
                questionFile.createNewFile();
                questionFile = new File(MainActivity.this.getFilesDir(), "Music");
                questionFile.createNewFile();
                questionFile = new File(MainActivity.this.getFilesDir(), "History");
                questionFile.createNewFile();
                questionFile = new File(MainActivity.this.getFilesDir(), "Pop Culture");
                questionFile.createNewFile();
                Question question = new Question();
                question.createQuestions(this);
            }
            alarmFile = new File(MainActivity.this.getFilesDir(), alarmFileName);
            if(alarmFile.createNewFile()){
                alarms.add(new Alarm("Weekdays", 7, 0, 1, RingtoneManager.getActualDefaultRingtoneUri(MainActivity.this, RingtoneManager.TYPE_ALARM)));
                alarms.add(new Alarm("Weekends", 7, 30, 2, RingtoneManager.getActualDefaultRingtoneUri(MainActivity.this, RingtoneManager.TYPE_ALARM)));
                fileManager.saveAlarmData(alarms, alarmFileName);
            }
            alarms = fileManager.loadAlarmData(alarmFileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        initializeUI();
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        adapter.addData(alarms);
    }

    private void initializeUI() {
        this.rvRecyclerView = findViewById(R.id.rvRecyclerView);
        this.rvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.adapter = new RecyclerAdapter(this);
        this.btnAdd = findViewById(R.id.btnAdd);
        rvRecyclerView.setAdapter(adapter);
    }

    public void addOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivityForResult(intent, NEW_REQUEST_CODE);
    }

    @Override
    public void onAlarmClick(int position) {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        alarms.get(position).setIntentData(intent);
        intent.putExtra("position", position);
        startActivityForResult(intent, EDIT_REQUEST_CODE);
    }

    @Override
    public void onSwitchClick(int position) {
        alarms.get(position).toggleAlarm();
        try {
            fileManager.saveAlarmData(alarms, alarmFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(alarms.get(position).getActive()){
            setAlarm(position);
            Toast.makeText(this, alarms.get(position).calculateCountDown(), Toast.LENGTH_SHORT).show();
        }
        else{
            cancelAlarm(position);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            if(requestCode == NEW_REQUEST_CODE) {
                Alarm alarm = new Alarm(data);
                alarms.add(alarm);
                adapter.addItem(alarm);
                try {
                    fileManager.saveAlarmData(alarms, alarmFileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                setAlarm(alarms.indexOf(alarm));
                Toast.makeText(this, alarm.calculateCountDown(), Toast.LENGTH_SHORT).show();

            }
            if(requestCode == EDIT_REQUEST_CODE) {
                cancelAlarm(data.getExtras().getInt("position"));
                Alarm alarm = new Alarm(data);
                alarms.set(data.getExtras().getInt("position"), alarm);
                adapter.changeItem(alarm, data.getExtras().getInt("position"));
                try {
                    fileManager.saveAlarmData(alarms, alarmFileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                setAlarm(data.getExtras().getInt("position"));
                Toast.makeText(this, alarm.calculateCountDown(), Toast.LENGTH_SHORT).show();
            }
        }
        if (resultCode == RESULT_DELETE)
        {
            int position = data.getExtras().getInt("position");


            for(int i = position; i<alarms.size(); i++)
            {
                cancelAlarm(i);
            }

            alarms.remove(data.getExtras().getInt("position"));
            adapter.removeItem(data.getExtras().getInt("position"));

            for(int i = position; i<alarms.size(); i++)
            {
                if(alarms.get(position).getActive()) {
                    setAlarm(i);
                }
            }

            try {
                fileManager.saveAlarmData(alarms, alarmFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void cancelAlarm(int position){
        Intent intent = new Intent(MainActivity.this, AlarmTrigger.class);
        alarms.get(position).setIntentData(intent);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), position, intent, FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
    }

    private void setAlarm(int position){
        Intent intent = new Intent(MainActivity.this, AlarmTrigger.class);
        alarms.get(position).setIntentData(intent);
        intent.putExtra("position", position);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), position, intent, FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + alarms.get(position).calculateRingingTime(), pendingIntent);
    }

}

