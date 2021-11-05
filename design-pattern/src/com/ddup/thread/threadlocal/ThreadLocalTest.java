package com.ddup.thread.threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: hwj
 * @Description TODO
 * @create: 2021/11/5 10:10
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        Map<String, String> user = new HashMap<>();
        user.put("id", Thread.currentThread().getId() + "");
        user.put("name", Thread.currentThread().getName());
        UserHolder.setUserInfo(user);

        MyTask user1 = new MyTask();
        MyTask user2 = new MyTask();

        Thread t1 = new Thread(user1, "user1");
        Thread t2 = new Thread(user2, "user2");

        t1.start();
        t2.start();

        UserServiceImpl.setPwd();
        Map<String, String> currentUser = UserHolder.getUser();

        System.out.println(currentUser);


    }
}

class MyTask implements Runnable {
    @Override
    public void run() {
        Map<String, String> user = new HashMap<>();
        user.put("id", Thread.currentThread().getId() + "");
        user.put("name", Thread.currentThread().getName());
        UserHolder.setUserInfo(user);

        UserServiceImpl.setPwd();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}