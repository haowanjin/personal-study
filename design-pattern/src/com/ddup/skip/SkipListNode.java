package com.ddup.skip;

import lombok.Data;

/**
 * @author: hwj
 * @Description TODO
 * @create: 2021/8/31 15:53
 */
@Data
public class SkipListNode<T> {
    //索引层
    private SkipListLevel[] level;

    //后退指针
    private SkipListNode<T> backWord;

    //分值
    private double score;

    // 成员对象
    private T obj;

    // 初始化头结点
    public SkipListNode(T obj) {
        this.obj = obj;
        this.level = new SkipListLevel[32];
        initLevel(this.level, 32);
        this.score = 0;
    }

    // 根据"层高"和"分值"，新建一个节点
    public SkipListNode(T obj, int levelHeight, double score) {
        this.obj = obj;
        this.level = new SkipListLevel[levelHeight];
        initLevel(this.level, levelHeight);
        this.score = score;
    }

    private void initLevel(SkipListLevel[] level, int height) {
        for (int i = 0; i < height; ++i) {
            level[i] = new SkipListLevel();
        }
    }
}
