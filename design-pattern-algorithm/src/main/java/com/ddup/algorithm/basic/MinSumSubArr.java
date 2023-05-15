package com.ddup.algorithm.basic;

import java.util.Arrays;
import java.util.List;

/**
 * @author haowanjin
 * @date 2023/5/14 15:26
 * <p>
 *
 * </p>
 */
public class MinSumSubArr {
    public static void main(String[] args) {
        System.out.println(sumSubArr(Arrays.asList(3, 1, 2, 4, 5)));
    }

    public static int sumSubArr(List<Integer> nums) {
        // 单调栈+动态规划
        int n = nums.size();
        long res = 0;
        int[] dp = new int[n + 1]; // 子区间最小值之和
        int[] stack = new int[n + 1];
        int top = 0;
        int mod = 1000000007;
        // 初始化
        Arrays.fill(stack, -1);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            // 构建单调栈
            while (top > 0 && nums.get(i) <= nums.get(stack[top])) {
                top--;
            }
            dp[i + 1] = (dp[stack[top] + 1] + (i - stack[top]) * nums.get(i)) % mod;
            stack[++top] = i; // 入栈
            res += dp[i + 1];
        }
        return (int) (res % mod);
    }
}
