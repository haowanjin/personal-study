package com.ddup;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Test3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int markNum = in.nextInt();
            List<Integer> mark = Arrays.stream(in.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            int tree = in.nextInt();
            System.out.println(calcDist(mark, tree));
        }
    }

    private static int calcDist(List<Integer> mark, int tree) {
        Collections.sort(mark);
        int step = mark.size() / tree;
        int dist = mark.get(mark.size() - 1) / (tree - 1);

        return dist - mark.get(step);


    }
}
