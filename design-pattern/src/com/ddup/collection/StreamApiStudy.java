package com.ddup.collection;

import java.util.*;

/**
 * @author: haowanjin
 * @Description 集合 Stream API 学习
 * @create: 2022/3/4 16:32
 */
public class StreamApiStudy {
    public static void main(String[] args) {
        findFirstTest();
        //System.out.println(Arrays.toString(args));
    }

    public static void findFirstTest() {
        List<Integer> list = new ArrayList<>();
        list.add(12);
        list.add(21);
        list.add(11);
        list.add(12);
        Optional<Integer> first = list.stream().filter(e -> e==12).findFirst();
        System.out.println(first.get());
    }
}
