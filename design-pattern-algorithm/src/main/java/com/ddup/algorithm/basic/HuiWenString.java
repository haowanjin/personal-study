package com.ddup.algorithm.basic;

import java.util.*;

/**
 * 判断一个字符串是否是他的一个子串拼接而成的
 */
public class HuiWenString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String str = sc.nextLine();

            System.out.println(binSearch(str));
        }
    }


    public static String binSearch(String str) {
        int len = 2;
        while (str.length() / len > 0) {
            if (str.length() % len == 0) {
                StringBuilder sb = new StringBuilder();
                String substring = str.substring(0, str.length() / len);
                for (int i = 0; i < len; i++) {
                    sb.append(substring);
                }
                if (sb.toString().equals(str)) {
                    return substring;
                }
            }
            len++;
        }
        return "false";
    }
}
