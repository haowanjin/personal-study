package com.ddup.algorithm.actual;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author haowanjin
 * @date 2023/5/15 17:30
 * <p>
 * 完美走位
 * </p>
 */
public class PerfectMove {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.next();

        System.out.println(getResult(str));
    }

    public static int getResult(String str) {
        Map<Character, Integer> count = new HashMap<>();
        for (char c : str.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        boolean flag = true;
        int total = 0;
        int avg = str.length() / 4;
        int minLen = str.length() + 1;
        for (Character c : count.keySet()) {
            if (count.get(c) > avg) {
                flag = false;
                count.put(c, count.get(c) - avg);
                total += count.get(c);
            } else {
                count.put(c, 0);
            }
        }
        if (flag) return 0;
        int i = 0, j = 0;
        while (j < str.length()) {
            char jc = str.charAt(j);
            if (count.get(jc) > 0) {
                total--;
            }
            count.put(jc, count.get(jc) - 1);
            while (total == 0) {
                minLen = Math.min(minLen, j - i + 1);
                char ic = str.charAt(j);
                if (count.get(ic) > 0) {
                    total++;
                }
                count.put(ic, count.get(ic) + 1);
                i++;
            }
            j++;
        }

        return minLen;
    }
}
