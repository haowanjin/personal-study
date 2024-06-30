package com.ddup.cloud.user;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ExcelTest {
    @Test
    public void test2() {
        List<Integer> list = new ArrayList<>();
        List<Integer> l2 = new LinkedList<>();
        l2.add(1);
        l2.add(1);
        l2.add(1);
        list = l2.stream().toList();
        System.out.println(list);
    }

    @Test
    public void test1() throws IOException {
        List<String> name1 = getList("D:\\WDR\\公示用研判意见表0610(4).xlsx");
        List<String> name2 = getList("D:\\WDR\\公示用研判意见表0612.xlsx");
        List<String> list = name2.stream().filter(e -> !name1.contains(e)).toList();

        System.out.println(list);
    }

    @Test
    public void testCash() {
        int[] initCashes = {5, 2, 5, 1};
        int[][][] paidCashes = {{{47}, {0, 0, 0, 0, 1}}, {{2}, {0, 1, 0, 0, 0}}, {{1}, {1, 0, 0, 1, 0}}};
        List<Integer> integers = calcCashes(initCashes, paidCashes);
        System.out.println(integers);
    }

    private List<Integer> calcCashes(int[] initCashes, int[][][] payments) {
        List<Money> moneyList = new ArrayList<>(5);

        int[] money = {1, 5, 10, 50, 100};
        for (int i = 0; i < initCashes.length; i++) {
            moneyList.add(new Money(money[i], initCashes[i]));
        }
        if (moneyList.size() < 5) {
            moneyList.add(new Money(100, 0));
        }
        for (int[][] payment : payments) {
            int price = payment[0][0];
            int[] paidCashes = payment[1];
            int paid = calcPaid(paidCashes, moneyList);
            if (paid < price) {
                continue;
            }
            for (int i = 0; i < paidCashes.length; i++) {
                if (paidCashes[i] > 0) {
                    moneyList.get(i).count += paidCashes[i];
                }
            }
            if (paid > price) {
                boolean flag = calReturn(moneyList, paid, price);
                if (!flag) {
                    for (int i = 0; i < paidCashes.length; i++) {
                        if (paidCashes[i] > 0) {
                            moneyList.get(i).count -= paidCashes[i];
                        }
                    }
                }
            }
        }
        return moneyList.stream().map(e -> e.count).collect(Collectors.toList());
    }

    private boolean calReturn(List<Money> moneyList, int paid, int price) {
        List<Money> baklist = moneyList.stream().toList();
        int returnMoney = paid - price;
        for (int i = baklist.size() - 1; i >= 0; i--) {
            if (returnMoney == 0) {
                return true;
            }
            if (returnMoney >= baklist.get(i).value) {
                int delCnt = returnMoney / baklist.get(i).value;
                if (delCnt > baklist.get(i).count) {
                    returnMoney = returnMoney - baklist.get(i).count * baklist.get(i).value;
                    baklist.get(i).count = 0;
                } else {
                    returnMoney = returnMoney - baklist.get(i).value * delCnt;
                    baklist.get(i).count -= delCnt;
                }
            }
        }
        if (returnMoney == 0) {
            for (int i = 0; i < baklist.size(); i++) {
                moneyList.add(i, baklist.get(i));
            }
        }
        return returnMoney == 0;
    }

    private int calcPaid(int[] paidCashes, List<Money> moneyList) {
        int sum = 0;
        for (int i = 0; i < paidCashes.length; i++) {
            sum += moneyList.get(i).value * paidCashes[i];
        }
        return sum;
    }


    private List<String> getList(String path) {
        List<String> name1 = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(path)) {
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet1 = workbook.getSheet("不予置人员名单");
            for (int i = 2; i < sheet1.getPhysicalNumberOfRows(); i++) {
                if (sheet1.getRow(i) != null && sheet1.getRow(i).getCell(1) != null) {
                    name1.add(sheet1.getRow(i).getCell(1).getStringCellValue());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return name1;
    }

    static class Money {
        int value;
        int count;

        public Money(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }
}