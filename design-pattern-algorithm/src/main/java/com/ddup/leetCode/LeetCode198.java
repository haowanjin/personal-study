package com.ddup.leetCode;

import java.util.Arrays;

/**
 * 打家劫舍
 */
public class LeetCode198 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int[] cache = new int[nums.length];
        Arrays.fill(cache, -1);
        System.out.println(dfs(nums.length - 1, nums, cache));
        System.out.println(dfs2(nums));
        System.out.println(dfs3(nums));
    }

    private static int dfs1(int i, int[] nums) {
        if (i < 0) {
            return 0;
        }
        return Math.max(dfs1(i - 1, nums), dfs1(i - 2, nums) + nums[i]);
    }

    private static int dfs(int i, int[] nums, int[] cache) {
        if (i < 0) {
            return 0;
        }
        if (cache[i] != -1) {
            return cache[i];
        }
        int res = Math.max(dfs(i - 1, nums, cache), dfs(i - 2, nums, cache) + nums[i]);
        cache[i] = res;
        return res;
    }

    private static int dfs2(int[] nums) {
        int[] f = new int[nums.length + 2];
        for (int i = 0; i < nums.length; i++) {
            f[i + 2] = Math.max(f[i + 1], f[i] + nums[i]);
        }
        return f[nums.length + 1];
    }

    private static int dfs3(int[] nums) {
        int f0 = 0;
        int f1=0;
        for (int num : nums) {
            int new_f = Math.max(f1, f0 + num);
            f0=f1;
            f1=new_f;
        }
        return f1;
    }
}
