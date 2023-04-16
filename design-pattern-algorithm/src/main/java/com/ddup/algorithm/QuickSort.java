package com.ddup.algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 快速排序算法
 */
public class QuickSort {

    public static void quickSort1(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (arr == null || arr.length < 2) {
            return;
        }
        if (left < right) {//首先判断角标是否越界
            int cen = arr[(left + ((right - left) >> 1))];//用变量将中间值记录下来，不记录下来会使中间值在下面代码执行过程中发生改变，因为数组在下面发生了重排，这时再取值就不准了
            int i = left - 1;
            int j = right + 1;
            while (true) {
                while (arr[++i] < cen && i < j) ;
                while (arr[--j] > cen && i < j) ;
                if (i >= j)
                    break;
                swap(arr, i, j);
            }
            quickSort(arr, left, i - 1);
            quickSort(arr, j + 1, right);
        }
    }


    public static void quickSort2(int[] arr, int l, int r) {
        if (arr == null || arr.length < 2) {
            return;
        }
        if (l < r) {
            int random = l + (int) Math.random() * (r - l + 1);
            swap(arr, random, r);
            int[] p = partition(arr, 0, r);
            quickSort2(arr, l, p[0] - 1);
            quickSort2(arr, p[1] + 1, r);
        }
    }

    public static int[] partition(int[] arr, int l, int r) {
        int less = l - 1;
        int more = r;
        while (l < more) {
            if (arr[l] < arr[r]) {
                swap(arr, ++less, l++);
            } else if (arr[l] > arr[r]) {
                swap(arr, --more, l);
            } else {
                l++;
            }
        }
        swap(arr, more, r);
        return new int[]{less + 1, more};
    }

    private static void swap(int[] arrs, int i, int j) {
        int temp = arrs[i];
        arrs[i] = arrs[j];
        arrs[j] = temp;
    }

    /**
     * 完美数判断
     */
    static boolean checkPerfectNumber(int num) {
        int sum = 0;
        int i = 1;
        int val = (int) Math.sqrt(num);  //为了降低时间复杂度，只遍历左半边即可
        while (i <= val && i != num) { //注意输入为1的情况
            if (num % i == 0) {
                sum += i;
                if (i != 1)
                    sum += num / i;
            }
            if (sum > num)
                return false;
            i++;
        }
        return sum == num ? true : false;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 3, 3, 2, 1, 5, 2, 2, 2, 5, 6, 1, 4};
        System.out.println(checkPerfectNumber(1));

        List<Integer> collect = Arrays.stream(arr).boxed().collect(Collectors.toList());
        Collections.sort(collect, (o1, o2) -> o2 - o1);
        System.out.println(collect);

        System.out.println(Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
