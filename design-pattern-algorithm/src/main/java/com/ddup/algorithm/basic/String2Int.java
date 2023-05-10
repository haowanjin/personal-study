package com.ddup.algorithm.basic;

/**
 * @author haowanjin
 * @date 2023/4/15 19:46
 * <p>
 *
 * </p>
 */
public class String2Int {
    public static void main(String[] args) {
        System.out.println(str2Int("0XAA"));
        System.out.println(Integer.toHexString(170));
    }


    public static int str2Int(String str) {
        str = str.toUpperCase();
        str = str.substring(2);
        char[] chars = str.toCharArray();
        int sum = 0;
        int j = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (Character.isDigit(chars[i])) {
                sum = sum + Integer.parseInt(chars[i] + "") * (int) Math.pow(16, j++);
            }
            if (chars[i] >= 'A' && chars[i] <= 'F') {
                sum = sum + Integer.parseInt((chars[i] - 'A' + 10) + "") * (int) Math.pow(16, j++);
            }
        }
        return sum;
    }
}
