package com.xinmy.springbootbase.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static Date start(Date now) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateFormat.format(now));
        } catch (ParseException e) {
            //
        }
        return null;
    }

    public static String date(Date date) {
        return date(date,"yyyy-MM-dd");
    }
    public static String date(Date date, String fmt) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(fmt);
        return dateFormat.format(date);
    }

    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Integer date2Int(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return Integer.valueOf(dateFormat.format(date));
    }

    /** 判断两个时间是否为同一天 */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        return DateUtils.isSameDay(date1, date2);
    }

}
