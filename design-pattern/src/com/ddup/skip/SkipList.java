package com.ddup.skip;


import lombok.Data;

import java.util.Random;

/**
 * @author: hwj
 * @Description TODO
 * @create: 2021/8/31 15:55
 */
@Data
public class SkipList<T extends Comparable<? super T>> {
    //首尾结点的指针
    private SkipListNode<T> header;
    private SkipListNode<T> tail;
    //记录跳表中结点数量
    private long size;
    //最大结点的层数
    private int maxLevel;

    //构造方法初始化SkipList
    public SkipList() {
        SkipListNode<T> node = new SkipListNode<>(null);
        this.header = node;
        this.tail = node;
        this.size = 0;
        this.maxLevel = 0;
    }

    /**
     * 获取随机的层高度
     *
     * @return
     */
    private int getRandomHeight() {
        Random random = new Random();
        int i = 1;
        for (; i < 32; ++i) {
            if (random.nextInt(2) == 0) {
                break;
            }
        }
        return i;
    }

    public SkipListNode slInsert(double score, T obj) {
        int levelHeight = getRandomHeight();
        SkipListNode<T> target = new SkipListNode<>(obj, levelHeight, score);
        // update[i] 记录所有需要进行调整的前置位节点
        SkipListNode[] update = new SkipListNode[Math.max(levelHeight, maxLevel)];
        int[] rank = new int[update.length];//记录每一个update节点的排位
        int i = update.length - 1;
        if (levelHeight > maxLevel) {
            for (; i >= maxLevel; --i) {
                update[i] = header;
                rank[i] = 0;
            }
            maxLevel = levelHeight;
        }
        for (; i >= 0; --i) {

            SkipListNode<T> node = header;
            SkipListNode<T> next = node.getLevel()[i].getForward();
            rank[i] = 0;
            //遍历得到与target最接近的节点(左侧)
            while (next != null && (score > next.getScore()
                    || score == next.getScore() && next.getObj().compareTo(obj) < 0)) {
                rank[i] += node.getLevel()[i].getSpan();
                node = next;
                next = node.getLevel()[i].getForward();

            }
            update[i] = node;
        }

        //当maxLevel>levelHeight，前面部分节点的span值加1，因为该节点与forword指向节点之间将要 多出来一个新节点
        for (i = update.length - 1; i >= levelHeight; --i) {
            int span = update[i].getLevel()[i].getSpan();
            update[i].getLevel()[i].setSpan(++span);
        }
        //遍历 update[] 进行插入和更新操作
        for (; i >= 0; --i) {

            SkipListLevel pre = update[i].getLevel()[i];
            //将target节点插入update[i]和temp之间
            SkipListNode<T> temp = pre.getForward();
            int span = pre.getSpan();

            pre.setForward(target);
            pre.setSpan(rank[0] + 1 - rank[i]);

            target.getLevel()[i].setSpan(span > 0 ? (span - rank[0] + rank[i]) : 0);
            target.getLevel()[i].setForward(temp);
            //设置后退指针
            if (temp == null) {
                target.setBackWord(header);
            } else {
                target.setBackWord(temp.getBackWord());
                temp.setBackWord(target);
            }

        }

        if (tail.getLevel()[0].getForward() != null) {
            tail = target;
        }
        size++;
        return target;

    }
    /**
     * 删除节点
     * @param obj
     * @return 删除的节点(若节点不存在则返回null)
     */
    public SkipListNode zslDelete(double score, T obj) {
        SkipListNode[] update = new SkipListNode[maxLevel];
        SkipListNode<T> node = header;
        for (int i = maxLevel - 1; i >= 0; --i) {
            SkipListNode<T> next = node.getLevel()[i].getForward();
            //遍历得到与target最接近的节点
            while (next != null && (score > next.getScore() || score == next.getScore() && next.getObj().compareTo(obj) < 0)) {
                node = next;
                next = node.getLevel()[i].getForward();
            }
            update[i] = node;
        }
        //待删除的目标节点
        SkipListNode<T> target = update[0].getLevel()[0].getForward();
        if(target==null) return null;

        for (int i = maxLevel - 1; i >= 0; --i) {
            SkipListLevel current = update[i].getLevel()[i];
            SkipListNode<T> next = current.getForward();
            if (next == null) continue;
            if (next != target) {
                current.setSpan(-1);
                continue;
            }
            current.setForward(target.getLevel()[i].getForward());
            if(current.getForward()!=null)
                current.setSpan(target.getLevel()[i].getSpan() - 1);
            else
                current.setSpan(0);
        }
        size--;
        while(header.getLevel()[maxLevel-1].getSpan()==0){
            maxLevel--;
        }
        return target;
    }

    // 根据分值范围 fromScore~toScore，返回第一个符合范围的节点
    public SkipListNode<T> zslFirstInRange(double fromScore, double toScore, SkipListNode<T> node, int k) {
        if (!zslIsInRange(fromScore, toScore)) {
            return null;
        }

        SkipListNode<T> next = node.getLevel()[k].getForward();

        if (next == null || next.getScore() >= fromScore) {
            if (k == 0) return next != null && next.getScore() > toScore ? null : next;
            return zslFirstInRange(fromScore, toScore, node, k - 1);
        }
        return zslFirstInRange(fromScore, toScore, next, k);
    }

    // 根据分值范围，返回最后一个符合范围的节点
    public SkipListNode<T> zslLastInRange(double fromScore, double toScore, SkipListNode<T> node, int k) {
        if (!zslIsInRange(fromScore, toScore)) {
            return null;
        }

        SkipListNode<T> next = node.getLevel()[k].getForward();

        if (next == null || next.getScore() > toScore) {
            if (k == 0) return next != null && next.getScore() < fromScore ? null : node;
            return zslLastInRange(fromScore, toScore, node, k - 1);
        }
        return zslLastInRange(fromScore, toScore, next, k);
    }

    private boolean zslIsInRange(double fromScore, double toScore) {
        return false;
    }
}
