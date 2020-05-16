package com.atguigu.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
/*
*
* 通过实现Callable接口来新建一个线程
* */
class MyThread implements Callable<Integer>
{


    @Override
    public Integer call() throws Exception {
        try {
            TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("Callable接口中的call方法已经被调用了");
        return 1024;
    }
}


public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        Thread thread = new Thread(futureTask, "AA");

        thread.start();

        try {TimeUnit.SECONDS.sleep(6);} catch (InterruptedException e) {e.printStackTrace();}
        System.out.println(futureTask.get());
    }
}
