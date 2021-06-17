package com.ddup.thread.cyclic;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Horse implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private int strides = 0;
    private static Random random = new Random(47);
    private static CyclicBarrier barrier;

    public Horse(CyclicBarrier b) {
        this.barrier = b;
    }

    public synchronized int getStrides() {
        return strides;
    }


    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    strides += random.nextInt(3);// 0,1 or 2
                }
                barrier.await();
            }
        } catch (InterruptedException e) {
            System.out.println("horse " + id + "interrupted");
        } catch (BrokenBarrierException e) {
            System.out.println("horse " + id + "brokenBarrier");
            e.printStackTrace();
        }
    }

    public String toString() {
        return "Horse " + id + " -->" + getStrides();
    }

    public String tracks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getStrides(); i++) {
            sb.append("*");
        }
        sb.append(id);
        return sb.toString();
    }
}
