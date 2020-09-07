package com.sabo.dominik.quizalarm;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class FileManager {

    private Context context;

    public FileManager(Context context) {
        this.context = context;
    }

    public void saveAlarmData(ArrayList<Alarm> alarmsList, String fileName) throws IOException {
        FileOutputStream fileOutputStream = context.openFileOutput(fileName, MODE_PRIVATE);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(alarmsList);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public ArrayList<Alarm> loadAlarmData(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = context.openFileInput(fileName);
        ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
        ArrayList<Alarm> alarms = (ArrayList<Alarm>) inputStream.readObject();
        inputStream.close();
        fileInputStream.close();
        return alarms;
    }

    public ArrayList<Question> loadQuestionData(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = context.openFileInput(fileName);
        ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
        ArrayList<Question> questions = (ArrayList<Question>) inputStream.readObject();
        inputStream.close();
        fileInputStream.close();
        return questions;
    }
}
