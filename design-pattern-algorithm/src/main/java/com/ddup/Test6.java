package com.ddup;

import java.util.*;

public class Test6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int[] p = new int[n];
            for (int i = 0; i < n; i++) {
                p[i] = in.nextInt();
            }
            int m = in.nextInt();
            int[][] matrix = new int[m][m];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = in.nextInt();
                }
            }
            String res = test111(matrix, p);
            if ("error".equals(res)) {
                System.out.println("error");
            } else {
                char[] charArray = res.toCharArray();
                StringBuilder sb = new StringBuilder();
                for (char c : charArray) {
                    sb.append(c).append(" ");
                }
//                sb.delete(sb.length() - 1, sb.length());
                System.out.println(sb);
            }
        }
    }

    private static String test111(int[][] matrix, int[] p) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < p.length; i++) {
            int count = 0;
            for (int j = 0; j < matrix.length; j++) {
                for (int k = 0; k < matrix[0].length; k++) {
                    if (p[i] == matrix[j][k]) {
                        if (matrix[j + 1][k] == p[i + 1] || matrix[j][k + 1] == p[i + 1]) {
                            sb.append(j).append(k);
                            count++;
                        } else if (sb.length() != 0) {
                            int k1 = Integer.parseInt(sb.substring(sb.length() - 1, sb.length()));
                            int j1 = Integer.parseInt(sb.substring(sb.length() - 2, sb.length()));
                            if (count == 0 && (k == k1 + 1 || j == j1 + 1) && matrix[j1][k1] == p[i - 1]) {
                                sb.append(j).append(k);
                                count++;
                            } else if (count > 0 && (k == k1 + 1 || j == j1 + 1) && matrix[j1][k1] == p[i - 1]) {
                                String old = sb.toString();
                                sb.delete(sb.length() - 2, sb.length());
                                sb.append(j).append(k);
                                if (old.compareTo(sb.toString()) < 0) {
                                    sb = new StringBuilder(old);
                                }
                            }
                            count++;
                        }
                    }
                }
            }
            if (count < 1) {
                return "error";
            }
        }
        return sb.length() == p.length * 2 ? sb.toString() : "error";
    }
}
