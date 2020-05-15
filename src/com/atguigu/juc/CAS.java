package com.atguigu.juc;

import java.util.concurrent.atomic.AtomicInteger;

public class CAS {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5,2020)+"tttt" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5,2022));
    }
}
