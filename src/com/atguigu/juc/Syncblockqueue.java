package com.atguigu.juc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class Syncblockqueue {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(() -> {

            try {
                System.out.println(Thread.currentThread().getName() + "正在放入值1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName() + "正在放入值2");
                blockingQueue.put("2");

                System.out.println(Thread.currentThread().getName() +"正在放入值3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


                }, "AA").start();

        new Thread(() -> {

            try {
                System.out.println(Thread.currentThread().getName() +"正在取出值");
                blockingQueue.take();
                System.out.println(Thread.currentThread().getName() +"正在取出值");
                blockingQueue.take();

                System.out.println(Thread.currentThread().getName() +"正在取出值");
                blockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }, "BB").start();
    }
}
