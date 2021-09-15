package com.ddup.reptile;

/**
 * @author: hwj
 * @Description TODO
 * @create: 2021/9/3 11:05
 */
public class HelloReptile {
    public static void main(String[] argv) {

        test1();
    }

    public static void test1() {
        String a = "JAVA";
        String a1 = "JAVA".intern();
        String b = "JAVA";
        String c = new String("JAVA");
        String d = "JA";
        String e = "VA";
        String f = "JA" + "VA";
        String g = d + e;
        String h = c;
        System.out.println("a==a1  " + (a == a1));
        System.out.println("a==a  " + (a == a));
        System.out.println("a==b  " + (a == b));
        System.out.println("a==c  " + (a == c));
        System.out.println("a equal c  " + (a.equals(c)));
        System.out.println("a==f  " + (a == f));
        System.out.println("a==g  " + (a == g));
        System.out.println("a==h  " + (a == h));
        System.out.println("c==h  " + (c == h));
    }

}

class Customer {

    private final long customerID;

    public Customer() {
        customerID = createID();
    }

    public long getID() {
        return customerID;
    }

    private long createID() {
        return customerID; // generate new ID
    }

    // more declarations
}

class Status {
    int st = 1;

    public void tes() {
        int[] a1 = {1, 3, 4,};
        int a2[] = {1, 2, 3};
        int a3[] = new int[]{1, 2, 4};
    }
}