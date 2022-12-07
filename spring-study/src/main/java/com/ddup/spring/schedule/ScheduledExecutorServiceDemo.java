package com.ddup.spring.schedule;

import java.time.LocalDateTime;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceDemo {
    public static void main(String[] args) {
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(5);
        Runnable r = () -> {
            System.out.println("当前线程：" + Thread.currentThread().getName() + " ,当前时间：" + LocalDateTime.now());
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        service.scheduleAtFixedRate(r, 1, 5, TimeUnit.SECONDS);
    }
}
