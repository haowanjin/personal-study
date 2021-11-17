package com.ddup.thread.pool;

import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        int taskSize = 10;
        // ExecutorService pool = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(taskSize);
        ExecutorService pool = new ThreadPoolExecutor(2,
                20, 6, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        for (int i = 0; i < taskSize; i++) {
            pool.execute(new ThreadTask("任务 " + (i + 1), latch));
        }
        //  pool.shutdown(); // 会等待所有任务执行完才关闭线程池
        List<Runnable> runnables = pool.shutdownNow();// 尝试中断（如果任务没有对interrupted异常做处理，也无法中断)正在执行的任务，返回未开始的任务列表
        //latch.await();
        while (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
            System.out.println("线程池没有关闭");
        }
        System.out.println("线程池关闭");
    }
    static class ThreadTask extends Thread {
        CountDownLatch latch;
        public ThreadTask(String name, CountDownLatch latch) {
            super(name);
            this.latch = latch;
        }
        @Override
        public void run() {
            try {
                System.out.println(getName() + " *** 开始执行");
                TimeUnit.SECONDS.sleep(2);
                System.out.println(getName() + " 执行完毕 ***");
                latch.countDown();
            } catch (InterruptedException e) {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException interruptedException) {
//                        interruptedException.printStackTrace();
                    }
                    System.out.println(getName() + " 被中断");
                }
            }
        }
    }
}
