package com.ddup.spring.schedule;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class TimerStudy {
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("当前线程：" + Thread.currentThread().getName() + " ,当前时间：" + LocalDateTime.now());
            }
        };
        timer.schedule(task, 0, 2000);
    }
}
