package com.atguigu.juc;

import java.util.concurrent.CountDownLatch;

public class CountDown {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++)
        {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "被灭");
                countDownLatch.countDown();
            },CountryEumu.forEach_Country(i).getRetMessage()).start();
        }

        countDownLatch.await();
        System.out.println("秦国统一");

    }
}
