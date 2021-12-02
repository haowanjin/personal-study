package com.ddup.algorithm;

import com.ddup.algorithm.modal.TreeNode;

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

    public static void morrisePre(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.getLeftTreeNode();
            if (mostRight != null) {
                while (mostRight.getRightTreeNode() != null && mostRight.getRightTreeNode() != cur) {
                    mostRight = mostRight.getRightTreeNode();
                }
                if (mostRight.getRightTreeNode() == null) {
                    mostRight.setRightTreeNode(cur);
                    System.out.print(cur.getData() + " ");
                    cur = cur.getLeftTreeNode();
                    continue;
                } else {
                    mostRight.setRightTreeNode(null);
                }
            } else {// 没有左子树
                System.out.print(cur.getData() + " ");
            }
            cur = cur.getRightTreeNode();
        }
        System.out.println();
    }

    public static void morriseIn(TreeNode head) { // 中序遍历
        if (head == null) {
            return;
        }
        TreeNode cur = head;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.getLeftTreeNode();
            if (mostRight != null) {// 有左子树
                while (mostRight.getRightTreeNode() != null && mostRight.getRightTreeNode() != cur) { // 查找左子树的最右节点
                    mostRight = mostRight.getRightTreeNode();
                }
                if (mostRight.getRightTreeNode() == null) {// 最右节点的右节点为空，令其指向当前节点
                    mostRight.setRightTreeNode(cur);
                    cur = cur.getLeftTreeNode(); // 当前节点左移
                    continue;
                } else { // 最右节点为当前节点，令其为空
                    mostRight.setRightTreeNode(null);
                }
            }
            // 打印当前节点，当前节点右移(此处打印包含第一次到达和第二次到达)
            System.out.print(cur.getData() + " ");
            cur = cur.getRightTreeNode();
        }
    }

    public static RetType process(TreeNode head) {
        if (head == null) {
            return new RetType(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        RetType left = process(head.getLeftTreeNode());
        RetType right = process(head.getRightTreeNode());

        return new RetType(Math.max(left.max, right.max), Math.min(left.min, right.min));
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
