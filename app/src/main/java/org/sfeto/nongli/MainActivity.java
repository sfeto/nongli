package org.sfeto.nongli;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.content.pm.PackageManager;
import android.content.ComponentName;
import android.widget.Toast;


public class MainActivity extends Activity {
    static final int CHANGE_ICON = 9;
    static final int CHANGE_TEXT = 99;
    static final int ICON_CHANGED=999;
    public static String TAG="nongli";
    boolean is_updating=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        android.util.Log.d(MainActivity.TAG, ""+Thread.currentThread().getStackTrace()[2].getMethodName());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                registerBroadcastReceiver();
            }
        }).run();

        new Thread(new Runnable() {
            @Override
            public void run() {
                startAlarm();
            }
        }).run();

        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.obtainMessage(MainActivity.CHANGE_ICON).sendToTarget();
            }
        }).run();


    }

    private void startAlarm() {
        android.util.Log.d(MainActivity.TAG, ""+Thread.currentThread().getStackTrace()[2].getMethodName());

        int hour = 00;
        int minute = 00;
        Alarmer.setAlarm(this, hour, minute);
    }

    public void start_onclick(View view){
        android.util.Log.d(MainActivity.TAG, ""+Thread.currentThread().getStackTrace()[2].getMethodName());
        startActivity(new Intent(this, HomeActivity.class));
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            android.util.Log.d(MainActivity.TAG, ""+Thread.currentThread().getStackTrace()[2].getMethodName());

            switch(msg.what){
                case ICON_CHANGED:
                    is_updating=false;
                    Toast.makeText(getApplicationContext(),
                        getString(R.string.updated_img),
                        Toast.LENGTH_LONG).show();
                    finish();
                    break;
                case CHANGE_TEXT:
                    android.util.Log.d(MainActivity.TAG, "handleMessage CHANGE_TEXT");
                    Button btn_change = (Button) findViewById(R.id.change);
                    String refreshing=(String)msg.obj;
                    btn_change.setText(refreshing);
                    //break; fall to CHANGE_ICON
                case CHANGE_ICON:
                    String last_class = MyService.readConfig(getApplicationContext());
                    String className = last_class.substring(last_class.lastIndexOf(".")+1).toLowerCase();
                    int res_id = getResources().getIdentifier(className, "drawable", getPackageName());
                    if (res_id != 0) {
                        ImageView img_nongli = (ImageView) findViewById(R.id.img_nongli);
                        img_nongli.setImageResource(res_id);
                    }
                    break;
            }
        }
    };

    Handler getHandler(){
        return handler;
    }
    boolean launcherIconUpdate(){
        Context context=getApplicationContext();
        PackageManager pm = context.getPackageManager();
        String cls = MyService.getFullClassName(context);
        android.util.Log.d(MainActivity.TAG, "cls:"+cls);
        ComponentName cn = new ComponentName(context, cls);
        int state=pm.getComponentEnabledSetting(cn);
        if(state==PackageManager.COMPONENT_ENABLED_STATE_ENABLED){
            return true;
        }
        return false;
    }

    public void change_onclick(View view){
        android.util.Log.d(MainActivity.TAG, ""+Thread.currentThread().getStackTrace()[2].getMethodName());
        startAlarm();
        new Thread(new Runnable() {
            int count=3;
            String[] dots=new String[]{".","..","..."};
            String getDots(int count){
                return dots[count%3];
            }

            @Override
            public void run() {
                            android.util.Log.d(MainActivity.TAG, ""+Thread.currentThread().getStackTrace()[1].getMethodName());
                    while(is_updating){
                        String text=String.format("%s%s", 
                            getString(R.string.updating_img), getDots(count));
                        android.util.Log.d(MainActivity.TAG, "text:"+text);
                        Message msg=handler.obtainMessage(CHANGE_TEXT, text);
                        handler.sendMessage(msg);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(launcherIconUpdate()){
                            break;
                        }
                        count++;
                    }
                    handler.sendEmptyMessage(ICON_CHANGED);
            }
        }).start();
    }

    private void registerBroadcastReceiver()
    {
android.util.Log.d(MainActivity.TAG, ""+Thread.currentThread().getStackTrace()[2].getMethodName());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction("com.android.calendar.date_changed");
        intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
        intentFilter.addAction(Intent.ACTION_TIME_CHANGED);
        intentFilter.addAction(Intent.ACTION_LOCALE_CHANGED);
        intentFilter.addAction(Intent.ACTION_DATE_CHANGED);
        Context context = getApplicationContext();
        DateChangedReceiver receiver = new DateChangedReceiver();
        context.registerReceiver(receiver, intentFilter);
    }


}
