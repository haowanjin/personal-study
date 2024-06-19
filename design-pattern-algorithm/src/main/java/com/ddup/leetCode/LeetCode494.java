package com.ddup.leetCode;

import java.util.Arrays;

/**
 * 目标和，0 1 背包问题
 */
public class LeetCode494 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        int[] w = {};
        int[] v = {};
        // System.out.println(zeroPackage(0, 3, w, v));
        int target = 3;
        target += Arrays.stream(nums).sum();
        if (target < 0 || target % 2 == 1) {
            System.out.println(0);
        }
        target = target / 2;
        System.out.println(targetSum(nums.length - 1, target, nums));
    }

    /**
     * 0 1背包
     *
     * @param i        当前位置
     * @param capacity 容量
     * @param w        重量
     * @param v        价值
     * @return 最大方案数
     */
    private static int zeroPackage(int i, int capacity, int[] w, int[] v) {
        if (i < 0) {
            return 0;
        }
        if (capacity < w[i]) {
            return zeroPackage(i - 1, capacity, w, v);
        }
        return Math.max(zeroPackage(i - 1, capacity, w, v), zeroPackage(i - 1, capacity - w[i], w, v) + v[i]);
    }

    /**
     * p
     * s-p
     * p-(s-p)=t
     * 2p=s+t
     * p=(s+t)/2
     *
     * @param i 当前选择位置
     * @param target 目标数
     * @param nums 数组
     * @return 最后方案数
     */
    private static int targetSum(int i, int target, int[] nums) {
        if (i < 0) {
            return target == 0 ? 1 : 0;
        }
        if (target < nums[i]) {
            return targetSum(i - 1, target, nums);
        }
        return targetSum(i - 1, target, nums) + targetSum(i - 1, target - nums[i], nums);
    }

}
