package com.atguigu.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

class Lock3
{
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock()
    {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+ "该线程已经获得锁了");
        while (!atomicReference.compareAndSet(null,thread))
        {

        }
    }

    public void myunlock()
    {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println("锁已经释放");
    }
}


public class SpinLock {
    public static void main(String[] args) {
        Lock3 lock3 = new Lock3();
        new Thread(() -> {
                    lock3.myLock();
                    try { TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
                    lock3.myunlock();
                }, "AAA").start();

        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(() -> {
                    lock3.myLock();
            System.out.println("BBB线程得到锁");
            lock3.myunlock();
                }, "BBB").start();
    }
}
