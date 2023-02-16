package com.ddup.proxy;

import java.lang.reflect.Proxy;

/**
 * @author haowanjin
 * @date 2023/2/15 20:33
 * @description JDK动态代理
 */
public class ProxyTest {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();

        UserService o = (UserService) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(), new Class[]{UserService.class}, new AopInvokerHandler(service));
        o.doSth();
    }
}
