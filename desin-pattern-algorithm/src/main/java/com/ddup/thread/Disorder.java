package com.ddup.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author haowanjin
 * @date 2022/12/28 20:53
 * @description
 */
public class Disorder {
    private /*volatile*/ static int a = 0, b = 0, x = 0, y = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < Long.MAX_VALUE; i++) {
            a = b = x = y = 0;
            CountDownLatch latch = new CountDownLatch(2);
            Thread t1 = new Thread(() -> {
                a = 1;
                x = b;
                latch.countDown();
            });

            Thread t2 = new Thread(() -> {
                b = 1;
                y = a;
                latch.countDown();
            });
            t1.start();
            t2.start();
            latch.await();
            String result = "第" + i + "次时，x=" + x + ",y=" + y;
            if (x == 0 && y == 0) {
                System.out.println(result);
                break;
            }
        }
    }
}
