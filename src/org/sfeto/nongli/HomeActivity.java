package org.sfeto.nongli;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

public class HomeActivity extends Activity
{
    /** Called when the activity is first created. */
    private Lunar l;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
         l = new Lunar(Calendar.getInstance());
        new Thread(new Runnable(){
			@Override
			public void run() {

				((TextView)findViewById(R.id.hello)).setText("我去今天是农历:" + l.get_month() + "月" + l.get_date());
			}
        	
        }).start();
        addShortcutToDesktop();
    }
    void addShortcutToDesktop(){
		Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
		BitmapDrawable iconBitmapDrawabel = null;
		String label = this.getPackageName();
		PackageManager packageManager = getPackageManager();
		try {
			iconBitmapDrawabel = (BitmapDrawable) packageManager.getApplicationIcon(label);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, label);
                Bitmap tempBitmap=drawTextBitmap(iconBitmapDrawabel.getBitmap(),"" + l.get_month() + "月\n" + l.get_date());
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON, tempBitmap);
		shortcut.putExtra("duplicate", false);
		ComponentName comp = new ComponentName(label,"." + this.getLocalClassName());
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(Intent.ACTION_MAIN).setComponent(comp));
		sendBroadcast(shortcut);
    }
    public Bitmap drawTextBitmap(Bitmap gbitmap,String gText){
            Resources resources = this.getResources();
            float scale = resources.getDisplayMetrics().density;
	    Bitmap bitmap = scaleWithWH(gbitmap, 300*scale, 300*scale);
	    android.graphics.Bitmap.Config bitmapConfig =
			      bitmap.getConfig();
	    if(bitmapConfig == null) {
			 bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
	    }
	    bitmap = bitmap.copy(bitmapConfig, true);
	    Canvas canvas = new Canvas(bitmap);
	    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	    paint.setColor(Color.RED);
	    paint.setTextSize((int) (80 * scale));
            paint.setDither(true);
	    paint.setFilterBitmap(true);
	    Rect bounds = new Rect();
	    paint.getTextBounds(gText, 0, gText.length(), bounds);
	    int x = 30;
            int y = 30;
            canvas.drawText(gText, x * scale, y * scale, paint);
	    return bitmap;
    }
    public static Bitmap scaleWithWH(Bitmap src, double w, double h) {
        if (w == 0 || h == 0 || src == null) {
            return src;
        } else {
        int width = src.getWidth();
        int height = src.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = (float) (w / width);
        float scaleHeight = (float) (h / height);
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(src, 0, 0, width, height, matrix, true);
        }
    }
}
