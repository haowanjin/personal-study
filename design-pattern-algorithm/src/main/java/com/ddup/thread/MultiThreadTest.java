package com.ddup.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author haowanjin
 * @date 2022/12/30 16:20
 * @description
 */
public class MultiThreadTest {
    static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        int circle = 1000;
        ExecutorService pool = Executors.newFixedThreadPool(15);
        StringBuilder sb1 = new StringBuilder();
        StringBuffer sb2 = new StringBuffer();
        CountDownLatch l = new CountDownLatch(circle * 2);
        for (int i = 1; i <= circle; i++) {
            pool.execute(() -> {
                sb1.append(1+"");
                count++;
                l.countDown();
            });
        }
        for (int i = 1; i <= circle; i++) {
            pool.execute(() -> {
                sb2.append(1);
                count++;
                l.countDown();
            });

        }
        l.await();
        System.out.println("count = " + count);// 目标 = circle*2
        System.out.println("sb1 = " + sb1.length());// 目标 = circle
        System.out.println("sb2 = " + sb2.length());// 目标 = circle
        pool.shutdown();
    }
}
