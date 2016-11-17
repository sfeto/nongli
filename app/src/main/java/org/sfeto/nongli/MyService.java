package org.sfeto.nongli;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

import com.cnblogs.liuSiyuan.Lunar;

import java.util.Calendar;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int month = intent.getIntExtra("month", 0);
        int day = intent.getIntExtra("day", 0);
        if(month != 0 && day != 0){
            changeIcon(month, day);
        } else {
            changeIcon();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public static void saveConfig(Context context, String lastClassName){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("lastClassName", lastClassName);
        editor.commit();
    }

    public static String readConfig(Context context){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getString("lastClassName", context.getPackageName()+".MainActivity");
    }

    public void changeIcon() {
        Lunar lunar = new Lunar(Calendar.getInstance());
        String className = getPackageName()+
                getClassName(lunar.getMonth(), lunar.getDay());
        changeIcon(className);
    }

    public void changeIcon(int month, int day) {
        String className = getPackageName()+
                getClassName(month, day);
        changeIcon(className);
    }

    private String getClassName(int month, int day) {
        return String.format(".Luna%02d%02d",
                month,
                day);
    }

    public void changeIcon(String cls) {
        Context context = getBaseContext();
        ComponentName last_cn = new ComponentName(
                context,
                MyService.readConfig(context));
        PackageManager pm = getApplicationContext().getPackageManager();
        pm.setComponentEnabledSetting(last_cn,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
        Log.d("changeIcon", "lastcn:" +last_cn.getClassName());
        ComponentName cn = new ComponentName(
                context,
                cls);
        Log.d("changeIcon", "cncncn:" + cn.getClassName());
        pm.setComponentEnabledSetting(cn,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
        MyService.saveConfig(context, cls);
    }
}
