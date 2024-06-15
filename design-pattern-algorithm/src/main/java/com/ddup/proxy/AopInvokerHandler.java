package com.ddup.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author haowanjin
 * @date 2023/2/15 20:35
 * @description
 */
public class AopInvokerHandler implements InvocationHandler {
    private Object targetObj;

    public AopInvokerHandler(Object targetObj) {
       /* String text = """
                select * from
                user where
                a.id=''
                and a.text like '%dasfsa%'
                """;
        System.out.println(text);*/
        this.targetObj = targetObj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("preHandler 前置处理。。。。");
        Object invoke = method.invoke(targetObj, args);
        System.out.println("afterHandler 后置处理。。。。");
        return invoke;
    }
}
