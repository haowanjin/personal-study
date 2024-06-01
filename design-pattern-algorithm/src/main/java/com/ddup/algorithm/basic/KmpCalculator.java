package com.ddup.algorithm.basic;

import java.util.HashMap;
import java.util.Map;

/**
 * 最长子串
 */
public class KmpCalculator {
    public static void main(String[] args) {
        String str1 = "cababccababctka";
        String str2 = "ababctk";
        System.out.println(getStrIndex(str1, str2));

        System.out.println(getMinString("XDOYEZODEYXNZ", "XYZ"));

        System.out.println(calcStr("((())()()))"));
    }

    /**
     * kmp算法求解字符串匹配
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int getStrIndex(String str1, String str2) {
        int i1 = 0, i2 = 0;
        int[] next = getNextArray2(str2);

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
     * "前缀"指除了最后一个字符以外，一个字符串的全部头部组合
     * "后缀"指除了第一个字符以外，一个字符串的全部尾部组合
     *
     * @param str
     * @return
     */
    private static int[] getNextArray2(String str) {
        if (str.length() == 1) {
            return new int[]{-1};
        }
        int[] next = new int[str.length()];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        for (int i = 2; i < str.length(); i++) {
            while (str.charAt(i - 1) != str.charAt(cn) && cn > 0) {
                cn = next[cn - 1];
            }
            if (str.charAt(i - 1) == str.charAt(cn)) {
                next[i] = ++cn;
            } else {
                next[i] = 0;
            }
        }

        return next;
    }

    /**
     * 暴力求解字符串
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

    /**
     * 暴力匹配字符串
     *
     * @param ptr
     * @param pat
     * @return
     */
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

    /**
     * 找出最短不连续的子串
     *
     * @param S
     * @param T
     * @return
     */
    public static String getMinString(String S, String T) {
        Map<Character, Integer> map = new HashMap<>();
        int cnt = S.length() + 1;
        for (char c : T.toCharArray()) {
            map.compute(c, (k, v) -> {
                if (v == null) return -1;

                return v - 1;
            });
        }
        int slow = 0, fast = 0;
        int left = -1, right = -1;

        while (fast < S.length()) {
            char c = S.charAt(fast);
            int count = map.get(c) == null ? 1 : map.get(c) + 1;
            map.put(c, count);
            while (check(map)) {
                if (fast - slow + 1 < cnt) {
                    cnt = fast - slow + 1;
                    left = slow;
                    right = fast;
                }
                c = S.charAt(slow);
                map.compute(c, (k, v) -> {
                    if (v == null) {
                        return -1;
                    }
                    return v - 1;
                });
                slow++;
            }
            fast++;
        }
        if (left == -1) {
            return "";
        }
        return S.substring(left, right + 1);
    }

    public static boolean check(Map<Character, Integer> hash) {
        for (Integer value : hash.values()) {
            if (value < 0) {
                return false;
            }
        }
        return true;
    }


    //((())()()))
    public static int calcStr(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        int res = 0;
        int[] dp = new int[str.length()];
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == ')') {
                int pre = i - dp[i - 1] - 1;
                if (pre >= 0 && str.charAt(pre) == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
