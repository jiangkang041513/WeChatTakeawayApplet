package com.swpu.uchain.takeawayapplet.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName TimeUtil
 * @Author hobo
 * @Date 19-3-2 下午7:01
 * @Description 时间格式化工具
 **/
public class TimeUtil {
    /**
     * 获得当前的时间 格式为 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getNowTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 获得当前的时间 格式为：yyyy年yy月yy日 HH:mm:ss
     *
     * @return
     */
    public static String getTimeCN() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        return format.format(date);
    }

    /**
     * 将时间格式转换为微信支付所需格式
     *
     * @return java.lang.String
     * @author hobo
     */
    public static String getWxpayTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(date);
    }

    public static String stampToTime(long stamp) {
        String time;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(stamp * 1000);
        time = simpleDateFormat.format(date);
        return time;

    }

    /**
     * 当前时间 +1天
     * @param stamp
     * @return
     */
    public static String addCurrentTime(long stamp) {
        String time;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(stamp * 1000);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, 1);
        date = c.getTime();
        return format.format(date);
    }
}
