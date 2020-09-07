package com.sabo.dominik.quizalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.content.Context.ALARM_SERVICE;

public class AlarmTrigger extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Alarm alarm = new Alarm(intent);

        boolean flag = false;
        for(int i = 0; i<7; i++) {
            if (alarm.getWeekDay().get(i) == 1){
                 flag = true;
                 break;
            }
        }
        if(flag){
            Intent alarmIntent = new Intent(context.getApplicationContext(), AlarmTrigger.class);
            alarm.setIntentData(alarmIntent);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, intent.getExtras().getInt("position"), alarmIntent, FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + alarm.calculateRingingTime(), pendingIntent);
        }

        Intent ringIntent = new Intent(context.getApplicationContext(), AlarmActivity.class);
        alarm.setIntentData(ringIntent);
        ringIntent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            ringIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        context.startActivity(ringIntent);
    }
}
