package com.ddup.algorithm.basic;

import java.util.HashMap;
import java.util.List;

public class UnionFindSet<V> {
    private HashMap<V, Element<V>> elementMap;
    private HashMap<Element<V>, Element<V>> fatherMap;
    private HashMap<Element<V>, Integer> sizeMap;

    public UnionFindSet(List<V> list) {
        elementMap = new HashMap<>();
        fatherMap = new HashMap<>();
        sizeMap = new HashMap<>();
        for (V v : list) {
            Element<V> element = new Element<>(v);
            elementMap.put(v, element);
            fatherMap.put(element, element);
            sizeMap.put(element, 1);
        }

    }

    public Element<V> findHead(V v) {

        return null;
    }

    public boolean isSameSet(V a, V b) {

        return false;
    }

    public void union(V a, V b) {
        if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
            Element<V> aF = findHead(a);
            Element<V> bF = findHead(b);
            Element<V> big = sizeMap.get(aF) >= sizeMap.get(bF) ? aF : bF;
            Element<V> small = big == aF ? bF : aF;
            fatherMap.put(small, big);
            sizeMap.put(big, sizeMap.get(aF) + sizeMap.get(bF));
            sizeMap.remove(small);
        }
    }

    private static class Element<V> {
        private V value;

        public Element(V value) {
            this.value = value;
        }
    }

}
