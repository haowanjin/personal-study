package com.ddup;

import java.util.*;

public class Test5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<App> list = new ArrayList<>();
        while (in.hasNext()) {
            String n = in.nextLine();
            for (int i = 0; i < Integer.parseInt(n); i++) {
                String s = in.nextLine();
                String[] str = s.split(" ");
                App app = new App(str[0], str[1], str[2], str[3]);
                list.add(app);

            }

            String time = in.nextLine();
            System.out.println(test11(list, time));
            list.clear();
        }
    }

    private static String test11(List<App> list, String time) {
        if (list.isEmpty()) {
            return "NA";
        }
        list.sort(Comparator.comparing(o -> o.end));
        List<App> arr = new ArrayList<>();

        for (App app : list) {
            if (app.start.compareTo(time) <= 0 && app.end.compareTo(time) >= 0) {
                arr.add(app);
            }
        }
        if (arr.isEmpty()) {
            return "NA";
        }
        if (arr.size() == 1) {
            return arr.get(0).name;
        }
        String name = "NA";
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i).end.compareTo(arr.get(i + 1).start) > 0 && arr.get(i + 1).priority.compareTo(arr.get(i).priority) > 0) {
                name = arr.get(i + 1).name;
            }
        }
        return name;
    }

    static class App {
        String name;
        String priority;
        String start;
        String end;

        public App(String name, String priority, String start, String end) {
            this.name = name;
            this.priority = priority;
            this.start = start;
            this.end = end;
        }
    }
}
