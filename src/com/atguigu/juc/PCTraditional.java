package com.atguigu.juc;
/*
* 传统版的生产者和消费者
* */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Number
{
    private int num = 0;
    Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    //增加一
    public void Increasment() throws InterruptedException {

        lock.lock();
        try{
            //判断
            while (num != 0)
            {
                condition.await();
            }
            //干活
            num++;
            //通知
            System.out.println(Thread.currentThread().getName() + "        " + num);
            condition.signalAll();
        }catch (Exception e)
        {
             e.printStackTrace();
        }finally {
             lock.unlock();
        }
    }


    //减少一
    public void decreasment() throws InterruptedException {

        lock.lock();
        try{
            //判断
            while (num == 0)
            {
                condition.await();
            }
            //干活
            num--;
            //通知
            System.out.println(Thread.currentThread().getName() + "        " + num);
            condition.signalAll();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}


public class PCTraditional {
    public static void main(String[] args) {
        Number number = new Number();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    number.Increasment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
                }, "AA").start();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    number.decreasment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();

    }
}
