package com.ddup.algorithm;

public class MaxSumSubArray {
    public static void main(String[] args) {
        int[] arr = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(calculate(arr, 0, 8));
        System.out.println(calculate(arr));
        System.out.println(calculate2(arr));
    }


    // 答案错误
    public static int calculate(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        int mid = (r - l) / 2 + l;
        int left = calculate(arr, l, mid);
        int right = calculate(arr, mid + 1, r);
        int sum = 0;
        for (int i = l; i <= r; i++) {
            sum += arr[i];
        }

        return Math.max(sum, Math.max(left, right));
    }

    public static int calculate(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                max = Math.max(sum, max);
            }
        }
        return max;
    }

    public static int calculate2(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            if (sum < 0) {
                sum = 0;
            }
            sum += num;
            max = Math.max(sum, max);
        }
        return max;
    }

}
