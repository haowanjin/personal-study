package com.ddup.pattern;

public class ClassLoadTest {
    static {
        System.out.println("ClassLoadTest static block");
    }
    public static void main(String[] args) {
        new A();
    }
}

class A{
    static {
        System.out.println("A static block");
    }

    public A() {
        System.out.println("constructor method");
    }
}
