package com.atguigu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Print3
{
    //定义标志位
    private int sigin = 1;
    private Lock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();

    public void A() throws InterruptedException {

        lock.lock();
        try{
            //判断
            while (sigin != 1)
            {
                c1.await();
            }
            //干活
            print4(5);
            //通知
            sigin = 2;
            c2.signal();
        }catch (Exception e)
        {
             e.printStackTrace();
        }finally {
             lock.unlock();
        }
    }
    public void B() throws InterruptedException {

        lock.lock();
        try{
            //判断
            while (sigin != 2)
            {
                c2.await();
            }
            //干活
            print4(10);
            //通知
            sigin = 3;
            c3.signal();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void C() throws InterruptedException {

        lock.lock();
        try{
            //判断
            while (sigin != 3)
            {
                c3.await();
            }
            //干活
            print4(15);
            //通知
            sigin = 1;
            c1.signal();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print4(int num)
    {
        for (int i = 1; i <=num ; i++) {
            System.out.println(Thread.currentThread().getName() + "       " + i);
        }

    }


}

public class MannyCondition {
    public static void main(String[] args) {
        Print3 print3 = new Print3();

        new Thread(() -> {
            for (int i = 1; i <=10 ; i++) {
                try {
                    print3.A();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
                }, "AA").start();
        new Thread(() -> {
            for (int i = 1; i <=10 ; i++) {
                try {
                    print3.B();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();
        new Thread(() -> {
            for (int i = 1; i <=10 ; i++) {
                try {
                    print3.C();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();
    }
}
