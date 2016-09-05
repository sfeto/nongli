package org.sfeto.nongli;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private String getString(int year, int month, int day) {
        return new ChinaDateWrapper(year, month, day).getLunarStr();
    }

    @Test
    public void WrongDay3TestOK() throws Exception {
        assertEquals("三月十一", getString(1989, 4, 16));
    }

    @Test
    public void WrongDay3Test() throws Exception {
        assertEquals("三月十二", getString(1989, 4, 17));
    }
}