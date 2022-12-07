package com.ddup.study.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author: hwj
 * @Description TODO
 * @create: 2022/1/12 16:27
 */
public class IdGenerationUtil {
    private static Map<Class, String> prefix = new HashMap<>();
    private static DateFormat format = new SimpleDateFormat("yyyyMMdd");
    private static Random random = new Random(47);

    static {
    }

    public static String getEntityId(Class clazz, int len) {
        int rs = (int) ((Math.random() * 9 + 1) * Math.pow(10, len - 1));
        return prefix.get(clazz) + format.format(new Date()) + rs;
    }

    public static String getEntityId(Class clazz) {
        return getEntityId(clazz, 5);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
        }
    }
}
