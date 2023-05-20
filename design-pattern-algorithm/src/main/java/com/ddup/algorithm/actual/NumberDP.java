package com.ddup.algorithm.actual;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author haowanjin
 * @date 2023/5/15 20:09
 * <p>
 *
 * </p>
 */
public class NumberDP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int L = sc.nextInt();
        int R = sc.nextInt();

        int count = digitSearch(R) - digitSearch(L - 1);
        System.out.println(count);
    }

    private static int digitSearch(int n) {
        Integer[] arr = Arrays.stream(Integer.toBinaryString(n).split(""))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        int[][][] f = new int[n][2][2];

        return dfs(0, true, f, arr, 0, 0);
    }

    private static int dfs(int p, boolean limit, int[][][] f, Integer[] arr, int pre, int prePre) {
        if (p == arr.length) return 1;
        if (!limit && f[p][pre][prePre] != 0) return f[p][pre][prePre];
        int max = limit ? arr[p] : 1;
        int count = 0;
        for (int i = 0; i <= max; i++) {
            if (i == 1 && pre == 0 && prePre == 1) continue;
            count += dfs(p + 1, limit && i == max, f, arr, i, pre);
        }
        if (!limit) f[p][pre][prePre] = count;
        return count;
    }


}
