package com.ddup.algorithm.basic;

import com.ddup.algorithm.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haowanjin
 * @date 2023/5/13 18:52
 * <p>
 *
 * </p>
 */
public class BSTGenerateTest {
    public static void main(String[] args) {
        System.out.println(numTrees(3));
    }

    public static List<TreeNode<Integer>> generateTree(int n) {

        return buildTree(1, n);
    }

    private static List<TreeNode<Integer>> buildTree(int start, int end) {
        List<TreeNode<Integer>> list = new ArrayList<>();
        if (start > end) {
            list.add(null);
            return list;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode<Integer>> leftNodes = buildTree(start, i - 1);
            List<TreeNode<Integer>> rightNodes = buildTree(i + 1, end);
            for (TreeNode<Integer> leftNode : leftNodes) {
                for (TreeNode<Integer> rightNode : rightNodes) {
                    TreeNode<Integer> root = new TreeNode<>(i);
                    root.setLeft(leftNode);
                    root.setRight(rightNode);
                    list.add(root);
                }
            }
        }
        return list;
    }

    public static int numTrees(int n) {
        int[] arr = new int[n + 1];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 0; j < i; j++) {
                arr[i] += arr[j] * arr[i - j - 1];
            }
        }
        return arr[n];
    }
}
