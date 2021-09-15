package com.ddup.skip;

import lombok.Data;

/**
 * @author: hwj
 * @Description TODO
 * @create: 2021/8/31 15:53
 */
@Data
public class SkipListLevel {
    //前进指针
    private SkipListNode forward;
    //跨度
    private int span;

    public static void main(String[] args) {
        System.out.println(256 >> 2);
    }
}
