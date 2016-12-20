package org.sfeto.nongli;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by qinghao on 16-11-22.
 */

public class Alarmer {

    private static final String TAG = "Alarmer";

    public static void setAlarm(Context context, long triggerMilis){
        Log.d(TAG, "setAlarm");
        Log.d(TAG, "triMilis:" + triggerMilis);
        Log.d(TAG, "curr time:" + getTimeString(Calendar.getInstance().getTimeInMillis()));
        Log.d(TAG, "next time:" + getTimeString(triggerMilis));
        Intent myIntent = new Intent(context, DateChangedReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, triggerMilis, pendingIntent);
    }

    private static String getTimeString(long milis) {
        Calendar cal =Calendar.getInstance();
        cal.setTimeInMillis(milis);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str_time = sdf.format(cal.getTime());
        return str_time;
    }

    static long getMilis(int hour24, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour24);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second); // alarm at beginning of minute
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    static long getNextDay() {
        return getMilis(0,0,0)+AlarmManager.INTERVAL_DAY;
    }

    static void setNextAlarm(Context context) {
        long next= getNextDay();
        Log.d(TAG, "   next :" + next);
        setAlarm(context, next);
    }

    public static void setAlarm(Context context, int hour, int minute) {
        setAlarm(context, getMilis(hour, minute, 0));
    }
}
