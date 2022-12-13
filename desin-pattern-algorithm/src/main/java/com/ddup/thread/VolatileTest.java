package com.ddup.thread;

public class VolatileTest {
    public static volatile int race = 0;
    private static final int THREADS_COUNT = 20;

    public static void incr() {
        race++;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(VolatileTest::incr);
            threads[i].start();
        }
        Thread.sleep(500);
        System.out.println(Thread.activeCount());

        System.out.println(race);
    }
}
