package com.ddup.leetCode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode131 {
    public static void main(String[] args) {
        String s = "aabb";
        List<List<String>> res = new ArrayList<>();
        dfs(0, 0, s, new ArrayList<>(), res);
        System.out.println(res);
    }

    private static void dfs(int start, int i, String s, List<String> path, List<List<String>> res) {
        if (i == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (i < s.length() - 1) {
            dfs(start, i + 1, s, path, res);
        }
        String st = s.substring(start, i + 1);
        String sb = new StringBuilder(st).reverse().toString();
        if (st.equals(sb)) {
            path.add(st);
            dfs(i + 1, i + 1, s, path, res);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 枚举以 i 位置开始，截取每个位置的字符串，如果截取的每个字符串都是回文子串，则加入答案
     *
     * @param i    开始位置
     * @param s    字符串
     * @param path 每次满足条件的结果
     * @param res  所有结果集
     */
    private static void dfs1(int i, String s, List<String> path, List<List<String>> res) {
        if (i == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int j = i; j < s.length(); j++) {
            String st = s.substring(i, j + 1);
            String sb = new StringBuilder(st).reverse().toString();
            if (st.equals(sb)) {
                path.add(st);
                dfs1(j + 1, s, path, res);
                path.remove(path.size() - 1);
            }

        }
    }
}
