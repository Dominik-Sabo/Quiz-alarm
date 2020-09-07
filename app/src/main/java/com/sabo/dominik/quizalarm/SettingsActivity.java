package com.sabo.dominik.quizalarm;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SettingsActivity extends AppCompatActivity {

    final private int RESULT_DELETE = 5;

    ImageView ivBack, ivDone, ivThemeChevron, ivRingtoneChevron;
    TimePicker timePicker;
    EditText etAlarmName;
    TextView tvMonday, tvTuesday, tvWednesday, tvThursday, tvFriday, tvSaturday, tvSunday, tvName, tvRingtone, tvTheme, tvAll, tvAnimal, tvMusic, tvPopCulture, tvHistory, tvAnime;
    RadioButton rbEasy, rbMedium, rbHard;
    RadioGroup rgDifficulty;
    Button btnDelete;

    Uri ringtonePath;
    ArrayList<Integer> weekDays = new ArrayList<Integer>(Arrays.asList(new Integer[7]));
    int quizDifficulty;
    int position;

    boolean themeFlag = false;
    boolean finishFlag = false;
    boolean deleteFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initializeUI();
        setUpListeners();

        if(getIntent().hasExtra("position")) {
            position = getIntent().getExtras().getInt("position");
            this.tvName.setText(getIntent().getExtras().getString("name"));
            this.etAlarmName.setText(getIntent().getExtras().getString("name"));

            this.tvTheme.setText(getIntent().getExtras().getString("quizTheme"));

            this.quizDifficulty = getIntent().getExtras().getInt("quizDifficulty");
            updateRadioButton(quizDifficulty);

            this.ringtonePath = getIntent().getExtras().getParcelable("ringtoneURI");
            if(this.tvRingtone.getText().toString().equals("")) ringtonePath = RingtoneManager.getActualDefaultRingtoneUri(SettingsActivity.this, RingtoneManager.TYPE_ALARM);
            this.tvRingtone.setText(RingtoneManager.getRingtone(this, ringtonePath).getTitle(this));

            this.weekDays = getIntent().getExtras().getIntegerArrayList("weekDays");
            updateWeekColours(weekDays);

            this.timePicker.setHour(getIntent().getExtras().getInt("hour"));
            this.timePicker.setMinute(getIntent().getExtras().getInt("minute"));
            btnDelete.setVisibility(View.VISIBLE);
        }
        else {
            fillWeekDays();
            ringtonePath = RingtoneManager.getActualDefaultRingtoneUri(SettingsActivity.this, RingtoneManager.TYPE_ALARM);
            quizDifficulty = 1;
        }
    }

    private void fillWeekDays() {
        Collections.fill(weekDays, 1);
        weekDays.set(5, 0);
        weekDays.set(6, 0);
    }

    private void setUpListeners() {


        tvRingtone.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(SettingsActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(SettingsActivity.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        if (ContextCompat.checkSelfPermission(SettingsActivity.this,
                                Manifest.permission.READ_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(SettingsActivity.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                        }
                    } else {
                        ActivityCompat.requestPermissions(SettingsActivity.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                    }
                } else {
                    Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                    intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM);
                    intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Tone");
                    intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, ringtonePath);
                    intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false);
                    intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true);
                    startActivityForResult(intent, 999);
                }
            }
        });

        ivRingtoneChevron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(SettingsActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(SettingsActivity.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE)) {

                    } else {
                        ActivityCompat.requestPermissions(SettingsActivity.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                    }
                } else {
                    Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                    intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM);
                    intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Tone");
                    intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, ringtonePath);
                    intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false);
                    intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true);
                    startActivityForResult(intent, 999);
                }
            }
        });

        tvTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themeFlag = !themeFlag;
                if (themeFlag){
                    ivThemeChevron.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                    tvAll.setVisibility(View.VISIBLE);
                    tvAnimal.setVisibility(View.VISIBLE);
                    tvMusic.setVisibility(View.VISIBLE);
                    tvPopCulture.setVisibility(View.VISIBLE);
                    tvHistory.setVisibility(View.VISIBLE);
                    tvAnime.setVisibility(View.VISIBLE);
                }
                else{
                    ivThemeChevron.setImageResource(R.drawable.ic_chevron_right_black_24dp);
                    tvAll.setVisibility(View.GONE);
                    tvAnimal.setVisibility(View.GONE);
                    tvMusic.setVisibility(View.GONE);
                    tvPopCulture.setVisibility(View.GONE);
                    tvHistory.setVisibility(View.GONE);
                    tvAnime.setVisibility(View.GONE);
                }
            }
        });

        ivThemeChevron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themeFlag = !themeFlag;
                if (themeFlag){
                    ivThemeChevron.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                    tvAll.setVisibility(View.VISIBLE);
                    tvAnimal.setVisibility(View.VISIBLE);
                    tvMusic.setVisibility(View.VISIBLE);
                    tvPopCulture.setVisibility(View.VISIBLE);
                    tvHistory.setVisibility(View.VISIBLE);
                    tvAnime.setVisibility(View.VISIBLE);
                }
                else{
                    ivThemeChevron.setImageResource(R.drawable.ic_chevron_right_black_24dp);
                    tvAll.setVisibility(View.GONE);
                    tvAnimal.setVisibility(View.GONE);
                    tvMusic.setVisibility(View.GONE);
                    tvPopCulture.setVisibility(View.GONE);
                    tvHistory.setVisibility(View.GONE);
                    tvAnime.setVisibility(View.GONE);
                }
            }
        });

        tvAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTheme.setText(tvAll.getText().toString());
            }
        });

        tvAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTheme.setText(tvAnimal.getText().toString());
            }
        });

        tvMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTheme.setText(tvMusic.getText().toString());
            }
        });

        tvPopCulture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTheme.setText(tvPopCulture.getText().toString());
            }
        });

        tvHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTheme.setText(tvHistory.getText().toString());
            }
        });

        tvAnime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTheme.setText(tvAnime.getText().toString());
            }
        });

        tvMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleWeekday(tvMonday, 0);
            }
        });

        tvTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleWeekday(tvTuesday, 1);
            }
        });

        tvWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleWeekday(tvWednesday, 2);
            }
        });

        tvThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleWeekday(tvThursday, 3);
            }
        });

        tvFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleWeekday(tvFriday, 4);
            }
        });

        tvSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleWeekday(tvSaturday, 5);
            }
        });

        tvSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleWeekday(tvSunday, 6);
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishFlag = true;
                finish();
            }
        });

        rbEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizDifficulty = 0;
            }
        });

        rbMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizDifficulty = 1;
            }
        });

        rbHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizDifficulty = 2;
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(SettingsActivity.this)
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to delete this alarm?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finishFlag = true;
                        deleteFlag = true;
                        finish();
                    }
                })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

    private void toggleWeekday(TextView day, int position) {
        weekDays.set(position, weekDays.get(position)!=0 ? 0:1);
        if(weekDays.get(position) != 0){
            day.setTextColor(Color.rgb(0,0,205));
        }
        else{
            day.setTextColor(Color.rgb(0, 133, 119));
        }
    }


    private void updateWeekColours(ArrayList<Integer> weekDays){
        if(weekDays.get(0) !=0) tvMonday.setTextColor(Color.rgb(0,0,205));
        else tvMonday.setTextColor(Color.rgb(0, 133, 119));
        if(weekDays.get(1) !=0) tvTuesday.setTextColor(Color.rgb(0,0,205));
        else tvTuesday.setTextColor(Color.rgb(0, 133, 119));
        if(weekDays.get(2) !=0) tvWednesday.setTextColor(Color.rgb(0,0,205));
        else tvWednesday.setTextColor(Color.rgb(0, 133, 119));
        if(weekDays.get(3) !=0) tvThursday.setTextColor(Color.rgb(0,0,205));
        else tvThursday.setTextColor(Color.rgb(0, 133, 119));
        if(weekDays.get(4) !=0) tvFriday.setTextColor(Color.rgb(0,0,205));
        else tvFriday.setTextColor(Color.rgb(0, 133, 119));
        if(weekDays.get(5) !=0) tvSaturday.setTextColor(Color.rgb(0,0,205));
        else tvSaturday.setTextColor(Color.rgb(0, 133, 119));
        if(weekDays.get(6) !=0) tvSunday.setTextColor(Color.rgb(0,0,205));
        else tvSunday.setTextColor(Color.rgb(0, 133, 119));
    }

    private void updateRadioButton(int quizDifficulty){
        switch(quizDifficulty){
            case 0: rbEasy.toggle(); break;
            case 1: rbMedium.toggle(); break;
            case 2: rbHard.toggle(); break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 999) {
            Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
            if (uri != null) {
                ringtonePath = uri;
                tvRingtone.setText(RingtoneManager.getRingtone(this, uri).getTitle(this));
                }
            }
        }



    private void initializeUI() {
        this.ivBack = findViewById(R.id.ivBackArrow);
        this.ivDone = findViewById(R.id.ivDone);
        this.ivRingtoneChevron = findViewById(R.id.ivChevron);
        this.ivThemeChevron = findViewById(R.id.ivChevron2);
        this.timePicker = findViewById(R.id.tpTimePicker);
        this.etAlarmName = findViewById(R.id.etAlarmName);

        this.tvMonday = findViewById(R.id.tvMonday);
        this.tvTuesday = findViewById(R.id.tvTuesday);
        this.tvWednesday = findViewById(R.id.tvWednesday);
        this.tvThursday = findViewById(R.id.tvThursday);
        this.tvFriday = findViewById(R.id.tvFriday);
        this.tvSaturday = findViewById(R.id.tvSaturday);
        this.tvSunday = findViewById(R.id.tvSunday);
        this.tvName = findViewById(R.id.tvNew);

        this.tvRingtone = findViewById(R.id.tvRingtoneName);
        this.tvTheme = findViewById(R.id.tvQuizThemeName);

        this.rgDifficulty = findViewById(R.id.rgDifficulty);
        this.rbEasy = findViewById(R.id.rbEasy);
        this.rbMedium = findViewById(R.id.rbMedium);
        this.rbHard = findViewById(R.id.rbHard);

        this.tvAll = findViewById(R.id.tvAll);
        this.tvAnimal = findViewById(R.id.tvAnimals);
        this.tvMusic = findViewById(R.id.tvMusic);
        this.tvPopCulture = findViewById(R.id.tvPopCulture);
        this.tvHistory = findViewById(R.id.tvHistory);
        this.tvAnime = findViewById(R.id.tvAnime);

        this.btnDelete = findViewById(R.id.btnDelete);
    }

    @Override
    public void finish() {
        if(finishFlag) {
            Intent data = new Intent();
            if(getIntent().hasExtra("position")) {
                data.putExtra("position", position);
            }
            if(deleteFlag)
            {
                setResult(RESULT_DELETE, data);
                super.finish();
            }
            setIntentData(data);
            setResult(RESULT_OK, data);
            super.finish();
        }
        else super.finish();
    }

    private void setIntentData(Intent data) {
        if(etAlarmName.getText().toString().equals("")) data.putExtra("name", "Alarm");
        else data.putExtra("name", etAlarmName.getText().toString());
        data.putExtra("ringtone", ringtonePath.toString());
        data.putExtra("quizTheme", tvTheme.getText().toString());
        data.putExtra("quizDifficulty", quizDifficulty);
        data.putIntegerArrayListExtra("weekDays", weekDays);
        data.putExtra("hour", timePicker.getHour());
        data.putExtra("minute", timePicker.getMinute());
    }
}
