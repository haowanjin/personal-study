package com.ddup.algorithm.basic;

import java.util.*;

public class Knaspack {
    public static void main(String[] args) {
        List<Integer> l1 = Arrays.asList(3, 10);
        List<Integer> l2 = Arrays.asList(9, 1);
        List<Integer> l3 = Arrays.asList(5, 1);
        List<List<Integer>> nums = Arrays.asList(l3, l2, l1);

        System.out.println(knapsack(8, 3, nums));


    }

    public static ArrayList<Integer> knapsack(int v, int n, List<List<Integer>> nums) {
        ArrayList<Integer> volume = new ArrayList<>();
        ArrayList<Integer> price = new ArrayList<>();
        ArrayList<Integer> res = new ArrayList<>();
        for (List<Integer> num : nums) {
            volume.add(num.get(0));
            price.add(num.get(1));
        }
        int[] dp = new int[v + 1];
        int[] dp1 = new int[v + 1];

        Arrays.fill(dp1, Integer.MIN_VALUE);
        dp1[0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = volume.get(i); j < v + 1; j++) {
                dp[j] = Math.max(dp[j], dp[j - volume.get(i)] + price.get(i));
                dp1[j] = Math.max(dp1[j], dp1[j - volume.get(i)] + price.get(i));
            }
        }
        res.add(dp[v]);
        res.add(Math.max(dp1[v], 0));
        return res;
    }

    public static ArrayList<Integer> knapsack1(int v, int n, List<List<Integer>> nums) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        //问题1
        int[] dp = new int[v + 1];
        //问题2
        int[] dp1 = new int[v + 1];
        for (int i = 1; i <= v; ++i) {
            int max = Integer.MIN_VALUE;
            int max1 = Integer.MIN_VALUE;
            for (int j = 0; j < n; ++j) {
                if (i >= nums.get(j).get(0)) {
                    // 遍历所有物品，找出能够装入当前背包的最大价值
                    max = Math.max(dp[i - nums.get(j).get(0)] + nums.get(j).get(1), max);
                    // 问题2要求背包正好装满，故无需去计算不装满背包的情况
                    max1 = Math.max(dp1[i - nums.get(j).get(0)] + nums.get(j).get(1), max1);
                }
                // 背包不装满
                max = Math.max(max, dp[i - 1]);
            }
            dp[i] = max;
            dp1[i] = max1;
        }
        res.add(dp[v]);
        res.add(dp1[v] < 0 ? 0 : dp1[v]);
        return res;
    }

    public ArrayList<Integer> knapsack3(int v, int n, List<List<Integer>> nums) {
        int[] V = new int[n + 1];
        int[] W = new int[n + 1];
        for (int goods = 1; goods <= n; goods++) {
            V[goods] = nums.get(goods - 1).get(0);
            W[goods] = nums.get(goods - 1).get(1);
        }
        int[] dp1 = new int[v + 1];
        int[] dp2 = new int[v + 1];
        Arrays.fill(dp2, Integer.MIN_VALUE);
        dp2[0] = 0;
        for (int goods = 1; goods <= n; goods++) {
            for (int capacity = V[goods]; capacity <= v; capacity++) {
                dp1[capacity] = Math.max(dp1[capacity], dp1[capacity - V[goods]] + W[goods]);
                dp2[capacity] = Math.max(dp2[capacity], dp2[capacity - V[goods]] + W[goods]);
                if (dp2[capacity] < 0) {
                    dp2[capacity] = Integer.MIN_VALUE;
                }
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        res.add(dp1[v]);
        res.add(dp2[v] == Integer.MIN_VALUE ? 0 : dp2[v]);
        return res;
    }
}
