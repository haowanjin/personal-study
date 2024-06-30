package com.ddup.leetCode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode17 {
    private static String[] dict = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static void main(String[] args) {
        String digits = "23";
        List<String> list = new ArrayList<>();
        char[] path = new char[digits.length()];
        dfs1(digits, 0, path, list);
        System.out.println(list);
    }

    private static void dfs1(String digits, int i, char[] path, List<String> res) {
        if (i == digits.length()) {
            res.add(new String(path));
            return;
        }
        int idx = digits.charAt(i) - '0';
        for (int j = 0; j < dict[idx].length(); j++) {
            path[i] = dict[idx].charAt(j);
            dfs1(digits, i + 1, path, res);
        }
    }


}
