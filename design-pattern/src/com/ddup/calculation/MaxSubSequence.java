package com.ddup.calculation;

import java.util.*;

public class MaxSubSequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        while (k != 0) {
            int[] array = new int[k];
            for (int i = 0; i < k; i++) {
                array[i] = sc.nextInt();
            }
            subSequence(array);
            k = sc.nextInt();
            array = null;
        }
        System.out.println(0 + " " + 0 + " " + 0);
    }

    private static void subSequence(int[] arr) {
        int sum = 0, max = arr[0], before = 0, end = 0, n;
        if ((n = arr.length) == 1) {
            System.out.println((arr[0] > 0 ? arr[0] : 0) + " " + arr[0] + " " + arr[0]);
            return;
        }
        int[] dp = new int[n];
        dp[0] = arr[0];
        if (arr[0] < 0) sum++;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(arr[i], (dp[i - 1] + arr[i]));
            if (arr[i] < 0) sum++;
            if (max < dp[i]) {
                max = dp[i];
                end = i;// 如果有相同的最长子序列和，通过end记录下标靠前的。
            }
        }
        if (sum == n) {
            System.out.println(0 + " " + arr[0] + " " + arr[n - 1]);
        } else {
            int i = end;
            while (dp[i] >= 0 && i > -1) i--;
            System.out.println(max + " " + arr[++i] + " " + arr[end]);
        }
        dp = null;
    }
}