package com.ddup.algorithm.basic;

import java.util.List;
import java.util.Stack;

public class StackTest {
    public static void main(String[] args) {

    }

    public static Stack<Integer> sortStack(Stack<Integer> list) {
        Stack<Integer> tmp = new Stack<>();
        tmp.push(list.pop());
        while (!list.isEmpty()) {
            int cur = list.pop();
            while (cur > tmp.peek()) {
                list.push(tmp.pop());
            }
            tmp.push(cur);
        }
        while (!tmp.isEmpty()) {
            list.push(tmp.pop());
        }
        return list;
    }
}
