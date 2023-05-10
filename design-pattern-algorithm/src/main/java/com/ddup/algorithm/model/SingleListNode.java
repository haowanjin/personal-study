package com.ddup.algorithm.model;

import lombok.Data;

/**
 * @author haowanjin
 * @date 2023/5/10 22:24
 * <p>
 *
 * </p>
 */
@Data
public class SingleListNode<V> {
    private V value;
    private SingleListNode<V> next;

    public SingleListNode(V value) {
        this.value = value;
    }

    public SingleListNode(V value, SingleListNode<V> next) {
        this.value = value;
        this.next = next;
    }

}
