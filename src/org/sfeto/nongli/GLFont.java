package org.sfeto.nongli;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.Paint.FontMetrics;
import android.text.TextPaint;

public class GLFont {
    /*
     * 默认采用白色字体，宋体文字加粗
     */
	public static Bitmap getImage(int width, int height ,String mStringM,String mStringD, int size) {
		return getImage(width, height, mStringM, mStringD, size, Color.WHITE, Typeface.create("宋体",Typeface.BOLD));
	}

	public static Bitmap getImage(int width, int height ,String mStringM, String mStringD,int size ,int color) {
		return getImage(width, height, mStringM, mStringD, size, color, Typeface.create("宋体",Typeface.BOLD));
	}

	public static Bitmap getImage(int width, int height ,String mStringM, String mStringD,int size ,int color, String familyName) {
		return getImage(width, height, mStringM,mStringD, size, color, Typeface.create(familyName,Typeface.BOLD));
	}

	public static Bitmap getImage(int width, int height ,String mStringM,String mStringD,int size, int color, Typeface font) {
		int x = width;
		int y = height;

		Bitmap bmp = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);
		//图象大小要根据文字大小算下,以和文本长度对应
		Canvas canvasTemp = new Canvas(bmp);

		Paint p1 = new Paint();
		p1.setColor(Color.BLACK);

		Paint p = new Paint();
		Rect rect = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
        RectF rectF = new RectF(rect);

		p.setColor(color);
		p.setTypeface(font);
		p.setAntiAlias(true);//去除锯齿
		p.setFilterBitmap(true);//对位图进行滤波处理
		p.setTextSize(scalaFonts(size));
		float tX1 = (x - getFontlength(p, mStringM))/2;
		float tX2 = (x - getFontlength(p, mStringD))/2;
		float tY = (y - getFontHeight(p))/2+getFontLeading(p)/2;
		canvasTemp.drawRoundRect(rectF, 20, 20, p1);
		p.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvasTemp.drawText(mStringM,tX1,tY,p);
		canvasTemp.drawText(mStringD,tX2,tY+getFontHeight(p),p);

		canvasTemp.drawBitmap(bmp, null, rect, p1);

		return bmp;
	}

	/**
	 * 根据屏幕系数比例获取文字大小
	 * @return
	 */
	static float scalaFonts(int size) {
		//暂未实现
		return size;
	}

	/**
	 * @return 返回指定笔和指定字符串的长度
	 */
	public static float getFontlength(Paint paint, String str) {
		return paint.measureText(str);
	}
	/**
	 * @return 返回指定笔的文字高度
	 */
	public static float getFontHeight(Paint paint)  {
	    FontMetrics fm = paint.getFontMetrics();
	    return fm.descent - fm.ascent;
	}
	/**
	 * @return 返回指定笔离文字顶部的基准距离
	 */
	public static float getFontLeading(Paint paint)  {
	    FontMetrics fm = paint.getFontMetrics();
	    return fm.leading- fm.ascent;
	}

}
