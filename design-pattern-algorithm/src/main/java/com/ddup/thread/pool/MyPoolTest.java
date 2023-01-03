package com.ddup.thread.pool;

import java.util.Random;

public class MyPoolTest {
    public static void main(String[] args) throws InterruptedException {
        MyThreadPool p = new MyThreadPool(3, 5);
        p.execute(new MyTask("TaskA"));
        p.execute(new MyTask("TaskB"));
        p.execute(new MyTask("TaskC"));
        p.execute(new MyTask("TaskD"));
        p.execute(new MyTask("TaskE"));

        System.out.println(p);

        Thread.sleep(10000);

        p.destroy();
        System.out.println(p);

    }

    public static class MyTask implements Runnable {
        private String name;
        private Random r = new Random();

        public MyTask(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(r.nextInt(1000) + 2000);
            } catch (InterruptedException e) {
                System.out.println(getName() + "sleeping is interrupted --->" + Thread.currentThread().isInterrupted());
            }
            System.out.println("任务 " + getName() + " 完成");
        }
    }
}
