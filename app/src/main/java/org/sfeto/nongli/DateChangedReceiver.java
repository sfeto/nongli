package org.sfeto.nongli;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by qinghao on 16-11-15.
 */

public class DateChangedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, MyService.class));
        Alarmer.nextDayAlarm(context);
    }
}
