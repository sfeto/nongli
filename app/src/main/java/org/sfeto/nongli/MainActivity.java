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


public class MainActivity extends Activity {
    static final int CHANGE_ICON = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        int hour = 00;
        int minute = 00;
        Alarmer.setAlarm(this, hour, minute);
    }

    public void start_onclick(View view){
        startActivity(new Intent(this, HomeActivity.class));
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
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
    public void change_onclick(View view){
        startAlarm();
        long fiveSeconds= 9*1000;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, fiveSeconds);
    }

    private void registerBroadcastReceiver()
    {
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
