package com.ddup.algorithm.dp;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @author haowanjin
 * @date 2023/5/8 13:01
 * <p>
 *
 * </p>
 */
public class GameBag {
    private static class Element {
        private String name;
        private int value;/*物品价值*/
        private int cost;/*物品的花费*/

        public Element(String name, int cost, int value) {
            this.name = name;
            this.value = value;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Element{" +
                    "name='" + name +
                    "'，value=" + value +
                    "，cost=" + cost + "}";
        }
    }

    private static class ArrayElement {
        private int value;
        private Set<Element> elements;

        public ArrayElement(int value, Element element) {
            this.value = value;
            this.elements = new HashSet<>();
            this.elements.add(element);
        }

        public ArrayElement(int value, Set<Element> elements) {
            this.value = value;
            this.elements = elements;
        }

        @Override
        public String toString() {
            return "BagElement{" +
                    "value=" + value +
                    ",elements=" + elements;
        }
    }

    public void printRow(int rowNo, int bagSize, ArrayElement[][] calc) {
        System.out.println("当前行号：" + rowNo);
        for (int j = 0; j < bagSize; j++) {
            if (calc[rowNo][j] != null) {
                System.out.print("j=" + j + "," + calc[rowNo][j] + "\t");
            }
        }
        System.out.println();
    }

    /**
     * 1.当前背包小于当前物品花费，取上一行当前列
     * 2.当前背包不小于当前物品花费
     *      a.等于，比较当前价值与上一行当前列的价值取较大
     *      b.有剩余，当前价值
     * @param goods
     * @param bagSize
     */
    public void putBag(Element[] goods, int bagSize) {
        ArrayElement[][] calcArray = new ArrayElement[goods.length][bagSize];
        for (int i = 0; i < goods.length; i++) {
            for (int j = 0; j < bagSize; j++) {
                if (i == 0) {
                    calcArray[i][j] = new ArrayElement(goods[i].value, goods[i]);
                } else {
                    /**/
                    int spareSpace = j + 1 - goods[i].cost;
                    if (spareSpace < 0) {
                        calcArray[i][j] = calcArray[i - 1][j];
                    } else {
                        int preRow = i - 1;
                        int preRowValue = calcArray[preRow][j].value;
                        int currentValue = goods[i].value;
                        if (spareSpace > 0) currentValue += calcArray[preRow][spareSpace - 1].value;
                        if (preRowValue >= currentValue) {
                            calcArray[i][j] = new ArrayElement(preRowValue, calcArray[preRow][j].elements);
                        } else {
                            if (spareSpace == 0) {
                                calcArray[i][j] = new ArrayElement(currentValue, goods[i]);
                            } else {
                                Set<Element> newElement = new HashSet<>(calcArray[preRow][spareSpace - 1].elements);
                                newElement.add(goods[i]);
                                calcArray[i][j] = new ArrayElement(currentValue, newElement);
                            }
                        }
                    }
                }
            }
            printRow(i, bagSize, calcArray);
            System.out.println("-----------------------------------------------");
        }
        System.out.println("最终结果" + calcArray[goods.length - 1][bagSize - 1]);
    }

    public static void main(String[] args) {
        Element[] tourElements = new Element[]{
                new Element("天安门广场", 1, 9),
                new Element("故宫", 4, 9),
                new Element("颐和园", 2, 9),
                new Element("八达岭长城", 1, 7),
                new Element("天坛", 1, 6),
                new Element("圆明园", 2, 8),
                new Element("恭王府", 1, 5)
        };
        new GameBag().putBag(tourElements, 4);
    }

}
