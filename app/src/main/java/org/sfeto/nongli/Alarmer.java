package org.sfeto.nongli;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by qinghao on 16-11-22.
 */

public class Alarmer {

    private static final String TAG = "Alarmer";

    public static void setAlarm(Context context, int hour24, int minute){
        Log.d(TAG, "setAlarm");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour24);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 00); // alarm at beginning of minute
        long firstTime = calendar.getTimeInMillis();
        Intent myIntent = new Intent(context, DateChangedReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC, firstTime, AlarmManager.INTERVAL_DAY, pendingIntent);
    }
}
