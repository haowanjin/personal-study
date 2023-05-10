package com.ddup.algorithm.basic;

/**
 * @author haowanjin
 * @date 2022/12/24 11:51
 * @description
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(3, 'A', 'B', 'C');
    }

    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第1个盘子 " + a + "->" + c);
        } else {
            //将上面n-1个盘子从a移到b
            hanoiTower(num - 1, a, c, b);
            //将最下面的盘子从a移到b
            System.out.println("第" + num + "个盘子 " + a + "->" + c);
            // 将b上的n-1个盘子从b移到c
            hanoiTower(num - 1, b, a, c);
        }
    }
}
