package org.sfeto.nongli;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class DateChangedReceiver extends BroadcastReceiver{

	private static final String TAG = HomeActivity.TAG;

	@Override
	public void onReceive(Context paramContext, Intent paramIntent) {
		try {
			Log.d(TAG, "org.sfeto.nongli.DateChangedReceiver.onReceive(Context, Intent)");
			Intent intent = new Intent(paramContext, HomeActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			paramContext.startActivity(intent);
		} catch (Exception e) {
			Toast.makeText(
					paramContext,
					"There was an error somewhere, but we still received an alarm",
					Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
	}

}
