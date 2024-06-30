package com.ddup.algorithm.tree;

import java.util.*;
import java.util.stream.Collectors;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public static TreeNode array2Tree(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        return buildTree(arr, 0);
    }

    public static Integer[] tree2Array(TreeNode root) {
        if (root == null) {
            return new Integer[0];
        }
        List<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    list.add(node.val);
                    queue.add(node.left);
                    queue.add(node.right);
                } else {
                    List<TreeNode> collect = queue.stream().filter(Objects::nonNull).toList();
                    if (!collect.isEmpty()) {
                        list.add(null);
                    }
                }
            }
        }
        return list.toArray(new Integer[0]);
    }

    private static TreeNode buildTree(Integer[] arr, int index) {
        if (index >= arr.length) {
            return null;
        }
        TreeNode node = arr[index] != null ? new TreeNode(arr[index]) : null;
        if (node != null) {
            node.left = buildTree(arr, 2 * index + 1);
            node.right = buildTree(arr, 2 * index + 2);
        }
        return node;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null)
            return root;
        if (left != null)
            return left;
        return right;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        int x = root.val;
        if (p.val < x && q.val < x) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (p.val > x && q.val > x) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }

    String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {

        List<String> res = new ArrayList<>();
        if (digits.isEmpty()) {
            res.add("");
            return res;
        }
        char[] chars = new char[digits.length()];
        dif(0, digits, res, chars);

        return res;
    }

    public void dif(int i, String digits, List<String> res, char[] chars) {
        if (i == digits.length()) {
            res.add(new String(chars));
            return;
        }
        int idx = Integer.parseInt(digits.charAt(i) + "");
        String ss = map[idx];
        for (int j = 0; j < ss.length(); j++) {
            chars[i] = ss.charAt(j);
            dif(i + 1, digits, res, chars);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        TreeNode node = array2Tree(arr);
        Integer[] integers = tree2Array(node);
        System.out.println(Arrays.toString(integers));

        StringBuilder sb = new StringBuilder(2);
        sb.insert(0, "1");
        sb.insert(1, "2");

        System.out.println(sb);
        sb.delete(0, sb.length());
        System.out.println(sb);
        sb.insert(0, "2");
        sb.insert(1, "3");
        System.out.println(sb);
    }
}
