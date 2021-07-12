package com.ddup.calculation;

/**
 * 判断一个字符串是不是十进制数字
 */
public class Solution {

    public static boolean isNumber(String s) {
        if (s == null || s.length() == 0)
            return false;
        //去除两端的空格
        int start = 0, end = s.length() - 1;
        while (start < s.length() && s.charAt(start) == ' ') {
            start++;
        }
        //若全为空格则返回false
        if (start == s.length()) {
            return false;
        }
        while (end >= 0 && s.charAt(end) == ' ') {
            end--;
        }
        //判断是不是只有一个e
        if (s.indexOf("E") != s.lastIndexOf("E")) {
            return false;
        }
        s = s.substring(start, end + 1);
        //按e分割字符串，不是分割成一部分或者两部分肯定有误
        String[] str = s.split("E");
        if (str.length > 2 || str.length < 1) {
            return false;
        }
        String temp = str[0];
        if (temp.equals(".") || temp.equals("") || temp.equals("+") || temp.equals("-")
                || temp.equals("-.") || temp.equals(".+")) {
            return false;
        }
        //底数部分可以有数字；+；-；.
        int flag = 1;
        if (temp.charAt(0) == '+' || temp.charAt(0) == '-') {
            temp = temp.substring(1);
        } else if (temp.charAt(0) == '.' || (temp.charAt(0) >= '0' && temp.charAt(0) <= '9')) {
        } else {
            return false;
        }
        for (int i = 0; i < temp.length(); i++) {
            if (flag == 1 && temp.charAt(i) == '.') {
                flag = 0;
                continue;
            } else if (temp.charAt(i) >= '0' && temp.charAt(i) <= '9') {
                continue;
            } else {
                return false;
            }
        }
        //如果s只是e，那么返回false
        if (str.length == 1 && !s.contains("E")) {
            return true;
        }
        if (str.length == 1 && s.contains("E")) {
            return false;
        }
        //指数只能有数字；+；-
        temp = str[1];
        if (temp.equals(".") || temp.equals("") || temp.equals("+") || temp.equals("-")) {
            return false;
        }
        if (temp.charAt(0) == '+' || temp.charAt(0) == '-') {
            temp = temp.substring(1);
        } else if (temp.charAt(0) >= '0' && temp.charAt(0) <= '9') {
        } else {
            return false;
        }
        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) >= '0' && temp.charAt(i) <= '9') {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isNumber("q"));
    }
}
