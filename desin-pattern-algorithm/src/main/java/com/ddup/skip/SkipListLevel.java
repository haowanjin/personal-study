package com.ddup.skip;

/**
 * @author: hwj
 * @Description TODO
 * @create: 2021/8/31 15:53
 */
public class SkipListLevel {
    //前进指针
    private SkipListNode forward;
    //跨度
    private int span;

    public SkipListNode getForward() {
        return forward;
    }

    public void setForward(SkipListNode forward) {
        this.forward = forward;
    }

    public int getSpan() {
        return span;
    }

    public void setSpan(int span) {
        this.span = span;
    }

    public static void main(String[] args) {
        System.out.println(256 >> 2);
    }
}
