package com.atguigu.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Cycle {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> { System.out.println("七颗龙珠已经集齐，开始召唤神龙");});

        for (int i = 1; i <= 7; i++)
        {
            final int temp = i;
            new Thread(() -> {
                System.out.println("第" + temp + "课龙珠已经集齐");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
