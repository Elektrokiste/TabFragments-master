package com.tabian.tabfragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public class BootCompletereceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);

            Intent startServiceIntent = new Intent(context,Hintergrundservice.class);
            PendingIntent startServicePendingIntent = PendingIntent.getService(context,0,startServiceIntent,0);

            Calendar caldendar = Calendar.getInstance();
            caldendar.setTimeInMillis(System.currentTimeMillis());
            caldendar.set(Calendar.HOUR_OF_DAY,7);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,caldendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,startServicePendingIntent);


        }


    }
}
