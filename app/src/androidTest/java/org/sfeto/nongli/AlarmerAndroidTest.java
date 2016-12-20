package org.sfeto.nongli;

import android.app.AlarmManager;

import org.junit.Test;

import static java.lang.Math.abs;
import static java.lang.Math.floor;
import static java.lang.Math.random;
import static org.junit.Assert.*;

/**
 * Created by qinghao on 16-12-21.
 */
public class AlarmerAndroidTest {
    @Test
    public void setAlarm() throws Exception {

    }

    @Test
    public void test_getMilis_not_change_with_time() throws Exception {
        long a=Alarmer.getMilis(0,0,0);
        long b=Alarmer.getMilis(0,0,0);
        assertEquals(a,b);
    }

    @Test
    public void test_getNextDay() throws Exception {
        long a=Alarmer.getMilis(0,0,0);
        long b=Alarmer.getNextDay();
        assertEquals(abs(a-b),24*3600*1000);
    }

    @Test
    public void test_AlarmManager_INTERVAL_DAY() throws Exception {
        long a= (long) floor(random()*10000);
        long b=a+AlarmManager.INTERVAL_DAY;
        assertEquals(abs(a-b),24*3600*1000);
    }

    @Test
    public void setAlarm1() throws Exception {

    }

}