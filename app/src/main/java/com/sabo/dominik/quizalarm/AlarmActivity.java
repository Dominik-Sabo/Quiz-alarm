package com.sabo.dominik.quizalarm;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class AlarmActivity extends AppCompatActivity {

    private Button btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4, btnAnswer5;
    private TextView tvQuestion, tvScore;
    private MediaPlayer mediaPlayer;
    private PowerManager.WakeLock wakeLock;
    ArrayList<Question> questions = new ArrayList<Question>();
    ArrayList<Button> answers = new ArrayList<Button>();
    int score = 0, questionNumber = -1, penalty;
    Alarm alarm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);


        alarm = new Alarm(getIntent());
        penalty = alarm.getQuizDifficulty();


        try {
            loadQuestions();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        setUpViews();
        setUpListeners();

        setUpNextQuestion();

        mediaPlayer = new MediaPlayer();


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_FULLSCREEN |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true);
            setTurnScreenOn(true);
            KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
            keyguardManager.requestDismissKeyguard(this, null);
        } else {
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        }

        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, this.getClass().getSimpleName());
        wakeLock.acquire();

        try {
            playAlarm(alarm, mediaPlayer);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void setUpNextQuestion() {
        questionNumber++;
        if (questionNumber == questions.size()) {
            questionNumber = 0;
            Collections.shuffle(questions);
        }

        ArrayList<String> question = questions.get(questionNumber).prepareQuestion(alarm.getQuizDifficulty());
        tvQuestion.setText(questions.get(questionNumber).getQuestion());
        for (int i = 0; i < question.size(); i++) {
            answers.get(i).setText(question.get(i));
        }
    }

    private void loadQuestions() throws IOException, ClassNotFoundException {
        FileManager loader = new FileManager(this);
        if (alarm.getQuizTheme().equals("All")) {
            questions.addAll(loader.loadQuestionData("Animals"));
            questions.addAll(loader.loadQuestionData("Pop Culture"));
            questions.addAll(loader.loadQuestionData("History"));
            questions.addAll(loader.loadQuestionData("Music"));
            questions.addAll(loader.loadQuestionData("Anime"));
        } else questions.addAll(loader.loadQuestionData(alarm.getQuizTheme()));

        Collections.shuffle(questions);
    }

    private void setUpListeners() {

        btnAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(btnAnswer1);
                setUpNextQuestion();
            }
        });

        btnAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(btnAnswer2);
                setUpNextQuestion();
            }
        });

        btnAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(btnAnswer3);
                setUpNextQuestion();
            }
        });

        btnAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(btnAnswer4);
                setUpNextQuestion();
            }
        });

        btnAnswer5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(btnAnswer5);
                setUpNextQuestion();
            }
        });
    }

    private void checkAnswer(Button answer) {
        if (answer.getText().equals(questions.get(questionNumber).getCorrect())) {
            score++;
            tvScore.setText(String.valueOf(score));
        } else {
            penalty++;
            if (penalty == 3) {
                score--;
                if (score < 0) score = 0;
                tvScore.setText(String.valueOf(score));
                penalty = alarm.getQuizDifficulty();
            }
        }
        if (score == 5) {
            mediaPlayer.stop();
            mediaPlayer.release();
            wakeLock.release();
            finish();
        }
    }

    private void setUpViews() {
        this.btnAnswer1 = findViewById(R.id.btnAnswer1);
        this.btnAnswer2 = findViewById(R.id.btnAnswer2);
        this.btnAnswer3 = findViewById(R.id.btnAnswer3);
        this.btnAnswer4 = findViewById(R.id.btnAnswer4);
        if (alarm.getQuizDifficulty() > 0) btnAnswer4.setVisibility(View.VISIBLE);
        this.btnAnswer5 = findViewById(R.id.btnAnswer5);
        if (alarm.getQuizDifficulty() > 1) btnAnswer5.setVisibility(View.VISIBLE);
        this.tvQuestion = findViewById(R.id.tvQuestion);
        this.tvScore = findViewById(R.id.tvScore);

        answers.add(btnAnswer1);
        answers.add(btnAnswer2);
        answers.add(btnAnswer3);
        answers.add(btnAnswer4);
        answers.add(btnAnswer5);
    }

    private void playAlarm(Alarm alarm, MediaPlayer mediaPlayer) throws IOException {
        AudioAttributes audioAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ALARM).build();

        mediaPlayer.reset();
        mediaPlayer.setDataSource(AlarmActivity.this, alarm.getURI());
        mediaPlayer.setAudioAttributes(audioAttributes);
        mediaPlayer.setLooping(true);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

    @Override
    public void onBackPressed() {
    }


}
