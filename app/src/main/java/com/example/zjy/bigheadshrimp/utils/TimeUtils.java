package com.example.zjy.bigheadshrimp.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 仕囵弹 on 2018/11/14.
 * 时间转化工具
 */

@SuppressLint("SimpleDateFormat")
public class TimeUtils {

    public static String long2string(String format, long time) {
        return new SimpleDateFormat(format).format(new Date(time));
    }

    public static String datestring(String format, Date date){
        return new SimpleDateFormat(format).format(date);
    }

    public static long string2long(String format, String time){
        try {
            return new SimpleDateFormat(format).parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static long date2long(Date date){
        return date.getTime();
    }

    public static Date long2date(long time){
        return new Date(time);
    }

    public static Date string2date(String format, String time){
        try {
            return new SimpleDateFormat(format).parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

}
