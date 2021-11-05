package com.ddup.thread.threadlocal;

import java.util.Map;

/**
 * @author: hwj
 * @Description TODO
 * @create: 2021/11/5 11:03
 */
public class UserServiceImpl {

    public static String setPwd() {
        Map<String, String> user = UserHolder.getUser();
        if (user != null) {
            System.out.println("user name is:" + user.get("name"));

            user.put("pwd", user.get("name") + "->pwd");
        }
        System.out.println("当前执行的请求是：" + Thread.currentThread().getName());
        return Thread.currentThread().getName();
    }
}
