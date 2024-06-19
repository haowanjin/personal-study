package com.ddup.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * N 皇后
 */
public class LeetCode51 {
    public static void main(String[] args) {
        int n =9;
        List<List<String>> ans = new ArrayList<>();
        int[] col = new int[n];
        boolean[] onPath = new boolean[n];
        boolean[] diag1 = new boolean[n * 2 - 1];
        boolean[] diag2 = new boolean[n * 2 - 1];
        dfs(0, n, col, onPath, diag1, diag2, ans);
        System.out.println(ans);
        System.out.println(ans.size());
    }

    private static void dfs(int r, int n, int[] col, boolean[] onPath, boolean[] diag1, boolean[] diag2, List<List<String>> ans) {
        if (r == n) {
            List<String> board = new ArrayList<>(n);
            for (int c : col) {
                char[] row = new char[n];
                Arrays.fill(row, '.');
                row[c] = 'Q';
                board.add(new String(row));
            }
            ans.add(board);
            return;
        }
        for (int c = 0; c < n; c++) {
            int rc = r - c + n - 1;
            if (!onPath[c] && !diag1[r + c] && !diag2[rc]) {
                col[r] = c;
                onPath[c] = diag1[r + c] = diag2[rc] = true;
                dfs(r + 1, n, col, onPath, diag1, diag2, ans);
                onPath[c] = diag1[r + c] = diag2[rc] = false; // 恢复现场
            }
        }
    }
}
