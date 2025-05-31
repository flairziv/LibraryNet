package com.library.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


// 公共的时间转换类
public class DateUtils {
    static SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 可以方便修改日期格式

    public DateUtils() throws ParseException {
        String newTime = sd.format(new Date());// 获取当前时间
        System.out.println("输出当前时间:" + newTime);
        Date date = sd.parse(newTime);// 当前时间转换为时间戳
        // parse() 解析字符串的文本，生成 Date
        long unixTimestamp = date.getTime() / 1000;// 将时间戳转换为秒
        System.out.println("输出时间戳:" + unixTimestamp);
        String nTime = sd.format(unixTimestamp * 1000);// 时间戳转换为当前时间
        System.out.println("输出当前时间[时间戳转换]:" + nTime);// 输出当前时间
    }

    /**
     * 获取当前时间方法
     */
    public static String getNewTime() {
        String newTime = sd.format(new Date());// 获取当前时间
        return newTime;
    }

    /**
     * 获取当前时间戳方法
     */
    public static int getNewStamp() {
        int newTimeStamp = (int) (new Date().getTime()/1000);
        return newTimeStamp;
    }

    /**
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String time) throws ParseException {
        String newTime;
        Date date = sd.parse(time);
        int ts = (int) (date.getTime()/1000);
        newTime = String.valueOf(ts);
        return newTime;
    }

    /**
     * 将时间戳转换为时间
     */
    public static String stampToDate(String stamp) {
        String newStamp;
        // 使用 Long.parseLong() 将字符串转换为 long
        long lt = Long.parseLong(stamp) * 1000;  // 将秒转换为毫秒
        Date date = new Date(lt);  // 使用转换后的毫秒数创建日期对象
        newStamp = sd.format(date);  // 格式化日期为字符串
        return newStamp;
    }


    /**
     * 将MySQL中的TimeStamp类型转换为String
     */
    public static String stampToDate(Object stamp) {
        String timeStr = sd.format(stamp);//将一个 Date 格式化为日期/时间字符串
        return timeStr;
    }
}
