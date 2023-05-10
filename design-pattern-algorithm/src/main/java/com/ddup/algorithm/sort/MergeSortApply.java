package com.ddup.algorithm.sort;

public class MergeSortApply {

    public static void minSum(int[] arr) {
        System.out.println(process(arr, 0, arr.length - 1));
    }

    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >>1);
       // System.out.println("left=" + l + "\tr=" + r + "\tmid=" + mid);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    private static int merge(int[] arr, int left, int mid, int right) {
        int sum = 0;
        int[] tem = new int[right - left + 1];
        int i = 0, p1 = left, p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            if (arr[p1] < arr[p2]) {
                sum += (right - p2 + 1) * arr[p1];
            }
            tem[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
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
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,5,4};

          minSum(arr);
    }

}
