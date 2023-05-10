package com.ddup.algorithm.model;

import lombok.Data;

import java.util.List;

/**
 * @author haowanjin
 * @date 2023/5/10 22:25
 * <p>
 *
 * </p>
 */

@Data
public class ListNode<V> {
    private V data;
    private ListNode<V> pre;
    private ListNode<V> next;

    public ListNode(V data) {
        this.data = data;
    }

    public ListNode(V data, ListNode<V> pre, ListNode<V> next) {
        this.data = data;
        this.pre = pre;
        this.next = next;
    }
}
