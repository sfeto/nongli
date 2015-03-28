package org.sfeto.nongli;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Lunar l = new Lunar(Calendar.getInstance());
        new Thread(new Runnable(){
			@Override
			public void run() {
				Lunar l = new Lunar(Calendar.getInstance());
				((TextView)findViewById(R.id.hello)).setText("我去今天是农历:" + l.get_month() + "月" + l.get_date());
			}
        	
        }).start();
    }
}
