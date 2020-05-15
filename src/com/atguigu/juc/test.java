package com.atguigu.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData
{
    volatile int  number = 0;

    public void AddNumber()
    {
        this.number = 90;
    }

    public void Add()
    {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void AddAtomic()
    {
        atomicInteger.getAndIncrement();
    }
}



public class test {
    public static void main(String[] args) {
        Atomic();
    }

    private static void Atomic() {
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++)
        {
            new Thread(() -> {
                for (int j = 0; j <1000 ; j++) {
                    myData.Add();
                    myData.AddAtomic();
                }
            }, String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2)
        {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+ "  final number" + myData.number);
        System.out.println(Thread.currentThread().getName()+ " atomicInteger final number" + myData.atomicInteger);
    }

    private static void My20000() {
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++)
        {
            new Thread(() -> {
                for (int j = 0; j <1000 ; j++) {
                    myData.Add();
                }
            }, String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2)
        {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+ "  final number" + myData.number);
    }

    private static void CanSee() {
        MyData myData = new MyData();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
            myData.AddNumber();
            System.out.println(Thread.currentThread().getName() + "update number" + myData.number);
                }, "AA").start();

        while (myData.number == 0)
        {

        }
        System.out.println(Thread.currentThread().getName() + "mession complate");
    }

}
