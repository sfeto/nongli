package org.sfeto.nongli;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "Nongli");
		Bitmap iconBitmap = iconBitmapDrawabel.getBitmap();
		Bitmap txtBitmap = GLFont.getImage(iconBitmap.getWidth(), iconBitmap.getHeight(), l.get_month() + "月" , l.get_date(), 56);
		Bitmap tempBitmap = toConformBitmap(iconBitmap,txtBitmap);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON, tempBitmap);
		shortcut.putExtra("duplicate", false);
		ComponentName comp = new ComponentName(label,"." + this.getLocalClassName());
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(Intent.ACTION_MAIN).setComponent(comp));
		sendBroadcast(shortcut);
    }

    private Bitmap toConformBitmap(Bitmap background, Bitmap foreground) {
        if( background == null ) {
           return null;
        }
        int bgWidth = background.getWidth();
        int bgHeight = background.getHeight();
        Bitmap newbmp = Bitmap.createBitmap(bgWidth, bgHeight, Config.ARGB_8888);
        Canvas cv = new Canvas(newbmp);
        cv.drawBitmap(foreground, 0f, 0f, null);//在 0，0坐标开始画入fg ，可以从任意位置画入
        cv.save(Canvas.ALL_SAVE_FLAG);//保存
        cv.restore();//存储
        return newbmp;

    }
}
