package com.ddup.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: haowanjin
 * @Description
 * @create: 2022/5/5 20:27
 */
public class WeakReferenceTest {
    public static void main(String[] args) {
        ThreadLocal<String> local = new ThreadLocal<>();
        local.set("Hello world");
        List<String> l = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            l.add("list add " + i);
        }
        System.gc();
        System.out.println(local.get());
    }
}
