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

    public static void setAlarm(Context context, long firstTime){
        Log.d(TAG, "setAlarm");
        Intent myIntent = new Intent(context, DateChangedReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, firstTime, pendingIntent);
    }

    static long getFirstTime(int hour24, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour24);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second); // alarm at beginning of minute
        return calendar.getTimeInMillis();
    }

    static void nextDayAlarm(Context context) {
        Alarmer.setAlarm(context, getFirstTime(0,0,0)+AlarmManager.INTERVAL_DAY);
    }

    public static void setAlarm(Context context, int hour, int minute) {
        setAlarm(context, getFirstTime(hour, minute, 0));
    }
}
