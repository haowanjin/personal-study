package com.ddup.leetCode;

import java.util.*;

/**
 * @author haowanjin
 * @date 2023/4/26 15:06
 * <p>
 *
 * </p>
 */
public class HWTest {
    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        root.left = t2;
        root.right = t3;
        t2.left = t4;
        t2.right = t5;
        System.out.println(calcTreeLength1(root));
//
//        /*19
//        53
//        26*/
////        calcWater(57);
////        int[] arr = {5, 2, 3, 1, 4};
//        int[] arr = {0, 4, 3, 0};
//
//        System.out.println(Arrays.toString(twoSum(arr, 0)));
////        int[] arr = {2, 2, 1, 3, 5, 6, 3, 1, 5, 6, 0};
//      /*  quickSort(arr);
//        System.out.println(Arrays.toString(arr));*/
////        delRepeatNum(arr);
////        System.out.println(str2Int("0xAA"));
//        ListNode n1 = new ListNode(1, null);
//        ListNode n2 = new ListNode(2, n1);
//        ListNode n3 = new ListNode(3, n2);
//        ListNode n4 = new ListNode(4, n3);
//        ListNode n5 = new ListNode(5, n4);
//        n1.next = n5;
//        ListNode n6 = new ListNode(6, n5);
//        ListNode n7 = new ListNode(7, n6);
//        ListNode head = new ListNode(8, n7);
//
////        System.out.println(head);
////        ListNode res = reverseNode(head);
////        System.out.println(res);
////        System.out.println(isLoopListNode2(head));
////        System.out.println(loopNode2(head));
//        System.out.println(decimal2HexStr(1000));
//        System.out.println(Integer.toHexString(1000));
//        System.out.println(hexStr2Decimal("0x3E8"));
//        System.out.println(Integer.valueOf("3E8", 16));


    }

    public static void calcWater(int n) {
        if (n == 0) {
            return;
        }
        int sum = 0;
        // 已经有的空汽水瓶可以喝的汽水
        int m = n / 3;
        //剩余空汽水瓶
        int x = n % 3;
        int tol;
        while (m > 0) {
            sum += m;
            tol = m + x;
            m = tol / 3;
            x = tol % 3;
        }
        if (x == 2) {
            sum += 1;
        }

        System.out.println(sum);
    }

    public static void calCharNum(String str) {
        char[] chars = str.toCharArray();
        Set<Character> set = new HashSet<>();
        for (char c : chars) {
            if (c >= 0 && c <= 127) {
                set.add(c);
            }
        }
        System.out.println(set.size());
    }

    public static void quickSort(int[] arr) {
        quickSort2(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int l, int h) {
        if (l < h) {
            int p = process(arr, l, h);
            quickSort(arr, l, p - 1);
            quickSort(arr, p + 1, h);
        }
    }

    private static String decimal2HexStr(int decimal) {
        StringBuilder sb = new StringBuilder();
        int shang = decimal;
        int yushu;
        int hex = 16;

        while (true) {
            shang = decimal / hex;
            yushu = decimal % hex;
            if (yushu >= 10 && yushu < hex) {
                sb.append(('A' + (yushu - 10)));
            } else {
                sb.append(yushu);
            }
            if (shang == 0) {
                break;
            }
            decimal = shang;
        }

        return sb.reverse().toString();
    }

    public static void bitSet() {
        BitSet bitSet = new BitSet(128);
        System.out.println(bitSet);
        System.out.println(bitSet.get('c'));
        bitSet.set('c');
        System.out.println(bitSet.get('c'));
    }

    /**
     * 快速排序，将大于目标值的数放在右边，小于等于目标值得数挪到左边
     *
     * @param arr
     * @param l
     * @param h
     * @return
     */
    private static int process(int[] arr, int l, int h) {
        /*int idx = l + (h - l) / 2;
        int tmp = arr[idx];
        swap(arr, l, idx);
        int i = l, j = h;
        while (i < j) {
            while (i < j&& arr[j] > tmp) {
                j--;
            }
            while ( i < j && arr[i] <= tmp) {
                i++;
            }

            if (i < j) {
                swap(arr, i, j);
            }
        }
        swap(arr, l, i);
        return i;*/
        if (l >= h) {
            return l;
        }
        int i = l - 1, j = h + 1;
        while (i < j) {
            while (i < j && arr[--j] > arr[l]) ;
            while (i < j && arr[++i] <= arr[l]) ;
            if (i < j) {
                swap(arr, i, j);
            }
        }
        swap(arr, l, i);
        return i;
    }

    public static void quickSort2(int[] arr, int l, int h) {
        if (l < h) {
            int p = partition(arr, l, h);
            quickSort2(arr, l, p - 1);
            quickSort2(arr, p + 1, h);
        }
    }

    /**
     * 青蛙跳台阶 f(n) = f(n-1)+f(n-2)
     *
     * @param target
     * @return
     */
    public static int jumpFloor(int target) {
        int a = 1, b = 1, c = 1;
        for (int i = 2; i <= target; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    public static int partition(int[] arr, int l, int h) {
        int i = l - 1, j = h + 1;
        while (i < j) {
            while (i < j && arr[--j] > arr[l]) ;
            while (i < j && arr[++i] <= arr[l]) ;
            if (i < j) {
                swap(arr, i, j);
            }
        }
        swap(arr, l, i);
        return i;
    }

    /**
     * 合并两个升序链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoListNode(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode resultNode = new ListNode(0);
        ListNode p = resultNode;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 != null) {
            p.next = l1;
        }
        if (l2 != null) {
            p.next = l2;
        }
        return resultNode.next;
    }


    /**
     * 两数之和
     *
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            Integer pre = map.get(target - numbers[i]);
            if (pre != null && pre != i) {
                return new int[]{pre, i};
            }
            map.put(numbers[i], i);
        }
        return null;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 打印不重复数字
     *
     * @param arr
     */
    public static void delRepeatNum(int[] arr) {
        int x = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (x != arr[i]) {
                System.out.print(arr[i] + "\t");
                x = arr[i];
            }
        }
    }

    /**
     * 十六进制字符串转十进制数字
     *
     * @param str
     * @return
     */
    public static int hexStr2Decimal(String str) {
        String hex = str.substring(2).toUpperCase(Locale.ROOT);
        int num = 0;
        int j = 0;
        for (int i = hex.length() - 1; i >= 0; i--) {
            char c = hex.charAt(i);
            if (c >= 'A' && c <= 'F') {
                num += (c - 'A' + 10) * Math.pow(16, j++);
            } else {
                num += Integer.parseInt(hex.substring(i, i + 1)) * Math.pow(16, j++);
            }

        }
        return num;
    }

    /**
     * 链表反转
     * 当前链表指向null，当前链表的next指向当前链表
     *
     * @param head
     * @return
     */
    public static ListNode reverseNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        ListNode next = head.next;
        ListNode res = reverseNode(next);
        ListNode resNext = res;
        cur.next = null;
        while (resNext.next != null) resNext = resNext.next;

        resNext.next = cur;
        return res;
    }

    public static boolean isLoopListNode(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (slow.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public static boolean isLoopListNode2(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }

        return false;
    }

    public static ListNode loopNode(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (slow.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }

    public static ListNode loopNode2(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return head;
            }
            set.add(head);
            head = head.next;
        }

        return null;
    }

    public static int run(TreeNode root) {
        // 计算左子树的长度
        //计算右子树的长度
        //返回两者最小
        return calcTreeLength(root);
    }

    private static int calcTreeLength(TreeNode root) {
        if (root == null) return 0;
        int leftLen = 1;
        int rightLen = 1;
        if (root.left != null) {
            leftLen += calcTreeLength(root.left);
        }
        if (root.right != null) {
            rightLen += calcTreeLength(root.right);
        }
        if (root.left == null) {
            return rightLen;
        }
        if (root.right == null) {
            return leftLen;
        }
        return Math.min(leftLen, rightLen);
    }

    private static int calcTreeLength1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int depth = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (cur.left == null && cur.right == null) {
                    return depth;
                }
                if (cur.left != null) {
                    q.add(cur.left);
                }
                if (cur.right != null) {
                    q.add(cur.right);
                }
            }
            depth++;
        }
        return depth;
    }
}


class ListNode {
    int val;
    ListNode next = null;
    private boolean flag = false;

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        String s = this.val + "";
        /*ListNode next = this.next;
        while (next != null) {
            s += " " + next.val;

            next = next.next;
        }*/
        return s;
    }
}

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}

