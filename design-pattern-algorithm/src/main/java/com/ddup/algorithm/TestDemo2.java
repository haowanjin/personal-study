package com.ddup.algorithm;

import java.util.*;


public class TestDemo2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            boolean flag = true;
            String s1 = in.nextLine();
            String[] arr1 = s1.split(",");
            int m = Integer.parseInt(arr1[0]);
            int n = Integer.parseInt(arr1[1]);
            if (m < 3 || m > 10 || n < 3 || n > 100) {
                System.out.println(-1);
                continue;
            }
            int[][] arr = new int[m][n];
            for (int i = 0; i < m; i++) {
                String s2 = in.nextLine();
                String[] arr2 = s2.split(",");
                for (int j = 0; j < n; j++) {
                    int score = Integer.parseInt(arr2[j]);
                    if (score > 10 || score < 1) {
                        System.out.println(-1);
                        flag = false;
                        break;
                    }
                    arr[i][j] = score;
                }
                if (!flag) {
                    break;
                }
            }


            System.out.println(calcScore(arr));
        }

    }

    private static String calcScore(int[][] arr) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < arr[0].length; i++) {
            players.add(new Player(i + 1));
        }

        for (int[] ints : arr) {
            for (int i = 0; i < ints.length; i++) {
                players.get(i).socreList.add(ints[i]);
            }
        }

        Collections.sort(players);
        Player p1 = players.get(0);
        Player p2 = players.get(1);
        Player p3 = players.get(2);


        return p1.num + "," + p2.num + "," + p3.num;
    }

    private static class Player implements Comparable<Player> {
        int num;
        List<Integer> socreList = new ArrayList<>();

        public Player(int num) {
            this.num = num;
        }

        @Override
        public int compareTo(Player o) {
            if (this.getScore() - o.getScore() == 0) {
                this.socreList.sort((o1, o2) -> o2 - o1);
                o.socreList.sort((o1, o2) -> o2 - o1);
                for (int i = 0; i < this.socreList.size(); i++) {
                    if (o.socreList.get(i) > this.socreList.get(i)) {
                        return 1;
                    } else if (o.socreList.get(i) < this.socreList.get(i)) {
                        return -1;
                    }
                }
            }
            return o.getScore() - this.getScore();
        }

        public int getScore() {
            return socreList.stream().mapToInt(e -> e).sum();
        }
    }
}
