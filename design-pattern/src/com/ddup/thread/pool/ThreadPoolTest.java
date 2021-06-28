package com.ddup.thread.pool;

import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        //ExecutorService pool1 = Executors.newFixedThreadPool(2);
        ExecutorService pool2 = new ThreadPoolExecutor(2,
                20, 6, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        for (int i = 0; i < 6; i++) {
            pool2.execute(new ThreadTask("任务 " + i));
        }

        pool2.shutdown();

        while (!pool2.awaitTermination(1, TimeUnit.SECONDS)) {
            System.out.println("线程池没有关闭");
        }
        System.out.println("线程池关闭");

    }

    static class ThreadTask extends Thread {
        public ThreadTask(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                System.out.println(getName() + " *** 开始执行");
                TimeUnit.SECONDS.sleep(2);
                System.out.println(getName() + " 执行完毕 ***");
            } catch (InterruptedException e) {
                System.out.println(getName() + " 被中断");
            }
        }
    }
}
