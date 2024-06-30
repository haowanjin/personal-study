package com.ddup.leetCode;

import java.util.*;

public class ShortestSequence {
    public static void main(String[] args) {
        Integer[] events = {1, 1, 1, 1, 1, 9, 1, 8};
        Integer[] traits = {1, 9, 8};
        List<Integer> l2 = Arrays.asList(traits);

        Set<Integer> cache = new HashSet<>();
        List<Integer> matched = new ArrayList<>();

        int start = 0;
        int end = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < events.length; i++) {
            for (int j = i; j < events.length; j++) {
                if (!cache.contains(events[j]) && l2.contains(events[j])) {
                    if (!matched.isEmpty() && l2.indexOf(events[j]) < l2.indexOf(events[matched.get(matched.size() - 1)])) {
                        continue;
                    }
                    cache.add(events[j]);
                    matched.add(j);
                }
                if (matched.size() == l2.size()) {
                    if (matched.get(matched.size() - 1) - matched.get(0) < min) {
                        start = matched.get(0);
                        end = matched.get(matched.size() - 1);
                        min = Math.min(min, end - start);
                    }
                    cache.clear();
                    matched.clear();
                }
            }

        }
        Integer[] res = Arrays.copyOfRange(events, start, end + 1);
        System.out.println(Arrays.toString(res));
    }
}
