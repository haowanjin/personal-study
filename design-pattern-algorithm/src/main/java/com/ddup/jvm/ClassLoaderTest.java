package com.ddup.jvm;

/**
 * @author haowanjin
 * @date 2023/2/15 19:41
 * @description
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        MyClassLoader mcl = new MyClassLoader();
        Class<?> clazz = Class.forName("People", true, mcl);
        Object o = clazz.newInstance();
        System.out.println(o);
        System.out.println(o.getClass().getClassLoader());
    }
}
