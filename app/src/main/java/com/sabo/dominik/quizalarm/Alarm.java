package com.sabo.dominik.quizalarm;

import android.content.Intent;
import android.net.Uri;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;


public class Alarm implements Serializable {
    private String name;
    private String quizTheme;
    private int quizDifficulty;
    private String ringTone;
    private ArrayList<Integer> weekDay;
    private int hour;
    private int minute;
    private boolean active;

    public Alarm(String name, int hour, int minute, int weekDays, Uri uri ) {
        this.name = name;
        this.quizTheme = "All";
        this.quizDifficulty = 1;
        this.ringTone = uri.toString();
        this.hour = hour;
        this.minute = minute;
        this.weekDay = new ArrayList<Integer>(Arrays.asList(new Integer[7]));
        this.active = false;
        if(weekDays == 1){
            Collections.fill(weekDay, 1);
            weekDay.set(5, 0);
            weekDay.set(6, 0);
        }
        else{
            Collections.fill(weekDay, 0);
            weekDay.set(5, 1);
            weekDay.set(6, 1);
        }
    }

    public Alarm(Intent data){
        this.name = data.getExtras().getString("name");
        this.quizTheme = data.getExtras().getString("quizTheme");
        this.quizDifficulty = data.getExtras().getInt("quizDifficulty");
        this.ringTone = data.getExtras().getString("ringtone");
        this.weekDay = data.getExtras().getIntegerArrayList("weekDays");
        this.hour = data.getExtras().getInt("hour");
        this.minute = data.getExtras().getInt("minute");
        this.active = true;
    }

    public String getName() {
        return name;
    }

    public String getQuizTheme() {
        return quizTheme;
    }

    public int getQuizDifficulty() {
        return quizDifficulty;
    }

    public String getRingTone() {
        return ringTone;
    }

    public ArrayList<Integer> getWeekDay() {
        return weekDay;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public boolean getActive(){
        return active;
    }

    public void toggleAlarm(){
        active = !active;
    }

    public Uri getURI(){
        return Uri.parse(this.ringTone);
    }


    public String calculateCountDown() {
        int time = calculateRingingTime() / 1000;
        int day = time /86400;
        int hour = time/3600 - day*24;
        int minute = time/60 - (day * 24 + hour)*60;

        if(day != 0) return "Ring in " + day + " days " + hour + " hours " + minute + " minutes";
        else if(hour!=0) return "Ring in " + hour + " hours " + minute + " minutes";
        else return "Ring in " + minute + " minutes";
    }

    public int calculateRingingTime() {
        SimpleDateFormat currentMinute = new SimpleDateFormat("mm");
        SimpleDateFormat currentHour = new SimpleDateFormat("HH");

        Calendar calendar = Calendar.getInstance();
        String dayLongName = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

        int hour, minute, day = 0, dayOfWeek = 0;

        switch (dayLongName){
            case "Monday": dayOfWeek = 0; break;
            case "Tuesday": dayOfWeek = 1; break;
            case "Wednesday": dayOfWeek = 2; break;
            case "Thursday": dayOfWeek = 3; break;
            case "Friday": dayOfWeek = 4; break;
            case "Saturday": dayOfWeek = 5; break;
            case "Sunday": dayOfWeek = 6; break;
        }

        if(this.minute >= Integer.parseInt(currentMinute.format(new Date()))) {
            minute = this.minute - Integer.parseInt(currentMinute.format(new Date()));
        }
        else{
            minute = 60 + this.minute - Integer.parseInt(currentMinute.format(new Date()));
        }


        if(this.hour >= Integer.parseInt(currentHour.format(new Date()))) {
            hour = this.hour - Integer.parseInt(currentHour.format(new Date()));
            if (this.minute < Integer.parseInt(currentMinute.format(new Date()))) hour--;
            if(this.hour == Integer.parseInt(currentHour.format(new Date())))
                if(this.minute > Integer.parseInt(currentMinute.format(new Date()))) hour = 0;
                else if (this.minute == Integer.parseInt(currentMinute.format(new Date()))) hour = 24;
                else hour = 23;
        }
        else{
            hour = 23 - Integer.parseInt(currentHour.format(new Date())) + this.hour;
            if(this.minute >= Integer.parseInt(currentMinute.format(new Date()))) hour++;
        }

        boolean weekDayFlag = false;
        for(int i = 0; i<7; i++){
            if(this.weekDay.get(i) == 1) {
                weekDayFlag = true;
                break;
            }
        }
        boolean first = false;
         if(weekDayFlag){
             int i = dayOfWeek;
            while(true){
                if(i == 7) i = 0;
                if(this.weekDay.get(i) == 1) if(first) break;
                first = true;
                day++;
                i++;
            }

            if(this.hour == Integer.parseInt(currentHour.format(new Date())) && this.minute <= Integer.parseInt(currentMinute.format(new Date()))) day--;
            else if(this.hour < Integer.parseInt(currentHour.format(new Date()))) day--;
            if(day == 7) {
                if(this.hour == Integer.parseInt(currentHour.format(new Date())) && this.minute > Integer.parseInt(currentMinute.format(new Date()))) day = 0;
                else if(this.hour > Integer.parseInt(currentHour.format(new Date()))) day = 0;
            }

             boolean onlyTodayFlag = false;
             for(int j = 0; j<7; j++){
                 if(this.weekDay.get(j) == 1 && !(this.weekDay.get(dayOfWeek).equals(this.weekDay.get(j)))) {
                     onlyTodayFlag = true;
                     break;
                 }
             }
             if(this.weekDay.get(dayOfWeek) == 1)
             {
                 if(this.hour == Integer.parseInt(currentHour.format(new Date())) && this.minute > Integer.parseInt(currentMinute.format(new Date()))) day = 0;
                 else if(this.hour > Integer.parseInt(currentHour.format(new Date()))) day = 0;
             }
        }
            return ((day*24 + hour)*60 + minute)*60*1000;
    }

    public void setIntentData(Intent data) {
        data.putExtra("name", this.getName());
        data.putExtra("ringtoneURI", this.getURI());
        data.putExtra("ringtone", this.getRingTone());
        data.putExtra("quizTheme", this.getQuizTheme());
        data.putExtra("quizDifficulty", this.getQuizDifficulty());
        data.putIntegerArrayListExtra("weekDays", this.getWeekDay());
        data.putExtra("hour", this.getHour());
        data.putExtra("minute", this.getMinute());
    }

}
