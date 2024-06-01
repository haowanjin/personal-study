package com.ddup.algorithm.node;

public class NodeTest {
    public static void main(String[] args) {
        LinkedNode n6 = new LinkedNode(6, null);
        LinkedNode n5 = new LinkedNode(5, n6);
        LinkedNode n4 = new LinkedNode(4, n5);
        LinkedNode n3 = new LinkedNode(3, n4);
        LinkedNode n2 = new LinkedNode(2, n3);
        LinkedNode n1 = new LinkedNode(1, n2);

        LinkedNode res = reverseNode(n1);
        System.out.println(res);


        System.out.println(removeNum(new int[]{1, 3, 2, 5, 6, 3,3, 3, 3,}, 3));

    }

    public static LinkedNode swapNode(LinkedNode head) {
        LinkedNode res = new LinkedNode(0, head);
        LinkedNode cur = res;
        LinkedNode nxt;
        LinkedNode tmp;
        while (cur.getNext() != null && cur.getNext().getNext() != null) {
            nxt = head.getNext();
            tmp = nxt.getNext();
            nxt.setNext(head);
            head.setNext(tmp);
            cur.setNext(nxt);
            cur = head;

            head = head.getNext();
        }
        return res.getNext();
    }

    public static LinkedNode reverseNode(LinkedNode head) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        LinkedNode next = head.getNext();
        head.setNext(reverseNode(next.getNext()));
        next.setNext(head);
        return next;
    }


    public static int removeNum(int[] arr, int val) {
        int l = 0, r = arr.length - 1;
        while (l != r) {
            while (l < r && arr[l] != val) {
                l++;
            }
            while (r > l && arr[r] == val) {
                r--;
            }
            int tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
        }
        return arr[l] == val ? l : l + 1;
    }
}
