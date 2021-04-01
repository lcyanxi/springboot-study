package com.lcyanxi.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author lichang
 * @date 2021/3/26
 */
public class DateUtil {


    /**
     * 获得当前时间之后指定分钟的时间
     * @return date
     */
    public static Date getNextMinutes(int minutes){
        //获得当前时间之后指定天数
        LocalDateTime tomorrow=LocalDateTime.now().plusMinutes(minutes);
        ZoneId zoneId=ZoneId.systemDefault();
        ZonedDateTime zonedDateTime=tomorrow.atZone(zoneId);
        return Date.from(zonedDateTime.toInstant());
    }

    public static void main(String[] args) {
        System.out.println(getNextMinutes(-5));
    }
}
