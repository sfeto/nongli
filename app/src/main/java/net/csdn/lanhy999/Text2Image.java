package net.csdn.lanhy999;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;

/**
 * Created by qinghao on 16-11-14.
 */
public class Text2Image {
    /**
     * http://blog.csdn.net/lanhy999/article/details/8492168
     */
    public static Bitmap textAsBitmap(String text, float textSize) {

        TextPaint textPaint = new TextPaint();

        // textPaint.setARGB(0x31, 0x31, 0x31, 0);
        textPaint.setColor(Color.BLACK);

        textPaint.setTextSize(textSize);

        StaticLayout layout = new StaticLayout(text, textPaint, 450,
                Layout.Alignment.ALIGN_NORMAL, 1.3f, 0.0f, true);
        Bitmap bitmap = Bitmap.createBitmap(layout.getWidth() + 20,
                layout.getHeight() + 20, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.translate(10, 10);
        canvas.drawColor(Color.WHITE);

        layout.draw(canvas);
        Log.d("textAsBitmap",
                String.format("1:%d %d", layout.getWidth(), layout.getHeight()));
        return bitmap;
    }

    public static Bitmap StringToBitmap(String str, int icon_size) {
        int r = icon_size / 10;
        int line_height = icon_size / 2;
        int font_size = (int)(line_height * 0.95);

        TextPaint textPaint = getTextPaint(font_size);

        StaticLayout layout = new StaticLayout(str, textPaint, icon_size,
                Layout.Alignment.ALIGN_CENTER, 0.83f, 0f, true);
        Bitmap bitmap = Bitmap.createBitmap(icon_size,
                icon_size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(textPaint.getFlags());
        paint.setColor(-22490);
        canvas.drawRoundRect(new RectF(0.0F, 0.0F, icon_size, icon_size), r, r, paint);
        canvas.translate((icon_size-layout.getWidth())/2, -r/2);
        layout.draw(canvas);
        return bitmap;
    }

    @NonNull
    private static TextPaint getTextPaint(int font_size) {
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(0xff101010);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(font_size);
        return textPaint;
    }

    public static Bitmap getIcon(String text, int size)
    {
        Log.d("Nongli", "nicon_size:"+size);
        int r = size / 10;
        int line_height = size / 2;
        int font_size = (int)(line_height * 0.95);
        text = text.substring(0,2) + "\n" + text.substring(2);
        Bitmap bitmap = getBitmap(text, size, r, line_height, font_size);

        return bitmap;
    }

    @NonNull
    private static Bitmap getBitmap(String text, int size, int r, int line_height, float font_size) {
        float x = r/2;
        float y = line_height-r/2;
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        Canvas canvas = new Canvas(bitmap);
        paint.setColor(-22490);
        canvas.drawRoundRect(new RectF(0.0F, 0.0F, size, size), r, r, paint);
        paint.setColor(0xff101010);
        paint.setTextSize(font_size);
        String[] lines = text.split("\n");
        for (int j = 0; j < lines.length; j++)
        {
            String s1 = lines[j];
            canvas.drawText(s1, x, y, paint);
            y += line_height - r/2 ;
        }
        return bitmap;
    }
}
