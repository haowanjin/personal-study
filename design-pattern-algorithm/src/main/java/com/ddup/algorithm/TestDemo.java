package com.ddup.algorithm;

import java.util.Scanner;

public class TestDemo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String ipv4 = in.nextLine();
        System.out.println(calcIpv4(ipv4));

        System.out.println(1 * Math.pow(2, 24)+"");

        System.out.println();
    }

    private static String calcIpv4(String ipv4) {
        if (ipv4 == null || ipv4.isEmpty()) {
            return "invalid IP";
        }
        String[] arr = ipv4.split("#");
        if (arr.length != 4) {
            return "invalid IP";
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            int ip = Integer.parseInt(arr[i]);
            if (i == 0 && (ip < 1 || ip > 128)) {
                return "invalid IP";
            }
            if (i > 0 && (ip < 0 || ip > 255)) {
                return "invalid IP";
            }
            res.append(ip * Math.max(2, 8 * (3 - i)));
        }


        return res.toString();
    }
}
