package com.ddup.algorithm.actual;

import java.util.*;


/**
 * @author haowanjin
 * @date 2023/5/15 16:47
 * <p>
 *
 * </p>
 */
public class StringSort {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 3, 3, 2, 1, 5);
        list.sort((a, b) -> b - a);
        System.out.println(list);

        Scanner sc = new Scanner(System.in);
        String[] strArr = sc.nextLine().split(" ");
        System.out.println(getResult(strArr));
    }

    public static String getResult(String[] arr) {
        arr = Arrays.stream(arr).map(str -> {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        }).toArray(String[]::new);
        Map<String, Integer> countMap = new HashMap<>();
        for (String s : arr) {
            countMap.compute(s, (k, v) -> {
                v = v == null ? 1 : v + 1;
                return v;
            });
        }
        Arrays.sort(arr, (a, b) ->
                !Objects.equals(countMap.get(a), countMap.get(b)) ? countMap.get(b) - countMap.get(a) : (
                        a.length() != b.length() ? a.length() - b.length() : a.compareTo(b)
                )
        );

        StringJoiner sj = new StringJoiner(" ", "", "");

        for (String s : arr) {
            sj.add(s);
        }
        return sj.toString();
    }
}
