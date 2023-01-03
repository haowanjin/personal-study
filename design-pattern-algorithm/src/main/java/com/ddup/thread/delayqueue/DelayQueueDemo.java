package com.ddup.thread.delayqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class DelayQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<DelayTask> queue = new DelayQueue();

        for (int i = 0; i < 10; i++) {
            try {
                queue.put(new DelayTask("work " + i, 2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ThreadGroup g = new ThreadGroup("Consumer");
        for (int i = 0; i < 3; i++) {
            new Thread(g, new DelayTaskConsumer(queue)).start();
        }

        while (DelayTask.taskCount.get() > 0) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        g.interrupt();
        System.out.println("Main thread finished");

    }

}

class DelayTask implements Delayed {
    private static long currentTime = System.currentTimeMillis();
    protected final String taskName;
    protected final int timeCost;
    protected final long scheduleTime;

    protected static final AtomicInteger taskCount = new AtomicInteger(0);


    public DelayTask(String taskName, int timeCost) {
        this.taskName = taskName;
        this.timeCost = timeCost;
        taskCount.incrementAndGet();
        currentTime += 1000 + (long) (Math.random() * 1000);
        scheduleTime = currentTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        final long expireTime = scheduleTime - System.currentTimeMillis();
        return unit.convert(expireTime, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.scheduleTime - ((DelayTask) o).scheduleTime);
    }

    public void execTask() {
        long startTime = System.currentTimeMillis();
        System.out.println("[" + Thread.currentThread().getName() + "]" + taskName + ": schedule_start_time=" + scheduleTime + ",real start time="
                + startTime + ",delay=" + (startTime - scheduleTime));
        try {
            Thread.sleep(timeCost);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class DelayTaskConsumer implements Runnable {
    private final BlockingQueue<DelayTask> queue;

    public DelayTaskConsumer(BlockingQueue<DelayTask> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        DelayTask task = null;
        try {
            while (!Thread.interrupted()) {
                task = queue.take();
                task.execTask();
                DelayTask.taskCount.decrementAndGet();
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " " + task.taskName + " interrupted");
        }
    }
}