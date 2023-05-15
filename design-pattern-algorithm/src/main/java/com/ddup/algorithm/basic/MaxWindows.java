package com.ddup.algorithm.basic;

import java.util.*;

/**
 * @author haowanjin
 * @date 2023/5/13 19:40
 * <p>
 *
 * </p>
 */
public class MaxWindows {
    public static void main(String[] args) {
        int[] arr = {9, 10, 9, -7, -3, 8, 2, -6};
        System.out.println(maxWindows(arr, 5));
    }

    public static List<Integer> maxWindows(int[] arr, int windows) {
        if (windows > arr.length || windows == 0) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        // 先进先出，当队列里的元素大于Windows，队头弹出
        for (int i = 0; i < arr.length; i++) {
            if (queue.size() < windows) {
                queue.add(arr[i]);
            }
            if (queue.size() == windows) {
                list.add(queue.stream().mapToInt(e -> e).max().getAsInt());
                queue.poll();
            }
        }
        return list;
    }

    /**
     * 计算岛屿数量
     *
     * @param grid
     * @return
     */
    public int solve(char[][] grid) {
        // write code here
        int res = 0;
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    infect(grid, i, j, row, col);
                }
            }
        }
        return res;
    }

    private void infect(char[][] grid, int i, int j, int row, int col) {
        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] != '1') {
            return;
        }
        infect(grid, i - 1, j, row, col);
        infect(grid, i + 1, j, row, col);
        infect(grid, i, j - 1, row, col);
        infect(grid, i, j + 1, row, col);
    }
}
