package com.ddup.algorithm.actual;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author haowanjin
 * @date 2023/5/15 16:32
 * <p>
 *
 * </p>
 */
public class MidLinkNode {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    }

    public String zconvert(String str, int k) {
        // write code here
        if (k == 1) return str;
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(k, str.length()); i++) {
            rows.add(new StringBuilder());
        }
        int curRow = 0;
        boolean down = false;
        for (char c : str.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == k - 1) down = !down;
            if (!down) {
                curRow -= 1;
            } else {
                curRow += 1;
            }
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) {
            res.append(row);
        }
        return res.toString();
    }
}
