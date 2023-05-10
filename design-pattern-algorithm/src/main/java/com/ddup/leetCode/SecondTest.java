package com.ddup.leetCode;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author haowanjin
 * @date 2023/5/8 18:09
 * <p>
 *
 * </p>
 */
public class SecondTest {
    public static void main(String[] args) {
        LRUCache lru = new LRUCache(2);
        lru.put(1, 1);
        lru.put(2, 2);
        System.out.println(lru);
        lru.get(1);
        System.out.println(lru);
        lru.put(3, 3);
        System.out.println(lru);

        int[] nums = {2, 1, 1, 2};
        System.out.println(thief(nums));

        Integer[] treeData = {1, 3, 2, 5, 3, null, 9};
        System.out.println(calcTreeWidth(treeData));
    }

    /**
     * 偷窃沿街房屋
     *
     * @param nums
     * @return
     */
    public static int thief(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        for (int i = 2; i < nums.length ; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }

    public static int calcTreeWidth(Integer[] treeData) {
        TreeNode[] treeNodes = new TreeNode[treeData.length];
        for (int i = 0; i < treeData.length; i++) {
            if (treeData[i] == null) {
                treeNodes[i] = null;
            } else {
                treeNodes[i] = new TreeNode(treeData[i]);
            }
        }
        for (int i = 0; i < treeData.length; i++) {
            if (treeNodes[i] != null) {
                if (2 * i + 1 < treeData.length) {
                    treeNodes[i].left = treeNodes[2 * i + 1];
                }
                if (2 * i + 2 < treeData.length) {
                    treeNodes[i].right = treeNodes[2 * i + 2];
                }
            }
        }
        return calcTreeWidth(treeNodes[0]);
    }


    /**
     * 计算二叉树的宽度
     *
     * @param root
     * @return
     */
    public static int calcTreeWidth(TreeNode root) {
        //TODO 广度遍历、深度遍历还没深入学习，目前不太熟悉
        int res = 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        root.data = 1;
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelCount = queue.size();//一层节点数
            int left = queue.peekFirst().data;
            int right = queue.peekLast().data;
            res = Math.max(res, right - left + 1);
            for (int i = 0; i < levelCount; i++) {
                TreeNode tree = queue.poll();
                if (tree.left != null) {
                    queue.add(tree.left);
                    tree.left.data = 2 * tree.data;
                }
                if (tree.right != null) {
                    queue.add(tree.right);
                    tree.right.data = 2 * tree.data + 1;
                }
            }
        }
        return res;
    }


    private static class LRUCache {
        private int size;
        private int capacity;
        private CacheNode head;
        private CacheNode tail;

        public LRUCache(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            head = new CacheNode(0, 0);
            tail = new CacheNode(0, 0);
            head.next = tail;
            tail.pre = head;
        }

        private HashMap<Integer, CacheNode> cache = new HashMap<>();

        public void put(int key, int value) {
            if (cache.containsKey(key)) {// 若果存在key，更新value
                cache.get(key).value = value;
            } else {
                // 不存在则添加
                addNode(new CacheNode(key, value));
                if (size > capacity) {
                    // 判断size是否大于capacity,如果大于则删除最后一次使用过得node
                    removeNode(tail.pre);
                }
            }
            move2Head(cache.get(key));
        }

        public int get(int key) {
            CacheNode node = cache.get(key);
            if (node == null) {
                return -1;
            }
            move2Head(node);
            return node.value;
        }

        private void removeNode(CacheNode node) {
            cache.remove(node.key);
            CacheNode pre = node.pre;
            CacheNode next = node.next;
            pre.next = next;
            next.pre = pre;
            size--;
        }

        private void addNode(CacheNode node) {
            node.pre = head;
            node.next = head.next;
            head.next.pre = node;
            head.next = node;
            cache.put(node.key, node);
            size++;
        }

        private void move2Head(CacheNode node) {
            CacheNode pre = node.pre;
            CacheNode next = node.next;
            next.pre = pre;
            pre.next = next;
            node.pre = head;
            node.next = head.next;
            head.next.pre = node;
            head.next = node;
        }

        @Override
        public String toString() {
            CacheNode node = head.next;
            StringBuilder res = new StringBuilder();
            while (node != tail) {
                res.append(node).append(",");
                node = node.next;
            }
            if (res.length() > 0) {
                res = new StringBuilder(res.substring(0, res.length() - 1));
            }
            return res.toString();
        }
    }

    private static class CacheNode {
        private int key;
        private int value;
        private CacheNode pre;
        private CacheNode next;

        public CacheNode(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "key=" + key + " value = " + value;
        }
    }

    private static class TreeNode {
        private int data;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int data) {
            this.data = data;
        }
    }
}
