package com.ddup.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列
 */
public class LeetCode46 {
    public static void main(String[] args) {
        int[] nums = {0,1};
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>(nums.length);
        boolean[] visited = new boolean[nums.length];
        dfs(0, nums, path, res, visited);
        System.out.println(res);
    }

    public static void dfs(int i, int[] nums, List<Integer> path, List<List<Integer>> res, boolean[] visited) {
        if (i == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int j = 0; j < nums.length; j++) {
            if(!visited[j]){
                visited[j] = true;
                path.add(nums[j]);
                dfs(i + 1, nums, path, res, visited);
                path.remove(path.size() - 1);
                visited[j] = false;
            }
        }
    }
}
