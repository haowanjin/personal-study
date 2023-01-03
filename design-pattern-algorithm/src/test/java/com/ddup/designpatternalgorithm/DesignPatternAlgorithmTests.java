package com.ddup.designpatternalgorithm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@SpringBootTest
public class DesignPatternAlgorithmTests {
    StringBuilder sb1 = new StringBuilder();
    int count = 0;
    StringBuffer sb2 = new StringBuffer();
    CountDownLatch l = new CountDownLatch(200);

    @Test
   public void contextLoads() throws InterruptedException {
        for (int i = 1; i <= 100; i++) {
            new Thread(() -> {
                sb1.append(1);
                count++;
                l.countDown();
            }).start();
        }
        for (int i = 1; i <= 100; i++) {
            new Thread(() -> {
                sb2.append(1);
                count++;
                l.countDown();
            }).start();
        }
        l.await();
        System.out.println("count = " + count);
        System.out.println("sb1 = " + sb1.length());
        System.out.println("sb2 = " + sb2.length());
    }

    @Test
    public void testInt() {
        Integer a = new Integer(3);
        Integer b = 3; // 将3自动装箱成Integer类型
        int c = 3;
        Integer d = new Integer(2);
        Integer e = 2;
        Integer f = 2;
        System.out.println(e == f);
        System.out.println(d == f);
        System.out.println(d == e);// false
        System.out.println(a == b); // false 两个引用没有引用同一对象
        System.out.println(a == c); // true a自动拆箱成int类型再和c比较
        System.out.println(b == c); // true
    }

    @Test
    public void testArrayList() {
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);

        for (int i = 0; i < arr.size(); i++) {
            if (i == 1) {
                arr.remove(1);
            }
        }
        System.out.println(arr);
        Iterator<Integer> it = arr.iterator();
        while (it.hasNext()) {
            Integer i = it.next();
            if (i == 3) {
                it.remove();
            }
        }
        System.out.println(arr);
        /*for (Integer i : arr) {
            if (i == 2) {
                arr.remove(2);
            }
        }*/
    }

}
