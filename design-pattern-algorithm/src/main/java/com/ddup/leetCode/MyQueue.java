package com.ddup.leetCode;

import java.util.Stack;

/**
 * @author haowanjin
 * @date 2023/5/6 14:14
 * <p>
 *
 * </p>
 */
public class MyQueue {
    Stack<Integer> inStack = new Stack<>();
    Stack<Integer> outStack = new Stack<>();

    public static void main(String[] args) {
        MyQueue q = new MyQueue();
        q.push(1);
        q.push(2);
        q.push(3);
        q.push(4);
        q.push(5);
        q.push(6);
        q.push(7);
        q.push(8);
        q.push(9);
        q.push(10);

        System.out.println(q.peek());
        System.out.println(q.pop());
    }


    public void push(int x) {
        inStack.push(x);
    }


    public int pop() {
        if (outStack.isEmpty()) {
            in2out();
        }
        return outStack.pop();
    }

    public int peek() {
        if (outStack.isEmpty()) {
            in2out();
        }
        return outStack.peek();
    }

    private void in2out() {
        while (!inStack.isEmpty()) {
            outStack.add(inStack.pop());
        }
    }

    public boolean isEmpty() {
        return outStack.isEmpty() && inStack.isEmpty();
    }
}
