package com.ddup.leetCode;

public class LeetCode322 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5};
        int amount = 11;
        int[] dp = new int[amount + 1];
        System.out.printf("最小零钱数量：%s", process(nums, amount, dp));

    }

    private static int process(int[] coins, int amount, int[] dp) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;
        if (dp[amount] != 0) return dp[amount];
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (coin <= amount) {
                int p1 = process(coins, amount - coin, dp);
                if (p1 != -1) {
                    res = Math.min(res, p1 + 1);
                }
            }
        }
        res = res == Integer.MAX_VALUE ? -1 : res;
        dp[amount] = res;
        return res;
    }
}
