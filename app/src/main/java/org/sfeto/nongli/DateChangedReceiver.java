package org.sfeto.nongli;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


/**
 * Created by qinghao on 16-11-15.
 */

public class DateChangedReceiver extends BroadcastReceiver {

    private static final String TAG = DateChangedReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, ""+intent.getAction());
        context.startService(new Intent(context, MyService.class));
        Alarmer.setNextAlarm(context);
    }
}
