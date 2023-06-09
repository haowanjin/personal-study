package com.ddup.algorithm.actual;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 最佳种树距离
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] holes = new int[n];
        for (int i = 0; i < n; i++) {
            holes[i] = scanner.nextInt();
        }

        int target = scanner.nextInt();

        Arrays.sort(holes);
        int left = 0;
        int right = holes[holes.length - 1] - holes[0];
        int answer = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int count = 1;
            int previous = holes[0];

            for (int i = 1; i < holes.length; i++) {
                if (holes[i] - previous >= mid) {
                    count++;
                    previous = holes[i];

                    if (count >= target) {
                        answer = mid;
                        left = mid + 1;
                        break;
                    }
                }
            }

            if (count < target) {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }
}

