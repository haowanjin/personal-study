package com.ddup.thread.threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: hwj
 * {@link MyTask} 相当于一个用户访问 Controller，并调用 Service
 * {@link UserHolder} 相当于 Session，保存当前访问的用户信息
 * {@link UserServiceImpl#setPwd()} 修改用户密码方法，每个用户只修改自己的密码
 * @create: 2021/11/5 10:10
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
       /* Map<String, String> user = new HashMap<>();
        user.put("id", Thread.currentThread().getId() + "");
        user.put("name", Thread.currentThread().getName());
        // 相当于登录成功，将用户信息存入 Session
        UserHolder.setUserInfo(user);
        // 模仿两个用户登录系统
        MyTask user1 = new MyTask();
        MyTask user2 = new MyTask();
        Thread t1 = new Thread(user1, "user1");
        Thread t2 = new Thread(user2, "user2");
        t1.start();
        t2.start();

        UserServiceImpl.setPwd();
        Map<String, String> currentUser = UserHolder.getUser();
        System.out.println(currentUser);

        */
        ThreadLocal<Integer> a = new ThreadLocal<>();
        ThreadLocal<Integer> b = new ThreadLocal<>();
        ThreadLocal<Integer> c = new ThreadLocal<>();

        a.set(12);
        b.set(1223);
        c.set(1232);
        System.out.println(a.get());


    }
}

class MyTask implements Runnable {
    @Override
    public void run() {
        // 相当于登录
        Map<String, String> user = new HashMap<>();
        user.put("id", Thread.currentThread().getId() + "");
        user.put("name", Thread.currentThread().getName());
        UserHolder.setUserInfo(user);
        // 调用 Service，这些 Service 可从UserHolder 获取当前登录的用户信息
        UserServiceImpl.setPwd();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}