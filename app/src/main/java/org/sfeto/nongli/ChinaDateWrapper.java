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

    public String getLunarStr() {
        String lunar_str = ChinaDate.oneDay(year, month, day);
        int year_pos = lunar_str.lastIndexOf("年");
        return lunar_str.substring(year_pos+1);

        //long[] intluna = ChinaDate.calElement(2016, 9, 5);
    }
}
