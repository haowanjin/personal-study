package com.ddup.algorithm;

/**
 * @author: haowanjin
 * @Description TODO
 * @create: 2022/3/1 9:47
 */
public class ListNodeReverse {
    public static ListNode reserve(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode newNode = reserve(node.next);
        node.next.next = node;
        node.next = null;
        return newNode;
    }

    public static void main(String[] args) {
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        ListNode reserve = reserve(node1);
        System.out.println(reserve);

    }


    static class ListNode {
        int value;
        ListNode next;

        public ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }
    }
}
