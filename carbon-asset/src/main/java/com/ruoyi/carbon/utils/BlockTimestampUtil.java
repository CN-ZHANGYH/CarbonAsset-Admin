package com.ruoyi.carbon.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间戳工具类
 */

public class BlockTimestampUtil {

    /**
     * 将毫秒级别的时间戳进行转换
     * @param timestamp 时间戳
     * @return 返回格式化好的时间
     */
    public static String convert(Long timestamp){
        long timestampInSecond = timestamp / 1000L;
        Date date = new Date(timestampInSecond * 1000L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
