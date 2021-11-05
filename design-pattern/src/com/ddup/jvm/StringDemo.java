package com.ddup.jvm;

/**
 * @author: hwj
 * @Description TODO
 * @create: 2021/9/17 15:31
 */
public class StringDemo {
    public static void main(String[] args) {

        testStringIntern();
    }

    public void testNewString() {
        /**
         * 两个对象
         * 一个 new String()
         * 一个是常量池中的 ab
         */
        String ab = new String("ab");

        /**
         * 1 new StringBuilder()
         * 2 a
         * 3 常量池中的 a
         * 4 b
         * 5 常量池中的 b
         * 调用StringBuilder.toString()
         *  6 new String("ab")
         */
        String s = new String("c") + new String("d");

    }

    /**
     * intern()的作用: 如果字符串常量池中已经包含一个等于此 String 对象的字符串，
     * 则返回代表池中这个字符串的 String 对象； 否则，将此 String
     * 对象包含的字符串添加到常量池中，并且返回此 String 对象的引用。
     */
    public static void testStringIntern() {

        String a = "java";
        String str1 = new StringBuilder("ja").append("va").toString();
        String b = str1.intern();

        String str2 = new StringBuilder("计算机软件").append("工程师").toString();
        String c = str2.intern();

        System.out.println(str1 == a);// false // str1在堆上，a在常量池
        System.out.println(b == a);// true  由于字符串常量池中已经有 java 了
        System.out.println(b == str1);// false

        System.out.println(c == str2);// true 常量池中没有 "计算机软件工程师"
    }
}
