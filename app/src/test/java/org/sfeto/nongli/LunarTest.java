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

public class LunarTest {

    public static String getString(int year, int month, int day) {
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
    public void WrongDayTest() {
        assertEquals("七月廿二", getString(1989, 8, 23));
    }

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
        assertEquals("三月十二", getString(1989, 4, 17));
        assertEquals("三月十五", getString(1989, 4, 20));

        assertEquals("三月廿八", getString(1989, 5, 3));
        assertEquals("三月廿九", getString(1989, 5, 4));
    }
    @Test
    public void getChinaDateTest(){
        ChinaDateWrapper cdw = new ChinaDateWrapper();
        assertEquals("初一", cdw.getChinaDate(1));
        String[] ch_number = new String[]{
                "",
                "一", "二", "三",
                "四", "五", "六",
                "七", "八", "九"
        };
        for (int i = 1; i <= 9; i+=1) {
            assertEquals("初" + ch_number[i], cdw.getChinaDate(i));
        }
        int base = 10;
        assertEquals("初十", cdw.getChinaDate(base));
        for (int i = 1; i <= 9; i+=1) {
            assertEquals("十" + ch_number[i], cdw.getChinaDate(base+i));
        }

        base = 20;
        assertEquals("二十", cdw.getChinaDate(base));
        for (int i = 1; i <= 9; i+=1) {
            assertEquals("廿" + ch_number[i], cdw.getChinaDate(base+i));
        }
        base = 30;
        assertEquals("三十", cdw.getChinaDate(base));
    }

    @Test
    public void WrongDay4Test() {
        assertEquals("四月初一", getString(1989, 5, 5));
        assertNotEquals(getString(1988, 4, 10), getString(1988, 4, 11));
    }

    @Test
    public void Wrong20080101Test() {
        assertEquals("冬月廿三", getString(2008, 1, 1));
    }

    @Test
    public void Wrong20080108Test() {
        assertEquals("腊月初一", getString(2008, 1, 8));
    }

    @Test
    public void Wrong20080206Test() {
        assertEquals("腊月三十", getString(2008, 2, 6));
    }

    @Test
    public void Wrong20080207Test() {
        assertEquals("正月初一", getString(2008, 2, 7));
    }

    //TODO: single ton

}
