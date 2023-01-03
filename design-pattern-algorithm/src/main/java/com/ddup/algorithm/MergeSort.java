package com.ddup.algorithm;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 3, 3, 2, 1, 5, 2, 2, 2, 5, 6, 1, 4};
        System.out.println(Arrays.toString(arr));
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int arr[], int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        process(arr, left, mid);
        process(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] tem = new int[right - left + 1];
        int i = 0, p1 = left, p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            tem[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            tem[i++] = arr[p1++];
        }
        while (p2 <= right) {
            tem[i++] = arr[p2++];
        }
        for (i = 0; i < tem.length; i++) {
            arr[left + i] = tem[i];
        }
    }
}
