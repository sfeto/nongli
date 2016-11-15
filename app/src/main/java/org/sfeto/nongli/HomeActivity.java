package org.sfeto.nongli;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Calendar;
import net.csdn.lanhy999.Text2Image;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void updateImg_onclick(View view){
        setTitle("updateImg_onclick");
        ImageView img_nongli = (ImageView) findViewById(R.id.img_nongli);
        String luna_today = getTodayLuar();
        int iconSize = getIconSize();
        iconSize *= 3;
        img_nongli.setImageBitmap(Text2Image.getIcon(luna_today, iconSize));
    }

    private String getTodayLuar() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return new ChinaDateWrapper(year, month, day).getLunarStr();
    }

    public void resetImg_onclick(View view){
        setTitle("resetImg_onclick");
        ImageView img_nongli = (ImageView) findViewById(R.id.img_nongli);
        img_nongli.setImageDrawable(getResources().getDrawable(R.drawable.nongli_icon));
    }

    public void test_onclick(View view){
        setTitle("test_onclick");
        ImageView img_nongli = (ImageView) findViewById(R.id.img_nongli);
        int iconSize = getIconSize();
        iconSize *= 3;
        String luna_today = getTodayLuar();
        img_nongli.setImageBitmap(Text2Image.StringToBitmap(luna_today, iconSize));
    }

    private int getIconSize() {
        return ((ActivityManager)getSystemService(Context.ACTIVITY_SERVICE)).getLauncherLargeIconSize();
    }

    public void resize_onclick(View view){
        setTitle("resize_onclick");
        ImageView img_nongli = (ImageView) findViewById(R.id.img_nongli);
        ViewGroup.LayoutParams layoutParams = img_nongli.getLayoutParams();
        if(layoutParams.width != ViewGroup.LayoutParams.WRAP_CONTENT){
            layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else {
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        }
        img_nongli.setLayoutParams(layoutParams);
        img_nongli.requestLayout();
        //img_nongli.getParent().requestLayout();
    }

}
