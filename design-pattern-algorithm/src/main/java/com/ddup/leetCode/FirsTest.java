package com.ddup.leetCode;

import java.util.*;

/**
 * @author haowanjin
 * @date 2023/5/5 14:24
 * <p>
 *
 * </p>
 */
public class FirsTest {
    public static void main(String[] args) {
        /*String s = "()[]{{}";
        System.out.println(kuoHaoValid(s));

        int[][] grid = {{1, 2, 3}, {4, 5, 6}};
        System.out.println(shortestRoadSum(grid));

        System.out.println(subStrLength("aab"));

        int[] arr = {1, -2, 3, 10, -4, 7, 2, -5};
        System.out.println(findGreatestSumOfSubArray1(arr));*/

        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) {
            String a = in.nextLine();
            int[] arr1 = new int[Integer.parseInt(a)];
            String s1 = in.nextLine();
            String[] ss = s1.split("\\s");
            for (int i = 0; i < arr1.length; i++) {
                arr1[i] = Integer.valueOf(ss[i]);
            }
            bestStock2(arr1);
        }

    }

    /**
     * 4
     * 有效括号匹配
     *
     * @param str
     * @return
     */
    public static boolean kuoHaoValid(String str) {
        LinkedList<String> stack = new LinkedList<>();
        for (char c : str.toCharArray()) {
            String s = String.valueOf(c);
            switch (s) {
                case "(":
                case "{":
                case "[":
                    stack.push(s);
                    continue;
                default:
                    break;
            }
            if (stack.isEmpty() ||
                    (s.equals(")") && !stack.pop().equals("(")) ||
                    (s.equals("}") && !stack.pop().equals("{")) ||
                    (s.equals("]") && !stack.pop().equals("["))) {
                return false;
            }
        }
        if (!stack.isEmpty()) return false;
        return true;
    }

    /**
     * 计算字符串不重复字符最长子串
     * abcabcbb
     * pwwkew
     *
     * @param s
     * @return
     */
    public static int subStrLength(String s) {
        Queue<String> queue = new LinkedList<>();
        int maxLen = 0;
        for (char c : s.toCharArray()) {
            String s1 = String.valueOf(c);
            if (!queue.contains(s1)) {
                queue.add(s1);
            } else if (queue.peek().equals(s1)) {
                maxLen = Math.max(maxLen, queue.size());
                queue.poll();
                queue.add(s1);
            } else {
                maxLen = Math.max(maxLen, queue.size());
                while (!queue.isEmpty()) {
                    String s2 = queue.poll();
                    if (s1.equals(s2)) {
                        break;
                    }
                }
                queue.add(s1);
            }
        }
        return Math.max(maxLen, queue.size());
    }

    /**
     * 二位数组左上角到右下角最短路径之和
     *
     * @param grid
     * @return
     */
    public static int shortestRoadSum(int[][] grid) {
        int[][] res = new int[grid.length][grid[0].length];
        res[0][0] = grid[0][0];
        for (int i = 1; i < grid.length; i++) {
            res[i][0] = res[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < grid[0].length; i++) {
            res[0][i] = res[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                res[i][j] = Math.min(res[i - 1][j], res[i][j - 1]) + grid[i][j];
            }
        }

        return res[grid.length - 1][grid[0].length - 1];
    }

    public static int findGreatestSumOfSubArray1(int[] array) {
        int max = array[0];
        int[] dp = new int[array.length];
        dp[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            dp[i] = Math.max(array[i], dp[i - 1] + array[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 求子数组最大和
     *
     * @param array
     * @return
     */
    public static int findGreatestSumOfSubArray(int[] array) {
        int max = array[0];
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum = Math.max(array[i] + sum, array[i]);//当前数与加上前面数的累加和取最大值
            max = Math.max(max, sum);//保存最大和
        }
        return max;
    }

    /**
     * 买卖股票的最好时机
     *
     * @param prices
     * @return
     */
    public static int bestStock(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int[][] dp = new int[2][prices.length];
        dp[0][0] = prices[0]; //表示持有价格，保存最低价格
        dp[0][1] = 0;// 表示收益，当前价格减去持有价格和前一天收益取最大值
        for (int i = 1; i < prices.length; i++) {
            dp[0][i] = Math.min(dp[0][i - 1], prices[i]);
            dp[1][i] = Math.max(dp[1][i - 1], prices[i] - dp[0][i]);
        }

        return dp[1][prices.length - 1];
    }

    /**
     * 买卖股票的最好时机
     *
     * @param nums
     */
    public static void bestStock2(int[] nums) {
        if (nums.length == 1) {
            System.out.println(0);
            return;
        }
        int[][] dp = new int[nums.length][2];
        //0表示持有 1表示不持有
        dp[0][0] = -nums[0];
        dp[0][1] = 0;
        for (int i = 1; i < nums.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -nums[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + nums[i]);
        }
        System.out.println(dp[nums.length - 1][1]);

    }

}
