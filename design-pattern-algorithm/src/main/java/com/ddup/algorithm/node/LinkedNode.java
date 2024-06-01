package com.ddup.algorithm.node;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkedNode {
    private int val;

    private LinkedNode next;

    public String toString() {
        StringBuilder res = new StringBuilder(val + "->");
        LinkedNode tmp = next;
        while (tmp != null) {
            res.append(tmp.val).append("->");
            tmp = tmp.next;
        }
        return res.substring(0, res.length() - 2);
    }
}
