package com.ddup.leetCode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode78 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> res = new ArrayList<>();
        dfs2(0, nums, new ArrayList<>(nums.length), res);
        System.out.println(res);

    }

    /**
     *
     * @param i 不选第i个，选第i个
     * @param nums 数组
     * @param path 每种情况选择的结果
     * @param res 最终结果
     */
    private static void dfs1(int i, int[] nums, List<Integer> path, List<List<Integer>> res) {
        if (i == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        dfs1(i+1, nums, path, res);
        path.add(nums[i]);
        dfs1(i + 1, nums, path, res);
        path.remove(path.size() - 1);
    }

    /**
     * 递归枚举i与i之后元素的组合，每次将枚举的结果添加到最终结果中
     *
     * @param i 枚举i之后的元素
     * @param nums 数组
     * @param path 每次枚举的结果
     * @param res 保存结果
     */
    private static void dfs2(int i, int[] nums, List<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));
        if (i == nums.length) {
            return;
        }
        for (int j = i; j < nums.length; j++) {
            path.add(nums[j]);
            dfs2(j+1, nums, path, res);
            path.remove(path.size() - 1);
        }
    }
}
