package com.ddup.calculation;

import com.ddup.calculation.modal.TreeNode;

import java.util.Stack;

public class BinaryTreeForEach {
    public TreeNode init() {//注意必须逆序建立，先建立子节点，再逆序往上建立，因为非叶子结点会使用到下面的节点，而初始化是按顺序初始化的，不逆序建立会报错  
        TreeNode J = new TreeNode(8, null, null);
        TreeNode H = new TreeNode(4, null, null);
        TreeNode G = new TreeNode(2, null, null);
        TreeNode F = new TreeNode(7, null, J);
        TreeNode E = new TreeNode(5, H, null);
        TreeNode D = new TreeNode(1, null, G);
        TreeNode C = new TreeNode(9, F, null);
        TreeNode B = new TreeNode(3, D, E);
        TreeNode A = new TreeNode(6, B, C);
        return A;   //返回根节点  
    }

    public void printNode(TreeNode node) {
        System.out.print(node.getData() + " ");
    }

    public void theFirstTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        printNode(root);
        theFirstTraversal(root.getLeftTreeNode());
        theFirstTraversal(root.getRightTreeNode());
    }

    public void theCenterTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        theCenterTraversal(root.getLeftTreeNode());
        printNode(root);
        theCenterTraversal(root.getRightTreeNode());
    }


    public void theAfterTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        theAfterTraversal(root.getLeftTreeNode());
        theAfterTraversal(root.getRightTreeNode());
        printNode(root);
    }

    public void firstPrint(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.empty()) {
            if (node != null) {
                printNode(node);
                stack.push(node);
                node = node.getLeftTreeNode();
            } else {
                node = stack.pop();
                node = node.getRightTreeNode();
            }
        }
    }

    public void centerPrint(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.empty()) {
            if (node != null) {
                stack.push(node);
                node = node.getLeftTreeNode();
            } else {
                node = stack.pop();
                printNode(node);
                node = node.getRightTreeNode();
            }
        }
    }

    public void afterPrint(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> outPut = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.empty()) {
            if (node != null) {
                outPut.push(node);
                stack.push(node);
                node = node.getRightTreeNode();
            } else {
                node = stack.pop();
                node = node.getLeftTreeNode();
            }
        }
        while (!outPut.empty()) {
            printNode(outPut.pop());
        }
    }


    public static void main(String[] args) {
        BinaryTreeForEach tree = new BinaryTreeForEach();
        TreeNode treeNode = tree.init();
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
    }
}
