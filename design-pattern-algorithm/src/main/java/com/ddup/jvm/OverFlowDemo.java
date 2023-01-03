package com.ddup.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: hwj
 * @Description
 * @create: 2021/9/17 14:09
 */
public class OverFlowDemo {
    private int stackLength = 1;

    public static void main(String[] args) {
        OverFlowDemo demo = new OverFlowDemo();
        try {
            demo.stackOverFlow();
        } catch (Throwable e) {
            System.out.println("stackLength: " + demo.stackLength);
        }
        //demo.heapOverFlow();
    }


    /**
     * java 虚拟机栈：用于存储方法栈帧，如果一个方法不断调用自己，就会出现栈溢出
     */
    public void stackOverFlow() {
        stackLength++;
        stackOverFlow();
    }

    /**
     * 如果不断创建对象，就会出现堆溢出
     */
    public void heapOverFlow() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            list.add("stack over flow");
        }
    }


}
