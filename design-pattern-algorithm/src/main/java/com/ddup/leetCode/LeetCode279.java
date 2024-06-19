package com.ddup.leetCode;

import java.util.List;

public class LeetCode279 {
    public static void main(String[] args) {

    }

    private static int process(List<Integer> list, int index, int rest, int[][] dp) {
        if(rest == 0) {
            return 0;
        }
        if(index==list.size()) {
            return Integer.MAX_VALUE;
        }
        if(dp[index][rest] != -1) {
            return dp[index][rest];
        }
        int curr = list.get(index);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i * curr <= rest; i++) {
            int process = process(list, index + 1, rest - i * curr, dp);
            if(process != Integer.MAX_VALUE) {
                res=Math.min(res, i+process);
            }
        }
        dp[index][rest] = res;
        return res;
    }

}
