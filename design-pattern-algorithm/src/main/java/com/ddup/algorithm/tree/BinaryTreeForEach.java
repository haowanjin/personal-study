package com.ddup.algorithm.tree;

import com.ddup.algorithm.model.TreeNode;

import java.util.Stack;

public class BinaryTreeForEach {
    public TreeNode<Integer> init() {//注意必须逆序建立，先建立子节点，再逆序往上建立，因为非叶子结点会使用到下面的节点，而初始化是按顺序初始化的，不逆序建立会报错
        TreeNode<Integer> J = new TreeNode<>(8, null, null);
        TreeNode<Integer> H = new TreeNode<>(4, null, null);
        TreeNode<Integer> G = new TreeNode<>(2, null, null);
        TreeNode<Integer> F = new TreeNode<>(7, null, J);
        TreeNode<Integer> E = new TreeNode<>(5, H, null);
        TreeNode<Integer> D = new TreeNode<>(1, null, G);
        TreeNode<Integer> C = new TreeNode<>(9, F, null);
        TreeNode<Integer> B = new TreeNode<>(3, D, E);
        return new TreeNode<>(6, B, C);   //返回根节点
    }

    public void printNode(TreeNode<Integer> node) {
        System.out.print(node.getData() + " ");
    }

    public void theFirstTraversal(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        printNode(root);
        theFirstTraversal(root.getLeft());
        theFirstTraversal(root.getRight());
    }

    public void theCenterTraversal(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        theCenterTraversal(root.getLeft());
        printNode(root);
        theCenterTraversal(root.getRight());
    }


    public void theAfterTraversal(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        theAfterTraversal(root.getLeft());
        theAfterTraversal(root.getRight());
        printNode(root);
    }

    public void firstPrint(TreeNode<Integer> root) {
        Stack<TreeNode<Integer>> stack = new Stack<>();
        TreeNode<Integer> node = root;
        while (node != null || !stack.empty()) {
            if (node != null) {
                printNode(node);
                stack.push(node);
                node = node.getLeft();
            } else {
                node = stack.pop();
                node = node.getRight();
            }
        }
    }

    public void centerPrint(TreeNode<Integer> root) {
        Stack<TreeNode<Integer>> stack = new Stack<>();
        TreeNode<Integer> node = root;
        while (node != null || !stack.empty()) {
            if (node != null) {
                stack.push(node);
                node = node.getLeft();
            } else {
                node = stack.pop();
                printNode(node);
                node = node.getRight();
            }
        }
    }

    public void afterPrint(TreeNode<Integer> root) {
        Stack<TreeNode<Integer>> stack = new Stack<>();
        Stack<TreeNode<Integer>> outPut = new Stack<>();
        TreeNode<Integer> node = root;
        while (node != null || !stack.empty()) {
            if (node != null) {
                outPut.push(node);
                stack.push(node);
                node = node.getRight();
            } else {
                node = stack.pop();
                node = node.getLeft();
            }
        }
        while (!outPut.empty()) {
            printNode(outPut.pop());
        }
    }

    public static void morrisePre(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        TreeNode<Integer> cur = root;
        TreeNode<Integer> mostRight = null;
        while (cur != null) {
            mostRight = cur.getLeft();
            if (mostRight != null) {
                while (mostRight.getRight() != null && mostRight.getRight() != cur) {
                    mostRight = mostRight.getRight();
                }
                if (mostRight.getRight() == null) {
                    mostRight.setRight(cur);
                    System.out.print(cur.getData() + " ");
                    cur = cur.getLeft();
                    continue;
                } else {
                    mostRight.setRight(null);
                }
            } else {// 没有左子树
                System.out.print(cur.getData() + " ");
            }
            cur = cur.getRight();
        }
        System.out.println();
    }

    public static void morriseIn(TreeNode<Integer> head) { // 中序遍历
        if (head == null) {
            return;
        }
        TreeNode<Integer> cur = head;
        TreeNode<Integer> mostRight = null;
        while (cur != null) {
            mostRight = cur.getLeft();
            if (mostRight != null) {// 有左子树
                while (mostRight.getRight() != null && mostRight.getRight() != cur) { // 查找左子树的最右节点
                    mostRight = mostRight.getRight();
                }
                if (mostRight.getRight() == null) {// 最右节点的右节点为空，令其指向当前节点
                    mostRight.setRight(cur);
                    cur = cur.getLeft(); // 当前节点左移
                    continue;
                } else { // 最右节点为当前节点，令其为空
                    mostRight.setRight(null);
                }
            }
            // 打印当前节点，当前节点右移(此处打印包含第一次到达和第二次到达)
            System.out.print(cur.getData() + " ");
            cur = cur.getRight();
        }
    }

    public static RetType process(TreeNode<Integer> head) {
        if (head == null) {
            return new RetType(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        RetType left = process(head.getLeft());
        RetType right = process(head.getRight());

        return new RetType(Math.max(left.max, right.max), Math.min(left.min, right.min));
    }

    public static void main(String[] args) {
        BinaryTreeForEach tree = new BinaryTreeForEach();
        TreeNode<Integer> treeNode = tree.init();
        System.out.println("树的高度 = " + treeNode.getHeight());
        tree.theFirstTraversal(treeNode);
        System.out.println();
        tree.theCenterTraversal(treeNode);
        System.out.println();
        tree.theAfterTraversal(treeNode);

        System.out.println("\n----------------------------------");
        tree.firstPrint(treeNode);
        System.out.println();
        tree.centerPrint(treeNode);
        System.out.println();
        tree.afterPrint(treeNode);

        System.out.println("\n --------------------------- \nmorrise pre ");
        morrisePre(treeNode);
        System.out.println("\n --------------------------- \nmorrise in ");
        morriseIn(treeNode);
    }

    static class RetType {
        int max, min;
        public RetType(int max, int min) {
            this.min = min;
            this.max = max;
        }
    }
}
