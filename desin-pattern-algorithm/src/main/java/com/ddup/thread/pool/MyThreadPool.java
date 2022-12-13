package com.ddup.thread.pool;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MyThreadPool {
    private final static int WORK_COUNT = 5;
    private final static int TASK_COUNT = 30;
    private final int coreSize;
    private final BlockingQueue<Runnable> TASK_QUEUE;
    private WorkThread[] threadWorks;

    public MyThreadPool(int coreSize, int taskCount) {
        if (coreSize <= 0)
            coreSize = WORK_COUNT;
        if (taskCount <= 0)
            taskCount = TASK_COUNT;

        this.coreSize = coreSize;
        threadWorks = new WorkThread[this.coreSize];
        this.TASK_QUEUE = new ArrayBlockingQueue<>(taskCount);
        for (int i = 0; i < coreSize; i++) {
            threadWorks[i] = new WorkThread();
            threadWorks[i].start();
        }

    }

    public void execute(Runnable task) {
        try {
            TASK_QUEUE.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        System.out.println("ready close pool...");
        for (int i = 0; i < coreSize; i++) {
            threadWorks[i].stopWorker();
            threadWorks[i] = null;
        }
        TASK_QUEUE.clear();
    }

    private class WorkThread extends Thread {
        @Override
        public void run() {
            Runnable r;
            try {
                while (!isInterrupted()) {
                    r = TASK_QUEUE.take();
                    if (r != null) {
                        System.out.println(getId() + " ready to execute " +
                                ((MyPoolTest.MyTask) r).getName());
                        r.run();
                    }
                }
                r = null;
            } catch (InterruptedException e) {
                System.out.println(getId() + " work has been interrupted");
            }
        }

        public void stopWorker() {
            interrupt();
        }
    }

    @Override
    public String toString() {
        return "MyThreadPool{" +
                "coreSize=" + coreSize +
                ", threadWorks=" + Arrays.toString(threadWorks) +
                '}';
    }
}
