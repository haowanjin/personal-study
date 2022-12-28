package com.ddup.algorithm;

/**
 * @author haowanjin
 * @date 2022/12/24 12:16
 * @description
 */
public class ZeroOnePackage {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};
        int[] v = {1500, 3000, 2000};
        dispatch(w, v, 4);

    }

    public static void dispatch(int[] w, int[] v, int packSize) {
        int r = w.length + 1;
        int c = packSize + 1;
        int[][] s = new int[r][c];
        int[][] strategy = new int[r][c];
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (w[i-1] > j) {
                    s[i][j] = s[i - 1][j];
                } else {
                    if (s[i - 1][j] > (v[i - 1] + s[i - 1][j - w[i - 1]])) {
                        s[i][j] = s[i - 1][j];
                    } else {
                        s[i][j] = v[i - 1] + s[i - 1][j - w[i - 1]];
                        strategy[i][j] = 1;
                    }
                }
            }
        }
        int i = strategy.length - 1;
        int j = strategy[0].length - 1;
        while (i >= 0 && j >= 0) {
            if (strategy[i][j] == 1) {
                System.out.println("第" + i + "个物品加入背包");
                j -= w[i-1];
            }
            i--;
        }
    }
}
