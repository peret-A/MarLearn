package com.bdqn.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {
/**
 * @author liuziyang
 * @data 2024-03-01-14:46
 */
private static final SimpleDateFormat SIMPLEDATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");

    // 字符串转换为java.util.Date类型日期时间
    public static java.util.Date strDateToUtilDate(String strDate) {
        try {
            return SIMPLEDATEFORMAT.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    // java.util.Date类型日期时间转换为java.sql.Date类型日期时间
    public static java.sql.Date utilDateToSqlDate(java.util.Date date) {
        // long date.getTime():返回自 1970 年 1 月 1 日 00:00:00 GMT以来此 Date对象表示的毫秒数
        return new java.sql.Date(date.getTime());
    }

    // java.util.Date类转换为字符串类型
    public static String utilDateToString(java.util.Date date) {
        return SIMPLEDATEFORMAT.format(date);
    }

}
