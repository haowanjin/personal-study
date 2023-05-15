package com.ddup.algorithm.basic;

/**
 * @author haowanjin
 * @date 2023/5/13 17:25
 * <p>
 * 接雨水问题
 * 当前柱子的盛水量是其左边最高的柱子、右边最高柱子两者较小的减去当前柱子的高度
 * </p>
 */
public class MaxWater {

    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 5, 2, 4};
        System.out.println(dpMaxWater(arr));
    }

    /**
     * 双指针
     *
     * @param arr
     * @return
     */
    public static int maxWater(int[] arr) {
        int res = 0;
        int maxL = 0, maxR = 0;
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            maxL = Math.max(maxL, arr[left]);
            maxR = Math.max(maxR, arr[right]);
            if (maxR > maxL) {
                res += maxL - arr[left++];
            } else {
                res += maxR - arr[right--];
            }
        }
        return res;
    }

    /**
     * 暴力解决
     *
     * @param arr
     * @return
     */
    public static int violenceMaxWater(int[] arr) {
        int res = 0;
        //从第2个柱子开始，到倒数第2个柱子
        for (int i = 1; i < arr.length - 1; i++) {
            int maxL = 0, maxR = 0;
            for (int j = 0; j <= i; j++) {
                maxL = Math.max(maxL, arr[j]);
            }
            for (int j = i; j < arr.length; j++) {
                maxR = Math.max(maxR, arr[j]);
            }
            res += Math.min(maxL, maxR) - arr[i];

        }

        return res;
    }

    public static int dpMaxWater(int[] arr) {
        int res = 0;
        int len = arr.length;
        if (len <= 2) {
            return res;
        }
        int[][] dp = new int[2][len];
        dp[0][0] = arr[0];
        dp[1][len - 1] = arr[len - 1];
        for (int i = 1; i < len - 1; i++) {
            dp[0][i] = Math.max(dp[0][i - 1], arr[i]);
            dp[1][len - i - 1] = Math.max(arr[len - i - 1], dp[1][len - i]);
        }
        for (int i = 1; i < len - 1; i++) {
            res += Math.min(dp[0][i], dp[1][i]) - arr[i];
        }
        return res;
    }
}
