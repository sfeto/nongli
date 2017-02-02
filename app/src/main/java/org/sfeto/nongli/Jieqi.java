package org.sfeto.nongli;

import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Jieqi{
    private static String[][] jieqiName = {
            {"小寒", "大寒"},//01
            {"立春", "雨水"},//02
            {"惊蛰", "春分"},//03
            {"清明", "谷雨"},//04
            {"立夏", "小满"},//05
            {"芒种", "夏至"},//06
            {"小暑", "大暑"},//07
            {"立秋", "处暑"},//08
            {"白露", "秋分"},//09
            {"寒露", "霜降"},//10
            {"立冬", "小雪"},//11
            {"大雪", "冬至"} //12
    }; 
	public static int[][] jieqiInfo={
            /*01    02    03    04    05    06    07    08    09    10    11    12*/
            {5,20, 3,18, 5,20, 4,20, 5,21, 5,21, 7,22, 7,23, 7,23, 8,23, 7,22, 7,22}, //2017
            {5,20, 4,19, 5,21, 5,20, 5,21, 6,21, 7,23, 7,23, 8,23, 8,23, 7,22, 7,22}, //2018
    }; 

    public static int BASE_YEAR=2017;
    private final Calendar calender;
    //Calendar month is 0-11
    public static int month11(int m) {
        return m-1;
    }

    public static int month12(int m) {
        return m+1;
    }

    public Jieqi(Calendar calendar) {
        this.calender=calendar;
    }

    public Jieqi(int year, int month) {
        this(year, month, 15);
    }

    public Jieqi(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        month=month11(month);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        this.calender=calendar;
    }

    public int[] getJieqiInfo() throws Exception{
        return Jieqi.getJieqiInfo(calender.get(Calendar.YEAR),
                month12(calender.get(Calendar.MONTH)));
    }
    public static int[] getJieqiInfo(int year, int month) throws Exception{
        //0xA5, 0xC3, 0xA5, 0xB5, 0xA6, 0xA6, 0x87, 0x88, 0x88, 0x78, 0x87, 0x87,   //2017

        if(year<BASE_YEAR)
            throw new InvalidParameterException("No jieqi data for year before "+BASE_YEAR);
        else if (year>BASE_YEAR+jieqiInfo.length-1)
            throw new InvalidParameterException(
                    String.format("Only support up to %d, but you gives %d.",
                            (BASE_YEAR+jieqiInfo.length-1),year));
        int y_off=year-BASE_YEAR;
        int[] tmpyear=jieqiInfo[y_off];
        month=month11(month);
        int[] jieqi={tmpyear[2*month], tmpyear[2*month+1]};
        return jieqi;

    }

    public String getJieqiName() throws Exception{
        return Jieqi.getJieqiName(calender.get(Calendar.YEAR),
                month12(calender.get(Calendar.MONTH)),
                calender.get(Calendar.DAY_OF_MONTH));
    }

    public static boolean isJieqiDay(int year, int month, int day) throws Exception{
        int[] jqjqjq = Jieqi.getJieqiInfo(year, month);
        for(int jq :jqjqjq) {
            if (jq == day) {
                return true;
            }
        }
        return false;
    }
    public static String getJieqiName(int year, int month, int day) throws Exception{
        if (isJieqiDay(year,month,day)){
            int ab=day>15?1:0;
            return jieqiName[month11(month)][ab];
        }
        return "";
    }

    public static String getJieqiName(int month, int day) throws Exception{
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return getJieqiName(year, month, day);
    }
    public static String getJieqiClassName(int year, int month, int day) throws Exception{
        android.util.Log.d(MainActivity.TAG,
                String.format("getJieqiClassName y:%d,m:%d,d:%d", year,month,day));
        if (isJieqiDay(year,month,day)){
            String ab=day<15?"aa":"bb";
            return String.format("Jieqi%02d%s",
                    month, ab);
        }
        return "";
    }
    public static String getJieqiClassName(int month, int day) throws Exception{
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return getJieqiClassName(year, month, day);
    }

    public String getJieqiClassName() throws Exception{
        return Jieqi.getJieqiClassName(
                calender.get(Calendar.YEAR),
                month12(calender.get(Calendar.MONTH)),
                calender.get(Calendar.DAY_OF_MONTH));
    }
}