package com.library.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


// ������ʱ��ת����
public class DateUtils {
    static SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ���Է����޸����ڸ�ʽ

    public DateUtils() throws ParseException {
        String newTime = sd.format(new Date());// ��ȡ��ǰʱ��
        System.out.println("�����ǰʱ��:" + newTime);
        Date date = sd.parse(newTime);// ��ǰʱ��ת��Ϊʱ���
        // parse() �����ַ������ı������� Date
        long unixTimestamp = date.getTime() / 1000;// ��ʱ���ת��Ϊ��
        System.out.println("���ʱ���:" + unixTimestamp);
        String nTime = sd.format(unixTimestamp * 1000);// ʱ���ת��Ϊ��ǰʱ��
        System.out.println("�����ǰʱ��[ʱ���ת��]:" + nTime);// �����ǰʱ��
    }

    /**
     * ��ȡ��ǰʱ�䷽��
     */
    public static String getNewTime() {
        String newTime = sd.format(new Date());// ��ȡ��ǰʱ��
        return newTime;
    }

    /**
     * ��ȡ��ǰʱ�������
     */
    public static int getNewStamp() {
        int newTimeStamp = (int) (new Date().getTime()/1000);
        return newTimeStamp;
    }

    /**
     * ��ʱ��ת��Ϊʱ���
     */
    public static String dateToStamp(String time) throws ParseException {
        String newTime;
        Date date = sd.parse(time);
        int ts = (int) (date.getTime()/1000);
        newTime = String.valueOf(ts);
        return newTime;
    }

    /**
     * ��ʱ���ת��Ϊʱ��
     */
    public static String stampToDate(String stamp) {
        String newStamp;
        // ʹ�� Long.parseLong() ���ַ���ת��Ϊ long
        long lt = Long.parseLong(stamp) * 1000;  // ����ת��Ϊ����
        Date date = new Date(lt);  // ʹ��ת����ĺ������������ڶ���
        newStamp = sd.format(date);  // ��ʽ������Ϊ�ַ���
        return newStamp;
    }


    /**
     * ��MySQL�е�TimeStamp����ת��ΪString
     */
    public static String stampToDate(Object stamp) {
        String timeStr = sd.format(stamp);//��һ�� Date ��ʽ��Ϊ����/ʱ���ַ���
        return timeStr;
    }
}
