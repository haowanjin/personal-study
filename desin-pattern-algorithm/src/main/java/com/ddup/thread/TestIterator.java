package com.ddup.thread;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TestIterator {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(0, 1, 2, 3);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            try {
                System.out.println(120 / next);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
