package com.ddup.leetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author haowanjin
 * @date 2023/5/6 14:37
 * <p>
 *
 * </p>
 */
public class MyStack {
    Queue<Integer> queue1 = new LinkedList<>();
    Queue<Integer> queue2 = new LinkedList<>();


    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        System.out.println(stack.top());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    public void push(int element) {
        queue1.add(element);
    }

    public int pop() {
        in2Out();
        return queue1.poll();
    }

    private void in2Out() {
        while (queue1.size() > 1) {
            queue2.add(queue1.poll());
        }
        while (queue2.size() > 0) {
            queue1.add(queue2.poll());
        }
    }

    public int top() {
        in2Out();
        Integer val = queue1.poll();
        queue1.add(val);
        return val;
    }

    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }
}
