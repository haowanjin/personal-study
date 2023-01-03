package com.ddup.jvm;

import org.openjdk.jol.info.ClassLayout;

import java.lang.reflect.Field;

/**
 * @author haowanjin
 * @date 2022/12/29 20:31
 * @description
 */
public class JavaObjectLayoutTest {
    static JavaObjectLayoutTest t = new JavaObjectLayoutTest();

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String aa = "abc hello";
        String bb = "abc hello";
        String c = new String("abc hello");
        System.out.println(aa == bb);
        System.out.println(aa == c);
        Field value = String.class.getDeclaredField("value");
        value.setAccessible(true);
        char[] carr = (char[]) value.get(aa);
        carr[3] = '_';
        System.out.println(aa == bb);
        System.out.println("aa = " + aa);
        System.out.println("bb =" + bb);
        System.out.println("c = " + c);
        System.out.println(aa == c);
        int[] arr = new int[10];
        System.out.println(ClassLayout.parseInstance(t).toPrintable());
        synchronized (t) {
            System.out.println(ClassLayout.parseInstance(t).toPrintable());
        }
        System.out.println("------------------------------------------------------------------");
        System.out.println(ClassLayout.parseInstance(arr).toPrintable());
    }
}
