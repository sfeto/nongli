package org.sfeto.nongli;

/**
 * Created by qinghao on 2016/9/5.
 */

import com.cnblogs.hongten.ChinaDate;

import java.util.Calendar;
import java.util.Locale;

public class ChinaDateWrapper {
    private int year;
    private int month;
    private int day;
    static String[] ChineseMonthes = new String[] { "", "正", "二", "三", "四",
            "五", "六", "七", "八", "九", "十", "冬", "腊" };

    public ChinaDateWrapper(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public ChinaDateWrapper() {

    }

    public String getLunarStr() {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month-1, day);
        String lunar_str = new Lunar(cal).toString();
        int year_pos = lunar_str.lastIndexOf("闰");
        if (year_pos != -1)
            return lunar_str.substring(year_pos+1);
        return lunar_str;

    }

    public String getChinaDate(int day) {
        return Lunar.getChinaDayString(day);
    }
}
