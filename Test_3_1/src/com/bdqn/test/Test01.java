package com.bdqn.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test01 {
/**
 * @author liuziyang
 * @data 2024-03-01-14:56
 */
public static void main(String[] args) {
//    //分配 Date 对象，并初始化此对象，以表示本地时区中由 year、month、date、hrs、min 和 sec 参数指定的秒的开始瞬间。
//    //参数：
//    //year - 减 1900 的年份。
//    //month - 0-11 之间的月份。
//    //date - 一月中 1-31 之间的某一天。
//    //hrs - 0-23 之间的小时数。
//    //min - 0-59 之间的分钟数。
//    //sec - 0-59 之间的秒数。
//    java.util.Date utilDate = new Date(100,5,3);
//    System.out.println(utilDate);
//    System.out.println(utilDate.getDate());
//    System.out.println(utilDate.getTime());
//    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//    System.out.println(sqlDate);
    SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
    String date = "1953-19-16";
    try {
        Date date1 =simpleDateFormat.parse(date);
        System.out.println(date1);
        System.out.println(date1.getTime());
        System.out.println(simpleDateFormat.format(date1));
        java.sql.Date date2 = new java.sql.Date(date1.getTime());
        System.out.println(date2);
    } catch (ParseException e) {
        throw new RuntimeException(e);
    }
}
}
