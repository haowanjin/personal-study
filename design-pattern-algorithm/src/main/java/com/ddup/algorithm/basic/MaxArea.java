package com.ddup.algorithm.basic;

/**
 * @author haowanjin
 * @date 2023/5/12 10:51
 * <p>
 * 盛最多水的容器
 * </p>
 */
public class MaxArea {
    public static void main(String[] args) {
        int[] arr = {6, 4, 3, 1, 4, 6, 99, 62, 1, 2, 6};
        System.out.println(maxArea(arr));
    }

    public static int maxArea(int[] height) {
        int res = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            int capacity = Math.min(height[left], height[right]) * (right - left);
            res = Math.max(res, capacity);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

    /**
     * 错误示范
     *
     * @param height
     * @return
     */
    public int max(int[] height) {
        int res = 0;
        int r = height.length - 1;
        for (int i = 0; i < r; ) {
            int capacity = Math.min(height[i], height[r]) * (r - i);
            res = Math.max(res, capacity);
            if (height[i] < height[r]) {
                i++;
            } else {
                r--;
            }
        }

        return res;
    }
}
