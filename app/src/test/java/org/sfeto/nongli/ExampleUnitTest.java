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
        return LunarTest.getString(year, month, day);
    }

    @Test
    public void exampleTest() {
        assertEquals(1 , 1);
    }
}