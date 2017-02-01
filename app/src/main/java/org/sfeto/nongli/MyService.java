package org.sfeto.nongli;

import android.app.IntentService;
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

public class MyService extends IntentService {
    public MyService() {
        super("MyService");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int month = intent.getIntExtra("month", 0);
        int day = intent.getIntExtra("day", 0);
        if(month != 0 && day != 0){
            changeIcon(month, day);
        } else {
            changeIcon();
        }
    }

    public static void saveConfig(Context context, String lastClassName){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        Log.d(MainActivity.TAG, "saveConfig"+lastClassName);
        editor.putString("lastClassName", lastClassName);
        editor.commit();
    }

    public static String readConfig(Context context){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        Log.d(MainActivity.TAG, "last_cn:" + sharedPref.getString("lastClassName", context.getPackageName()+".MainActivity"));
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

    public static String getFullClassName(Context context) {
        Lunar lunar = new Lunar(Calendar.getInstance());
        return context.getPackageName()+
                new MyService().getClassName(lunar.getMonth(), lunar.getDay());
    }

    public void changeIcon(String cls) {
        Context context = getBaseContext();
        String last_cls = MyService.readConfig(context);
        Log.d(MainActivity.TAG, "last_cls:" +last_cls);
        Log.d(MainActivity.TAG, "    _cls:" +cls);
        // if (last_cls.equals(cls)){
        //     Log.d(MainActivity.TAG, "last_cls.equals(cls)");
        //     return;
        // }
        ComponentName last_cn = new ComponentName(
                context,
                last_cls);
        PackageManager pm = getApplicationContext().getPackageManager();
        pm.setComponentEnabledSetting(last_cn,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
        Log.d(MainActivity.TAG, "lastcn:" +last_cn.getClassName());
        ComponentName cn = new ComponentName(
                context,
                cls);
        Log.d(MainActivity.TAG, "cncncn:" + cn.getClassName());
        pm.setComponentEnabledSetting(cn,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
        MyService.saveConfig(context, cls);
    }
}
