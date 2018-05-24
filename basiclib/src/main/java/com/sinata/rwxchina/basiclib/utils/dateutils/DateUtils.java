package com.sinata.rwxchina.basiclib.utils.dateutils;

import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author HRR
 * @datetime 2017/12/27
 * @describe 时间工具类
 * @modifyRecord
 */

public class DateUtils {
    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1, Date date2){
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }

    //  实现给定某日期，判断是星期几

    /**
     * 获取某天是周几
     * @param date
     * @return
     */
    public static String getWeekday(Date date){//必须yyyy-MM-dd
        String dates=(1900+date.getYear())+"-"+(date.getMonth()+1)+"-"+date.getDate();
        LogUtils.e("datautils","date="+dates);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdw = new SimpleDateFormat("E");
        Date d = null;
        try {
            d = sd.parse(dates);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdw.format(d);
    }

    /**
     * 获取后一天日期（月-日）
     * @return
     */
    public static Date getNextDay(){
        Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,1);//把日期往前减少一天，若想把日期向后推一天则将负数改为正数
        return calendar.getTime();
    }
}
