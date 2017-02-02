package org.sfeto.nongli;

import com.cnblogs.liuSiyuan.Lunar;

import org.junit.Test;

import java.security.InvalidParameterException;
import java.util.Calendar;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/*
 * Created by qinghao on 2017/02/02.
 */

public class JieqiTest {
    @Test
    public void test_getJieqiInfo() throws Exception {
        int[] expect = {3, 18}; //2017
        int[] jqjqjq = Jieqi.getJieqiInfo(2017, 2);
        for (int i = 0; i < 2; i += 1) {
            assertEquals(expect[i], jqjqjq[i]);
        }
    }

    @Test
    public void test_getJieqiInfo_instance() throws Exception {
        int[] expect = {4,20}; //2017
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, Jieqi.month11(4));
        Jieqi jqobj = new Jieqi(calendar);
        int[] jqjqjq = jqobj.getJieqiInfo();
        for (int i = 0; i < 2; i += 1) {
            assertEquals(expect[1], jqjqjq[1]);
        }
    }

    @Test
    public void test_Jieqi_constructor() throws Exception {
        int[] expect = {3, 18}; //2017
        Jieqi jqobj = new Jieqi(2017,2);
        int[] jqjqjq = jqobj.getJieqiInfo();
        for (int i = 0; i < 2; i += 1) {
            assertEquals(expect[i], jqjqjq[i]);
        }
    }

    @Test
    public void test_getJieqiInfo2018() throws Exception {
        int[] expect = {5, 21}; //2018, 3
        int[] jqjqjq = Jieqi.getJieqiInfo(2018, 3);
        for (int i = 0; i < 2; i += 1) {
            assertEquals(expect[i], jqjqjq[i]);
        }
    }

    @Test
    public void test_getJieqiInfo2018_instance() throws Exception {
        int[] expect = {5, 21}; //2018, 3
        int[] jqjqjq = new Jieqi(2018, 3).getJieqiInfo();
        for (int i = 0; i < 2; i += 1) {
            assertEquals(expect[i], jqjqjq[i]);
        }
    }

    @Test
    public void test_InvalidParameterException_Before_BaseYear() throws Exception {
        try {
            int[] jqjqjq = Jieqi.getJieqiInfo(Jieqi.BASE_YEAR - 10, 2 - 1);
        } catch (Exception ipe) {
            assertTrue(ipe instanceof InvalidParameterException);
            assertTrue(ipe.getMessage().startsWith("No jieqi data for year before "));
        }
    }

    @Test
    public void test_InvalidParameterException_Before_BaseYear_instance() throws Exception {
        try {
            int[] jqjqjq = new Jieqi(Jieqi.BASE_YEAR - 10, 2 - 1).getJieqiInfo();
        } catch (Exception ipe) {
            assertTrue(ipe instanceof InvalidParameterException);
            assertTrue(ipe.getMessage().startsWith("No jieqi data for year before "));
        }
    }

    @Test
    public void test_InvalidParameterException_After_BaseYear() throws Exception {
        try {
            int[] jqjqjq = Jieqi.getJieqiInfo(Jieqi.BASE_YEAR + 10, 2 - 1);
        } catch (Exception ipe) {
            assertTrue(ipe instanceof InvalidParameterException);
            assertTrue(ipe.getMessage().startsWith("Only support up to"));
        }
    }

    @Test
    public void test_InvalidParameterException_After_BaseYear_instance() throws Exception {
        try {
            int[] jqjqjq = new Jieqi(Jieqi.BASE_YEAR + 10, 2 - 1).getJieqiInfo();
        } catch (Exception ipe) {
            assertTrue(ipe instanceof InvalidParameterException);
            assertTrue(ipe.getMessage().startsWith("Only support up to"));
        }
    }

    @Test
    public void test_getJieqiName_2017_立春() throws Exception {
        assertEquals("立春", Jieqi.getJieqiName(2017, 2, 3));
    }

    @Test
    public void test_getJieqiName_2017_立春_instance() throws Exception {
        assertEquals("立春", new Jieqi(2017, 2, 3).getJieqiName());
    }


    @Test
    public void test_getJieqiName_2017_雨水() throws Exception {
        assertEquals("雨水", Jieqi.getJieqiName(2017, 2, 18));
    }

    @Test
    public void test_getJieqiName_2017_雨水_instance() throws Exception {
        assertEquals("雨水", new Jieqi(2017, 2, 18).getJieqiName());
    }

    @Test
    public void test_getJieqiName_2018_冬至() throws Exception {
        assertEquals("冬至", Jieqi.getJieqiName(2018, 12, 22));
    }

    @Test
    public void test_getJieqiName_2018_冬至_instance() throws Exception {
        assertEquals("冬至", new Jieqi(2018, 12, 22).getJieqiName());
    }

    @Test
    public void test_getJieqiName_2017_1_1() throws Exception {
        assertEquals("", Jieqi.getJieqiName(2017, 1, 1));
    }

    @Test
    public void test_getJieqiName_2017_12_31() throws Exception {
        assertEquals("", Jieqi.getJieqiName(2017, 12, 31));
    }

    @Test
    public void test_getJieqiClassName() throws Exception{
        assertEquals("Jieqi02aa", Jieqi.getJieqiClassName(2017, 2, 3));
    }

    @Test
    public void test_getJieqiClassName_noyear() throws Exception{
        assertEquals("Jieqi02aa", Jieqi.getJieqiClassName(2, 3));
    }

    @Test
    public void test_isJieqiDay() throws Exception{
        for(int year=2017;year<=2018; year+=1) {
            for (int m = 1; m <= 12; m += 1) {
                int[] jqinfo = Jieqi.getJieqiInfo(year, m);
                for(int jd_day : jqinfo){
                    assertTrue(Jieqi.isJieqiDay(year, m, jd_day));
                }

            }
        }
    }

    @Test
    public void test_isJieqiDay_2017_2_3() throws Exception{
        int year=2017, m=2, jd_day=3;
        assertTrue(Jieqi.isJieqiDay(year, m, jd_day));
    }

    @Test
    public void test_getJieqiClassName_雨水() throws Exception{
        assertEquals("Jieqi02bb", Jieqi.getJieqiClassName(2017, 2, 18));
    }


}