package com.atguigu.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABAOver {
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);
    public static void main(String[] args) {
        new Thread(() -> {
            Integer stamp = atomicStampedReference.getStamp();
            System.out.println("初始版本号" + stamp);
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
                    atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1);
                    atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1);
                }, "AA").start();

        new Thread(() -> {
            Integer stamp = atomicStampedReference.getStamp();
            System.out.println("初始版本号" + stamp);
            try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(atomicStampedReference.compareAndSet(100, 101, stamp, stamp + 1));
        }, "BB").start();
    }

    private static void ABAmethod() {
        AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

        new Thread(() -> {
                    atomicReference.compareAndSet(100, 101);
                    atomicReference.compareAndSet(101,100);
                }, "AA").start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(atomicReference.compareAndSet(100, 2020) + " ttt" + atomicReference.get());
        }, "BB").start();
    }
}
