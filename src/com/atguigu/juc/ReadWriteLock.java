package com.atguigu.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCash
{
    Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    public void put(String key, Object value)
    {
        reentrantReadWriteLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "该线程正在写入");
            try { TimeUnit.MICROSECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
            map.put(key,value);
            System.out.println(Thread.currentThread().getName() + "该线程写入完成");

        }catch (Exception e)
        {
             e.printStackTrace();
        }finally {
             reentrantReadWriteLock.writeLock().unlock();
        }

    }
    public void get(String key)
    {
        reentrantReadWriteLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"该线程正在读取");
            try { TimeUnit.MICROSECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
            Object value = map.get(key);
            System.out.println(Thread.currentThread().getName() + "该线程读取完成" + value);
        }catch (Exception e)
        {
             e.printStackTrace();
        }finally {
             reentrantReadWriteLock.readLock().unlock();
        }

    }
}

public class ReadWriteLock {

    public static void main(String[] args) {
        MyCash myCash = new MyCash();

        for (int i = 1; i <= 5; i++)
        {
            final int tempInt = i;
            new Thread(() -> {
                myCash.put(tempInt+"", tempInt+"");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++)
        {
            final int tempInt = i;
            new Thread(() -> {
                myCash.get(tempInt+"");
            }, String.valueOf(i)).start();
        }
    }
}
