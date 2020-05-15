package com.atguigu.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Singal2
{
    private volatile static Singal2 singal2 = null;
    Lock lock = new ReentrantLock();
    private Singal2()
    {
        System.out.println("我是单例模式，构造方法被调用了");
    }

    public  static Singal2 getInstance()
    {

        if (singal2 == null)
        {
            synchronized (Singal2.class)
            {
                if (singal2 == null)
                {
                    singal2 = new Singal2();
                }
            }
        }
        return singal2;
    }
}


public class Signal {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++)
        {
            new Thread(() -> {
                Singal2.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
