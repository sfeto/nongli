package org.sfeto.nongli;

import com.cnblogs.hongten.ChinaDate;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

/*
 * Created by qinghao on 2016/9/5.
 */

public class ChinaDateTest {

    private String getString(int year, int month, int day) {
        return new ChinaDateWrapper(year, month, day).getLunarStr();
    }

    @Test
    public void TodayTest() {
        assertEquals("八月初五", getString(2016, 9, 5));
    }
    @Test
    public void OneDayTest() {
        assertEquals("八月初六", getString(2016, 9, 6));
    }
    @Test
    public void yearOffsetTest(){
        long[] lunarInfo = new long[] {
                0x04bd8, 0x04ae0, 0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2,//1910
                0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2, 0x095b0, 0x14977,//1920
                0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970,//1930
                0x06566, 0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7, 0x0c950,//1940
                0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557,//1950
                0x06ca0, 0x0b550, 0x15355, 0x04da0, 0x0a5d0, 0x14573, 0x052d0, 0x0a9a8, 0x0e950, 0x06aa0,//1960
                0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263, 0x0d950, 0x05b57, 0x056a0,//1970
                0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0, 0x195a6,//1980
                0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46, 0x0ab60, 0x09570,//1990
                0x04af5, 0x04970, 0x064b0, 0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0,//2000
                0x0c960, 0x0d954, 0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0, 0x0cab5,//2010
                0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930,//2020
                0x07954, 0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530,//2030
                0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45,//2040
                0x0b5a0, 0x056d0, 0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0 //2050
        };
        int y = 1989;
        assertEquals(0x09570, lunarInfo[y - 1900]);
    }

 /*   @Test
    public void WrongDayTest() {
        assertEquals("七月廿二", getString(1989, 8, 23));
    }*/

    @Test
    public void WrongDay1Test() {
        assertEquals("正月初一", getString(1989, 2, 6));
        assertEquals("正月三十", getString(1989, 3, 7));
    }

    @Test
    public void WrongDay2Test() {
        assertEquals("二月初一", getString(1989, 3, 8));
        assertEquals("二月廿九", getString(1989, 4, 5));
    }

    @Test
    public void WrongDay3Test() {
        assertEquals("三月初一", getString(1989, 4, 6));
        assertEquals("三月初二", getString(1989, 4, 7));
        assertEquals("三月初八", getString(1989, 4, 13));
        assertEquals("三月初九", getString(1989, 4, 14));
        assertEquals("三月初十", getString(1989, 4, 15));
        assertEquals("三月十一", getString(1989, 4, 16));
/*        assertEquals("三月十二", getString(1989, 4, 17));
        assertEquals("三月十五", getString(1989, 4, 20));

        assertEquals("三月廿八", getString(1989, 5, 3));
        assertEquals("三月廿九", getString(1989, 5, 4));*/
    }
    @Test
    public void getChinaDateTest(){
        assertEquals("初一", ChinaDate.getChinaDate(1));
        String[] ch_number = new String[]{
                "",
                "一", "二", "三",
                "四", "五", "六",
                "七", "八", "九"
        };
        for (int i = 1; i <= 9; i+=1) {
            assertEquals("初" + ch_number[i], ChinaDate.getChinaDate(i));
        }
        int base = 10;
        assertEquals("初十", ChinaDate.getChinaDate(base));
        for (int i = 1; i <= 9; i+=1) {
            assertEquals("十" + ch_number[i], ChinaDate.getChinaDate(base+i));
        }

        base = 20;
        assertEquals("二十", ChinaDate.getChinaDate(base));
        for (int i = 1; i <= 9; i+=1) {
            assertEquals("廿" + ch_number[i], ChinaDate.getChinaDate(base+i));
        }
        base = 30;
        assertEquals("三十", ChinaDate.getChinaDate(base));
    }

/*    @Test
    public void WrongDay4Test() {
        assertEquals("四月初一", getString(1989, 5, 5));
    }*/

    //TODO: single ton

}
