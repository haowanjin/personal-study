package com.ddup.algorithm.basic;

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

    /**
     * 暴力求解
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int violenceMatch(String str1, String str2) {
        int i = 0, j = 0;
        while (i < str1.length() && j < str2.length()) {
            if (str1.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j == str2.length()) {
            return i - j;
        } else {
            return -1;
        }
    }

    private static int violenceMatch2(String ptr, String pat) {
        int N = ptr.length();
        int M = pat.length();
        if (M > N) {
            return -1;
        }

        for (int i = 0; i <= N - M; ) {
            int j;
            for (j = 0; j < M; j++) {
                if (ptr.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
            }
            //模式串都匹配成功了
            if (j == M) {
                return i;
            }
            i++;
        }

        return -1;
    }
}
