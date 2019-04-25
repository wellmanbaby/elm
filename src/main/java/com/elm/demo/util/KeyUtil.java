package com.elm.demo.util;

import java.util.Random;

/*
* 数据库索引、约束的工具类
* */
public class KeyUtil {
    /*
    * 生成唯一的主键
    * 格式：时间+随机数
    * */
    //防止高并发加synchronized,以后学
    public static synchronized String genUniqueKey(){

        Random random = new Random();

        //生成6位随机数
        Integer number = random.nextInt(900000) + 100000;

        //当前时间（精确到毫秒）
        return System.currentTimeMillis()+String.valueOf(number);
    }
}
