package com.ddup.calculation;

/**
 * 最长子串
 */
public class KmpCalculator {
    public static void main(String[] args) {
        String str1 = "cababccababctka";
        String str2 = "ababctk";
        System.out.println(getStrIndex(str1, str2));
    }

    public static int getStrIndex(String str1, String str2) {
        int i1 = 0, i2 = 0;
        int[] next = getNextArray(str2);

        while (i1 < str1.length() && i2 < str2.length()) {
            if (str1.charAt(i1) == str2.charAt(i2)) {
                i1++;
                i2++;
            } else {
                if (next[i2] == -1) {
                    i1++;
                } else {
                    i2 = next[i2];
                }
            }
        }
        return i2 == str2.length() ? i1 - i2 : -1;
    }

    private static int[] getNextArray(String str2) {
        if (str2.length() == 1) {
            return new int[]{-1};
        }
        int[] next = new int[str2.length()];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < str2.length()) {
            if (str2.charAt(i - 1) == str2.charAt(cn)) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }
}
