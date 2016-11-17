package org.sfeto.nongli;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import net.csdn.lanhy999.Text2Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
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
        int iconSize = getIconSize();
        iconSize *= 3;
        String luna_today = getTodayLuar();

        ImageView img_nongli = (ImageView) findViewById(R.id.img_nongli);
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

    /**
     * http://stormzhang.com/android/2014/07/24/android-save-image-to-gallery/
     * @param bmp
     * @param appDir
     * @param fileName
     * @return
     */
    public File saveImage(Bitmap bmp, File appDir, String fileName) {
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        File pngfile = new File(appDir, fileName);
        try {
            OutputStream os = new FileOutputStream(pngfile);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, os);
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pngfile;
    }

    public void save_onclick(View view){
        setTitle("save_onclick");
        File appDir = new File (getExternalFilesDir(null), "Aoohee");
        //appDir = new File("/data/local/tmp");
        //ImageView img_nongli = (ImageView) findViewById(R.id.img_nongli);
        //Bitmap bm = ((BitmapDrawable)img_nongli.getDrawable()).getBitmap();
        for(int m=1;m<=12;m+=1){
            for(int d=1;d<=30;d+=1){
                String luna_str = ChinaDateWrapper.ChineseMonthes[m-1] + "月" +
                        new ChinaDateWrapper().getChinaDate(d);
                Bitmap bm = Text2Image.StringToBitmap(luna_str, 144);
                String name = String.format("%02d%02d.png", m, d);
                File file = saveImage(bm, appDir, name);
                Log.d("save_onclick:", file.getAbsolutePath());
            }
        }


    }

    public void prevDay_onclick(View view){}

    public void nextDay_onclick(View view){
        Bitmap bm = Text2Image.StringToBitmap("今天农历", 144);
        File appDir = new File (getExternalFilesDir(null), "Aoohee");
        File file = saveImage(bm, appDir, "luna_today.png");
        Log.d("save_onclick:", file.getAbsolutePath());
    }
}
