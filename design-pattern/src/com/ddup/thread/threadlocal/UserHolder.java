package com.ddup.thread.threadlocal;

import java.util.Map;

/**
 * @author: hwj
 * @Description 持有当前会话的用户信息
 * @create: 2021/11/5 10:50
 */
public class UserHolder {
    private final static ThreadLocal<Map<String, String>> userInfo = new ThreadLocal<>();

    public static void setUserInfo(Map<String, String> user) {
        userInfo.set(user);
    }

    public static Map<String, String> getUser() {
        return userInfo.get();
    }

    public static void clearHolder() {
        userInfo.remove();
    }
}
