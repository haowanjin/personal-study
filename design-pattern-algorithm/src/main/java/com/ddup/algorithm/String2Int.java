package com.ddup.algorithm;

/**
 * @author haowanjin
 * @date 2023/4/15 19:46
 * <p>
 *
 * </p>
 */
public class String2Int {
    public static void main(String[] args) {

    }

    public static void str2Int(String str) {
        String s = str.substring(3);
        char[] chars = s.toCharArray();
        int sum=0;
        int j=0;
        for (int i = chars.length-1;- i >=0; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                sum=sum+Integer.parseInt(chars[i]+"")*16^j;


            }
        }
    }
}
