package com.ddup.jvm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: hwj
 *
 *
 * -Xms5m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError
 *
 * @Description TODO
 * @create: 2021/11/30 17:16
 */
public class JvmThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(15000);
        new Thread(()->{
            List<byte[]> list = new ArrayList<>();
            while (true) {
                System.out.println(new Date().toString()+Thread.currentThread().getId()+"=====");
                byte[] b = new byte[1024 * 1024];
                list.add(b);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            while (true) {
                System.out.println(new Date().toString() + Thread.currentThread().getId() + "============");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }
}
