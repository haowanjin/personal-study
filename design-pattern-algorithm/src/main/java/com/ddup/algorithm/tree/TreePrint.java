package com.ddup.algorithm.tree;

import com.ddup.algorithm.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author haowanjin
 * @date 2023/5/10 22:09
 * <p>
 *
 * </p>
 */
public class TreePrint<V> {

    /**
     * 先序遍历
     *
     * @param root
     */
    public void preOrderPrint(TreeNode<V> root) {
        System.out.println(root.getData());
        preOrderPrint(root.getLeft());
        preOrderPrint(root.getRight());
    }

    /**
     * 中序遍历
     *
     * @param root
     */
    public void inOrderPrint(TreeNode<V> root) {
        preOrderPrint(root.getLeft());
        System.out.println(root.getData());
        preOrderPrint(root.getRight());
    }

    /**
     * 后序遍历
     *
     * @param root
     */
    public void postOrderPrint(TreeNode<V> root) {
        preOrderPrint(root.getLeft());
        System.out.println(root.getData());
        preOrderPrint(root.getRight());
    }

    /**
     * 非递归先序遍历
     *
     * @param root
     */
    public void preOrderPrintNoRecur(TreeNode<V> root) {
        if (root != null) {
            Stack<TreeNode<V>> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                root = stack.pop();
                System.out.println(root.getData());
                if (root.getRight() != null) {
                    stack.push(root.getRight());
                }
                if (root.getLeft() != null) {
                    stack.push(root.getLeft());
                }
            }
        }
    }

    /**
     * 非递归中序遍历
     *
     * @param root
     */
    public void inOrderPrintNoRecur(TreeNode<V> root) {
        if (root != null) {
            Stack<TreeNode<V>> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty() || root != null) {
                if (root.getLeft() != null) {
                    stack.push(root.getLeft());
                    root = root.getLeft();
                } else {
                    root = stack.pop();
                    System.out.println(root.getData());
                    root = root.getRight();
                }
            }
        }
    }

    /**
     * 非递归后续遍历
     *
     * @param root
     */
    public void postOrderPrintNoRecur(TreeNode<V> root) {
        if (root != null) {
            Stack<TreeNode<V>> stack = new Stack<>();
            Stack<TreeNode<V>> printStack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                root = stack.pop();
                printStack.push(root);
                if (root.getLeft() != null) {
                    stack.push(root.getLeft());
                }
                if (root.getRight() != null) {
                    stack.push(root.getRight());
                }
            }
            while (!printStack.isEmpty()) {
                System.out.println(printStack.pop());
            }
        }
    }

    /**
     * 层序遍历
     * @param root
     */
    public void layoutPrint(TreeNode<V> root) {
        if (root != null) {
            Queue<TreeNode<V>> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode<V> cur = queue.poll();
                System.out.println(cur.getData());
                if (cur.getLeft() != null) {
                    queue.add(cur.getLeft());
                }
                if (cur.getRight() != null) {
                    queue.add(cur.getRight());
                }
            }
        }
    }
}
