package com.ddup;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        while (in.hasNext()) {
            int n = in.nextInt();
            for (int i = 0; i < n; i++) {
                list.add(in.nextInt());
            }
            int money = in.nextInt();
            System.out.println(test1(list, money));
            list.clear();
        }
    }

    public static int test1(List<Integer>arr, int money) {
        int res = 0;
        if (arr == null || arr.size() == 0) {
            return 0;
        }
        if (arr.size() == 1) {
            if (arr.get(0)<= money) {
                return 1;
            }
            return 0;
        }
        int sum = 0;
        int max = 0;
        int count = 0;
        for (int i = 0; i < arr.size(); i++) {
            if ((sum = (arr.get(i) + sum)) <= money) {
                count++;
            } else {
                res = Math.max(res, count);
                count = 0;
                sum = 0;
            }
        }
        return res;
    }
}
